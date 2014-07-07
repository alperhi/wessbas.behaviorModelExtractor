package net.sf.markov4jmeter.behaviormodelextractor.extraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behaviormodelextractor.DotGraphGenerator;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.RBMToMarkovMatrixTransformer;

/**
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


    public void writeOutputFiles (
            final BehaviorMix behaviorMix,
            final String outputCsvFilename,
            final String outputDotFilename)
                    throws SecurityException,
                           IOException,
                           FileNotFoundException,
                           NullPointerException,
                           ExtractionException {

        final List<BehaviorMixEntry> behaviorMixEntries = behaviorMix.getEntries();

        for (int i = 0, n = behaviorMixEntries.size(); i < n; i++) {

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
        }

        System.out.println(BehaviorModelWriter.INFO_FINISHED);
    }

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
     * @throws SecurityException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NullPointerException
     * @throws ExtractionException
     */
    public void writeOutputFiles (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final String outputCsvFilename,
            final String outputDotFilename)
                    throws SecurityException,
                           IOException,
                           FileNotFoundException,
                           NullPointerException,
                           ExtractionException {

        for (int i = 0, n = behaviorModelsRelative.length; i < n; i++) {

            final BehaviorModelRelative behaviorModelRelative =
                behaviorModelsRelative[i];

            final String suffixedCsvFile = (n > 1) ?
                    this.getIndexedCsvFilename(outputCsvFilename, i) :
                    this.getCsvFilename(outputCsvFilename);

            final String suffixedOutputDotFile = (n > 1) ?
                    this.getIndexedDotFilename(outputDotFilename, i) :
                    this.getDotFilename(outputDotFilename);

            final String[][] matrix =
                    this.rbmToMarkovMatrixTransformer.transform(
                            behaviorModelRelative);

            this.writeMarkovMatrixToFile(suffixedCsvFile, matrix);
            this.writeDotGraphToFile(suffixedOutputDotFile, matrix);
        }

        System.out.println(BehaviorModelWriter.INFO_FINISHED);
    }

    /**
     * Writes the CSV- and DOT-files for a given Behavior Model.
     *
     * @param behaviorModelRelative
     *     Behavior Model whose files shall be written.
     * @param outputCsvFilename
     *     filename of the CSV-file to be written.
     * @param outputDotFilename
     *     filename of the DOT-file to be written.
     *
     * @throws SecurityException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws NullPointerException
     * @throws ExtractionException
     */
    public void writeOutputFiles (
            final BehaviorModelRelative behaviorModelRelative,
            final String outputCsvFilename,
            final String outputDotFilename)
                    throws SecurityException,
                           IOException,
                           FileNotFoundException,
                           NullPointerException,
                           ExtractionException {

        this.writeOutputFiles(
                new BehaviorModelRelative[]{ behaviorModelRelative },
                outputCsvFilename,
                outputDotFilename);
    }


    /* **************************  private methods  ************************* */


    /**
     *
     * @param filename
     * @param matrix
     * @throws FileNotFoundException
     * @throws SecurityException
     * @throws NullPointerException
     * @throws IOException
     */
    private void writeMarkovMatrixToFile (
            final String filename,
            final String[][] matrix) throws FileNotFoundException, SecurityException, NullPointerException, IOException {

        this.rbmToMarkovMatrixTransformer.getMarkovMatrixHandler().
        writeMarkovMatrixToCSVFile(filename, matrix);

        final String messageWritten = String.format(
                BehaviorModelWriter.INFO_FILE_WRITTEN,
                filename);

        System.out.println(messageWritten);
    }

    /**
     *
     * @param filename
     * @param matrix
     * @throws FileNotFoundException
     * @throws SecurityException
     * @throws NullPointerException
     * @throws IOException
     */
    private void writeDotGraphToFile (
            final String filename,
            final String[][] matrix) throws FileNotFoundException, SecurityException, NullPointerException, IOException {

        this.dotGraphGenerator.generateDotGraphForMarkovMatrix(
                matrix,  // TODO: matrix for model 5 is invalid! (model is correct)
                filename);

        final String messageWritten = String.format(
                BehaviorModelWriter.INFO_FILE_WRITTEN,
                filename);

        System.out.println(messageWritten);
    }

    /**
     *
     * @param filename
     * @return
     */
    private String getCsvFilename (final String filename) {

        return this.getFilenameWithSuffix(
                filename, BehaviorModelWriter.CSV_FILE_SUFFIX);
    }

    /**
     *
     * @param filename
     * @return
     */
    private String getDotFilename (final String filename) {

        return this.getFilenameWithSuffix(
                filename, BehaviorModelWriter.DOT_FILE_SUFFIX);
    }

    /**
     *
     * @param filename
     * @param index
     * @return
     */
    private String getIndexedCsvFilename (
            final String filename,
            final int index) {

        return this.getIndexedFilenameWithSuffix(
                filename, index, BehaviorModelWriter.CSV_FILE_SUFFIX);
    }

    /**
     *
     * @param filename
     * @param index
     * @return
     */
    private String getIndexedDotFilename (
            final String filename,
            final int index) {

        return this.getIndexedFilenameWithSuffix(
                filename, index, BehaviorModelWriter.DOT_FILE_SUFFIX);
    }

    /**
     *
     * @param filename
     * @param suffix
     * @return
     */
    private String getFilenameWithSuffix (
            final String filename,
            final String suffix) {

        return filename.toLowerCase().endsWith(suffix) ?
                filename : (filename + suffix);
    }

    /**
     *
     * @param filename
     * @param index
     * @param suffix
     * @return
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