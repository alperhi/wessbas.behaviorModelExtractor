package net.sf.markov4jmeter.behaviormodelextractor.extraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.RBMToMarkovMatrixTransformer;
import net.sf.markov4jmeter.behaviormodelextractor.util.CSVHandler;

/**
 * Class for writing Behavior Models to files.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class BehaviorModelWriter {


    /* *****************************  constants  **************************** */


    /** Information message for the case that a file has been written. */
    private final static String INFO_FILE_WRITTEN =
            "File \"%s\" written successfully.";

    /** Information message for the case that an operation has been finished. */
    private final static String INFO_FINISHED = "Finished.";

    /** Suffix of DOT-files. */
    private final static String DOT_FILE_SUFFIX = ".dot";

    /** Suffix of CSV-files. */
    private final static String CSV_FILE_SUFFIX = ".csv";


    /* *************************  global variables  ************************* */


    /** Instance for transforming Behavior Models to Markov matrices. */
    private final RBMToMarkovMatrixTransformer rbmToMarkovMatrixTransformer;


    /** Instance for generating DOT graphs from Markov matrices. */
    private final DotGraphGenerator dotGraphGenerator;


    /* ***************************  constructors  *************************** */


    /**
     * Constructor for a Behavior Model Writer.
     *
     * @param markovMatrixHandler
     *     instance for transforming Behavior Models to Markov matrices.
     * @param dotGraphGenerator
     *     instance for generating DOT graphs from Markov matrices.
     */
    public BehaviorModelWriter (
            final RBMToMarkovMatrixTransformer rbmToMarkovMatrixTransformer,
            final DotGraphGenerator dotGraphGenerator) {

        this.rbmToMarkovMatrixTransformer = rbmToMarkovMatrixTransformer;
        this.dotGraphGenerator            = dotGraphGenerator;
    }


    /* **************************  public methods  ************************** */


    /**
     * Writes the CSV- and DOT-files for a given set of Behavior Models.
     *
     * @param behaviorModelsRelative
     *     Behavior Models whose files shall be written.
     * @param outputCsvFilename
     *     base filename of CSV-files to be written; if more than one Behavior
     *     Models are passed, the filename will be indexed with inserted
     *     numbers, starting with 0; for example, <code>output.csv</code> will
     *     be indexed to <code>output0.csv</code>, <code>output1.csv</code>, ...
     * @param outputDotFilename
     *     base filename of DOT-files to be written; if more than one Behavior
     *     Models are passed, the filename will be indexed, analogous to the
     *     <code>outputCsvFilename</code>.
     *
     * @param behaviorMix
     * @param outputCsvFilename
     * @param outputDotFilename
     * @throws SecurityException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NullPointerException
     * @throws ExtractionException
     */
    public void writeOutputFiles (
            final BehaviorMix behaviorMix,
            final String outputCsvFilename,
            final String outputDotFilename)
                    throws SecurityException,
                           IOException,
                           FileNotFoundException,
                           NullPointerException,
                           ExtractionException {

        final List<BehaviorMixEntry> behaviorMixEntries =
                behaviorMix.getEntries();

        final int n = behaviorMixEntries.size();

        final String[][] behaviorMixRows = new String[n][];

        for (int i = 0; i < n; i++) {

            final BehaviorMixEntry behaviorMixEntry = behaviorMixEntries.get(i);

            final BehaviorModelRelative behaviorModelRelative =
                    behaviorMixEntry.getBehaviorModel();

            final String behaviorModelName =
                    behaviorMixEntry.getBehaviorModelName();

            final double relativeFrequency =
                    behaviorMixEntry.getRelativeFrequency();

            final String suffixedCsvFile = (n > 1) ?
                    this.getIndexedCsvFilename(outputCsvFilename, i) :
                    this.getCsvFilename(outputCsvFilename);

            final String suffixedOutputDotFile = (n > 1) ?
                    this.getIndexedDotFilename(outputDotFilename, i) :
                    this.getDotFilename(outputDotFilename);

            final String[][] matrix =
                    this.rbmToMarkovMatrixTransformer.transform(
                            behaviorModelRelative);

            // TODO: store (behaviorModelName, relativeFrequency, suffixedCsvFile) in Behavior Mix;
            this.writeMarkovMatrixToFile(suffixedCsvFile, matrix);
            this.writeDotGraphToFile(suffixedOutputDotFile, matrix);

            behaviorMixRows[i] = new String[]{
                    behaviorModelName,
                    " " + relativeFrequency,
                    " " + suffixedCsvFile
            };
        }

        final String filename = "examples/aida-gear/output/behaviorMix_entries.txt";

        final CSVHandler csvHandler = new CSVHandler(";");
        csvHandler.writeValues(filename, behaviorMixRows);  // FIXME: filename!

        System.out.println(BehaviorModelWriter.INFO_FINISHED);
    }


    /* **************************  private methods  ************************* */


    /**
     * Writes a given matrix to CSV-file.
     *
     * @param filename  name of the file to be written.
     * @param matrix    matrix whose content shall be written to file.
     *
     * @throws FileNotFoundException
     *     if the file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any
     *     other reason.
     * @throws IOException
     *     if an I/O error occurs.
     * @throws SecurityException
     *     if a security manager exists and its checkWrite method denies write
     *     access to the file.
     * @throws NullPointerException
     *     if <code>null</code> is passed as file path.
     */
    private void writeMarkovMatrixToFile (
            final String filename,
            final String[][] matrix)
                    throws FileNotFoundException,
                           SecurityException,
                           NullPointerException,
                           IOException {

        // might throw a FileNotFound-, Security-, NullPointer- or IOException;
        this.rbmToMarkovMatrixTransformer.getMarkovMatrixHandler().
        writeMarkovMatrixToCSVFile(filename, matrix);

        // might throw an IllegalFormat- or NullPointerException (both should
        // never happen here, since template and arguments are valid);
        final String messageWritten = String.format(
                BehaviorModelWriter.INFO_FILE_WRITTEN,
                filename);

        System.out.println(messageWritten);
    }

    /**
     * Writes the DOT graph of a given matrix to file.
     *
     * @param filename  name of the file to be written.
     * @param matrix    matrix whose DOT graph shall be written to file.
     *
     * @throws SecurityException
     *     if writing access to the specified file is denied.
     * @throws IOException
     *     if any I/O error occurs.
     */
    private void writeDotGraphToFile (
            final String filename,
            final String[][] matrix) throws SecurityException, IOException {

        // might throw a Security- or IOException;
        this.dotGraphGenerator.generateDotGraphForMarkovMatrix(
                matrix,
                filename);

        // might throw an IllegalFormat- or NullPointerException (both should
        // never happen here, since template and arguments are valid);
        final String messageWritten = String.format(
                BehaviorModelWriter.INFO_FILE_WRITTEN,
                filename);

        System.out.println(messageWritten);
    }

    /**
     * Appends a ".csv"-suffix to a given base-filename. If the base-filename
     * already ends with ".csv", no new suffix will be appended; for example,
     * base-filename "somefile.csv" will be left unchanged.
     *
     * @param filename  base-name of a file.
     *
     * @return  the filename with suffix ".csv".
     */
    private String getCsvFilename (final String filename) {

        return this.getFilenameWithSuffix(
                filename, BehaviorModelWriter.CSV_FILE_SUFFIX);
    }

    /**
     * Appends a ".dot"-suffix to a given base-filename. If the base-filename
     * already ends with ".dot", no new suffix will be appended; for example,
     * base-filename "somefile.dot" will be left unchanged.
     *
     * @param filename  base-name of a file.
     *
     * @return  the filename with suffix ".dot".
     */
    private String getDotFilename (final String filename) {

        return this.getFilenameWithSuffix(
                filename, BehaviorModelWriter.DOT_FILE_SUFFIX);
    }

    /**
     * Inserts a numeric index between a given base-filename and a
     * ".csv"-suffix; for example, base-filename "somefile" and index 5 will
     * result in "somefile5.csv". If the base-filename already ends with ".csv",
     * no new suffix will be appended; for example, base-filename "somefile.csv"
     * will even result in "somefile5.csv" for index 5.
     *
     * @param filename  base-name of a file.
     * @param index     index to be inserted.
     *
     * @return  the indexed filename.
     */
    private String getIndexedCsvFilename (
            final String filename,
            final int index) {

        return this.getIndexedFilenameWithSuffix(
                filename, index, BehaviorModelWriter.CSV_FILE_SUFFIX);
    }

    /**
     * Inserts a numeric index between a given base-filename and a
     * ".dot"-suffix; for example, base-filename "somefile" and index 5 will
     * result in "somefile5.dot". If the base-filename already ends with ".dot",
     * no new suffix will be appended; for example, base-filename "somefile.dot"
     * will even result in "somefile5.dot" for index 5.
     *
     * @param filename  base-name of a file.
     * @param index     index to be inserted.
     *
     * @return  the indexed filename.
     */
    private String getIndexedDotFilename (
            final String filename,
            final int index) {

        return this.getIndexedFilenameWithSuffix(
                filename, index, BehaviorModelWriter.DOT_FILE_SUFFIX);
    }

    /**
     * Extends a given filename with a specified file-suffix. If the filename
     * already ends with the specified suffix, no new suffix will be appended.
     *
     * @param filename  filename to be extended.
     * @param suffix    file-suffix to be appended.
     *
     * @return  the extended filename.
     */
    private String getFilenameWithSuffix (
            final String filename,
            final String suffix) {

        return filename.toLowerCase().endsWith(suffix) ?
                filename : (filename + suffix);
    }

    /**
     * Inserts a numeric index between a given base-filename and a file-suffix;
     * for example, base-filename "somefile", suffix ".csv" and index 5 will
     * result in "somefile5.csv". If the base-filename already ends with the
     * specified suffix, no new suffix will be appended; for example,
     * base-filename "somefile.csv" will even result in "somefile5.csv" for
     * index 5 and suffix ".csv".
     *
     * @param filename  base-name of a file.
     * @param index     index to be inserted.
     * @param suffix    file-suffix to be appended.
     *
     * @return  the indexed filename.
     */
    private String getIndexedFilenameWithSuffix (
            final String filename,
            final int index,
            final String suffix) {

        String indexedFilename;

        if ( filename.toLowerCase().endsWith(suffix) ) {

            final int endIndex = filename.length() - suffix.length();

            indexedFilename = filename.substring(0, endIndex) + index;

        } else {

            indexedFilename = filename + index;
        }

        return this.getFilenameWithSuffix(indexedFilename, suffix);
    }
}