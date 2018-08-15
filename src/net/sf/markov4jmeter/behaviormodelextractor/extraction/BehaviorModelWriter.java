/***************************************************************************
 * Copyright (c) 2016 the WESSBAS project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


package net.sf.markov4jmeter.behaviormodelextractor.extraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.RBMToMarkovMatrixTransformer;
import net.sf.markov4jmeter.behaviormodelextractor.util.CSVHandler;

/**
 * Class for writing output files for Behavior Models, Behavior Mix, use cases
 * and DOT graphs. Four types of output files will be written by the
 * <code>writeOutputFiles()</code> methods of this class:
 * <ul>
 *   <li>CSV-file (Markov matrix) for each Behavior Model.
 *   </li>
 *   <li>DOT-file (graph visualization) for each Behavior Model.
 *   </li>
 *   <li>TXT-file (Behavior Mix) which includes a name/frequency/filename
 *       row for each Behavior Model.
 *   </li>
 *   <li>TXT-file which contains a list of all detected use cases.
 *   </li>
 * </ul>
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class BehaviorModelWriter {


    /* *****************************  constants  **************************** */


    /** Name of the output file for Behavior Models. */
    private final static String FILENAME_BEHAVIOR_MODELS = "behaviormodel.csv";

    /** Name of the output file for DOT graphs. */
    private final static String FILENAME_GRAPHS = "graph.dot";

    /** Name of the output file for Behavior Mix. */
    private final static String FILENAME_BEHAVIOR_MIX = "behaviormix.txt";

    /** Name of the output file for use cases. */
    private final static String FILENAME_USE_CASES = "usecases.txt";

    /** Error message for the case that a directory does not exist. */
    private final static String ERROR_DIRECTORY_DOES_NOT_EXIST =
            "path \"%s\" does not denote an existing directory";

    /** Error message for the case that a file does not denote a directory. */
    private final static String ERROR_FILE_IS_NOT_A_DIRECTORY =
            "file \"%s\" does not denote a directory";

    /** Information message for the case that a file has been written. */
    private final static String INFO_FILE_WRITTEN =
            "File \"%s\" written successfully.";

    /** Suffix of DOT-files. */
    private final static String DOT_FILE_SUFFIX = ".dot";

    /** Suffix of CSV-files. */
    private final static String CSV_FILE_SUFFIX = ".csv";

    /** Suffix of TXT-files. */
    private final static String TXT_FILE_SUFFIX = ".txt";


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
     * Writes the CSV-, DOT- and TXT-files for the components that are provided
     * by a given Behavior Mix. Four types of output files will be written:
     * <ul>
     *   <li>CSV-file (Markov matrix) for each Behavior Model.
     *   </li>
     *   <li>DOT-file (graph visualization) for each Behavior Model.
     *   </li>
     *   <li>TXT-file (Behavior Mix) which includes a name/frequency/filename
     *       row for each Behavior Model.
     *   </li>
     *   <li>TXT-file which contains a list of all detected use cases.
     *   </li>
     * </ul>
     *
     * @param behaviorMix
     *     Behavior Mix which provides the information to be written to files.
     * @param outputDirectory
     *     output directory where the files will be written into.
     *
     * @throws FileNotFoundException
     *     if any file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any
     *     other reason.
     * @throws IOException
     *     if any I/O error occurs.
     * @throws SecurityException
     *     if writing access to any file is denied.
     * @throws NullPointerException
     *     if <code>null</code> is passed as output directory.
     * @throws ExtractionException
     *     if the output directory does not exist, or if a file of same name
     *     exists, but does not denote a directory.
     */
    public void writeOutputFiles (
            final BehaviorMix behaviorMix,
            final String outputDirectory) throws SecurityException,
                                                 IOException,
                                                 FileNotFoundException,
                                                 NullPointerException,
                                                 ExtractionException {

        // might throw a NullPointerException;
        final File outputDirectoryFile = new File(outputDirectory);

        // might throw a SecurityException;
        if ( !outputDirectoryFile.exists() ) {

            // might throw IllegalFormat- or NullPointerException (both should
            // never happen here, since template and arguments are valid);
            final String message = String.format(
                    BehaviorModelWriter.ERROR_DIRECTORY_DOES_NOT_EXIST,
                    outputDirectory);

            throw new ExtractionException(message);
        }

        if ( !outputDirectoryFile.isDirectory() ) {

            // might throw IllegalFormat- or NullPointerException (both should
            // never happen here, since template and arguments are valid);
            final String message = String.format(
                    BehaviorModelWriter.ERROR_FILE_IS_NOT_A_DIRECTORY,
                    outputDirectory);

            throw new ExtractionException(message);
        }

        // might throw a NullPointerException (should never happen here);
        final String filenameBehaviorModels = new File(
                outputDirectory,
                BehaviorModelWriter.FILENAME_BEHAVIOR_MODELS).getAbsolutePath();

        // might throw a NullPointerException (should never happen here);
        final String filenameGraphs =  new File(
                outputDirectory,
                BehaviorModelWriter.FILENAME_GRAPHS).getAbsolutePath();

        // might throw a NullPointerException (should never happen here);
        final String filenameBehaviorMix = new File(
                outputDirectory,
                BehaviorModelWriter.FILENAME_BEHAVIOR_MIX).getAbsolutePath();

        // might throw a NullPointerException (should never happen here);
        final String filenameUseCases = new File(
                outputDirectory,
                BehaviorModelWriter.FILENAME_USE_CASES).getAbsolutePath();

        // might throw a FileNotFound-, IO-, Security- or NullPointerException;
        this.writeOutputFiles(
                behaviorMix,
                filenameBehaviorModels,
                filenameGraphs,
                filenameBehaviorMix,
                filenameUseCases,
                outputDirectory);
    }

    /**
     * Writes the CSV-, DOT- and TXT-files for the components that are provided
     * by a given Behavior Mix. Four types of output files will be written:
     * <ul>
     *   <li>CSV-file (Markov matrix) for each Behavior Model.
     *   </li>
     *   <li>DOT-file (graph visualization) for each Behavior Model.
     *   </li>
     *   <li>TXT-file (Behavior Mix) which includes a name/frequency/filename
     *       row for each Behavior Model.
     *   </li>
     *   <li>TXT-file which contains a list of all detected use cases.
     *   </li>
     * </ul>
     *
     * @param behaviorMix
     *     Behavior Mix which provides the information to be written to files.
     * @param filenameBehaviorModels
     *     base filename of CSV-files to be written for the Behavior Models; if
     *     more than one Behavior Models exist, the filename will be indexed
     *     with inserted numbers, starting with zero; for example,
     *     <code>output.csv</code> will be indexed to
     *     <code>output0.csv</code>, <code>output1.csv</code>, ...
     * @param filenameGraphs
     *     base filename of DOT-files (graph visualizations) to be written for
     *     the Behavior Models; if more than one Behavior Models exist, the
     *     filename will be indexed, analogous to
     *     <code>filenameBehaviorModels</code>.
     * @param filenameBehaviorMix
     *     name of the TXT-file to be written for the Behavior Mix.
     * @param filenameUseCases
     *     name of the TXT-file to be written for the use cases.
     *
     * @throws FileNotFoundException
     *     if any file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any
     *     other reason.
     * @throws IOException
     *     if any I/O error occurs.
     * @throws SecurityException
     *     if writing access to any file is denied.
     * @throws NullPointerException
     *     if <code>null</code> is passed for any filename.
     */
    public void writeOutputFiles (
            final BehaviorMix behaviorMix,
            final String filenameBehaviorModels,
            final String filenameGraphs,
            final String filenameBehaviorMix,
            final String filenameUseCases,
            final String outputDirectory) throws SecurityException,
                                                  IOException,
                                                  FileNotFoundException,
                                                  NullPointerException {

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
                    this.getIndexedCsvFilename(filenameBehaviorModels, i) :
                    this.getCsvFilename(filenameBehaviorModels);
                    
            final String suffixedOutputCsvFile = (n > 1) ?
                    this.getIndexedCsvFilename(outputDirectory + "\\" + behaviorModelName, i) :
                    this.getCsvFilename(outputDirectory + behaviorModelName);

            final String suffixedOutputDotFile = (n > 1) ?
                    this.getIndexedDotFilename(filenameGraphs, i) :
                    this.getDotFilename(filenameGraphs);

            final String[][] matrix =
                    this.rbmToMarkovMatrixTransformer.transform(
                            behaviorModelRelative);

            // might throw a FileNotFound-, IO-, Security- or
            // NullPointerException;
            this.writeMarkovMatrixToFile(suffixedCsvFile, matrix);

            // might throw a Security- or IOException;
            this.writeDotGraphToFile(suffixedOutputDotFile, matrix);

            behaviorMixRows[i] = new String[]{
                    ((i == 0) ? "behaviorModels = " : "") + behaviorModelName + i,
                    " " + suffixedOutputCsvFile.replace("\\", "/"), 
                    " " + relativeFrequency,
                    " " + suffixedCsvFile.replace("\\", "/") + ( (n > 1 && i != (n-1)) ? ", \\" : "")
            };
        }

        // might throw a FileNotFound-, IO-, Security- or NullPointerException;
        this.writeBehaviorMixToFile(
                this.getFilenameWithSuffix(
                        filenameBehaviorMix,
                        BehaviorModelWriter.TXT_FILE_SUFFIX), behaviorMixRows);

        // might throw a FileNotFound-, IO-, Security- or NullPointerException;
        this.writeUseCases(
                this.getFilenameWithSuffix(
                        filenameUseCases,
                        BehaviorModelWriter.TXT_FILE_SUFFIX), behaviorMix);

    }


    /* **************************  private methods  ************************* */


    /**
     * Writes the Behavior Mix entries indicated by a given Behavior Mix to a
     * specified file.
     *
     * @param filename
     *     name of the file to be written.
     * @param behaviorMixRows
     *     entries of the Behavior Mix to be stored in the file.
     *
     * @throws FileNotFoundException
     *      if the file exists but is a directory rather than a regular file,
     *      does not exist but cannot be created, or cannot be opened for any
     *      other reason.
     * @throws IOException
     *     if any I/O error occurs.
     * @throws SecurityException
     *     if a security manager exists and its <code>checkWrite</code> method
     *     denies write access to the file.
     * @throws NullPointerException
     *     if <code>null</code> is passed as filename.
     */
    private void writeBehaviorMixToFile (
            final String filename,
            final String[][] behaviorMixRows)
                    throws FileNotFoundException,
                           SecurityException,
                           NullPointerException,
                           IOException {

        final CSVHandler csvHandler = new CSVHandler(";");

        // might throw a FileNotFound-, IO-, Security- or NullPointerException;
        
        csvHandler.writeValues(filename, behaviorMixRows);

        this.printFileWrittenInfo(filename);
    }

    /**
     * Writes the use cases that are provided by a given Behavior Mix to a
     * specified file.
     *
     * <p><u>Preconditions:</u>
     * <ul>
     *   <li> all Behavior Models of the given Behavior Mix build on a common
     *        set of use cases;
     *   </li>
     *   <li> use case names must not include tabulators.
     *   </li>
     * </ul>
     *
     * @param filename
     *     name of the file to be written.
     * @param behaviorMix
     *     Behavior Mix that provides the use cases to be stored in the file.
     *
     * @throws FileNotFoundException
     *      if the file exists but is a directory rather than a regular file,
     *      does not exist but cannot be created, or cannot be opened for any
     *      other reason.
     * @throws IOException
     *     if any I/O error occurs.
     * @throws SecurityException
     *     if a security manager exists and its <code>checkWrite</code> method
     *     denies write access to the file.
     * @throws NullPointerException
     *     if <code>null</code> is passed as filename.
     */
    private void writeUseCases (
            final String filename,
            final BehaviorMix behaviorMix)
                    throws FileNotFoundException,
                           SecurityException,
                           NullPointerException,
                           IOException {

        // to be returned (as array);
        final ArrayList<String[]> useCases = new ArrayList<String[]>();

        // it is assumed that use case names do not include a tabulator.
        final CSVHandler csvHandler = new CSVHandler("\t");

        final List<BehaviorMixEntry> behaviorMixEntries =
                behaviorMix.getEntries();

        // it is assumed that all Behavior Models build on a common set of
        // use cases  ->  first Behavior Model will be considered only;
        if (behaviorMixEntries.size() > 0) {

            final List<Vertex> vertices =
                    behaviorMixEntries.get(0).getBehaviorModel().getVertices();

            final Iterator<Vertex> iterator = vertices.iterator();

            while ( iterator.hasNext() ) {

                final UseCase useCase = iterator.next().getUseCase();

                if (useCase != null) {

                    useCases.add(new String[]{ useCase.getName() });
                }
            }
        }

        // might throw a FileNotFound-, IO-, Security- or NullPointerException;
        csvHandler.writeValues(filename, useCases.toArray( new String[][]{} ));

        this.printFileWrittenInfo(filename);
    }

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
     *     if any I/O error occurs.
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

        this.printFileWrittenInfo(filename);
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

        this.printFileWrittenInfo(filename);
    }

    /**
     * Prints an informational message to the standard output stream, for the
     * case that a file has been written (successfully).
     *
     * @param filename  name of the written file.
     */
    private void printFileWrittenInfo (final String filename) {

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
     * @param filename  base name of a file.
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
     * @param filename  base name of a file.
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
     * @param filename  base name of a file.
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
     * @param filename  base name of a file.
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
     * @param filename  base name of a file.
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
