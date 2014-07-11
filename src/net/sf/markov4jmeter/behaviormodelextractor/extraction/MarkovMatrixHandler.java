/***************************************************************************
 * Copyright 2012 by
 *  Christian-Albrechts-University of Kiel, 24098 Kiel, Germany
 *    + Department of Computer Science
 *     + Software Engineering Group
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.markov4jmeter.behaviormodelextractor.util.CSVHandler;


/**
 * Handler for reading/writing Markov mxn-matrices from/to CSV files.
 * Furthermore, this handler provides helping methods for printing, setting
 * certain matrix values etc.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.1
 */
public class MarkovMatrixHandler {


    /* *****************************  constants  **************************** */


    /** Error message for the case that a matrix value could not be set. */
    private final static String ERROR_MATRIX_VALUE_SETTING_FAILED =
            "matrix value could not be set to (\"%s\", \"%s\")";

    /** Error message for the case that a matrix has an inconsistent number
     *  of columns. */
    private final static String ERROR_INCONSISTENT_NUMBER_OF_MATRIX_COLUMNS =
            "matrix in file \"%s\" has an inconsistent number of columns";


    /* *************************  global variables  ************************* */


    /** Name of the final state, generally defined as "<code>$</code>". */
    private final String finalStateName;

    /** Instance for reading and writing CSV-files. */
    private final CSVHandler csvHandler;

    /** Think time default value, e.g. <code>"n(0 0)"</code>. */
    private final String thinkTimeDefaultValue;


    /* ***************************  constructors  *************************** */


    /**
     * Constructor for a Markov Matrix Handler.
     *
     * @param finalStateName
     *     name of the final state, generally defined as "<code>$</code>".
     * @param csvHandler
     *     instance for reading and writing CSV-files.
     * @param thinkTimeDefaultValue
     *     think time default value, e.g. <code>"n(0 0)"</code>.
     */
    public MarkovMatrixHandler (
            final String finalStateName,
            final CSVHandler csvHandler,
            final String thinkTimeDefaultValue) {

        this.finalStateName        = finalStateName;
        this.csvHandler            = csvHandler;
        this.thinkTimeDefaultValue = thinkTimeDefaultValue;
    }


    /* **************************  public methods  ************************** */


    /**
     * Prints a given mxn-matrix on the standard output.
     *
     * @param matrix  matrix to be printed.
     */
    public void print (final String[][] matrix) {

        if (matrix == null) {

            System.out.println("null");

        } else {

            for (int i = 0, m = matrix.length; i < m; i++) {

                final String[] row = matrix[i];

                for (int j = 0, n = row.length; j < n; j++) {

                    System.out.print(row[j]);

                    if (j < n - 1) {

                        System.out.print( this.csvHandler.getSeparator() );

                    } else {

                        System.out.println();
                    }
                }
            }
        }
    }

    /**
     * Collects the states of a template matrix; the final state (generally
     * denoted as <code>"$"</code>) will not be included to the result list.
     *
     * @param templateFilename
     *     filename of the template which provides the states.
     *
     * @return
     *     the list which includes all template states, except the final state.
     *
     * @throws FileNotFoundException
     *     in case the denoted file does not exist.
     * @throws NullPointerException
     *     if <code>null</code> is passed as file path.
     * @throws IOException
     *     if any reading error occurs.
     * @throws ExtractionException
     *     if the number of matrix columns is inconsistent.
     */
    public String[] collectStatesFromCSVFile (final String templateFilename)
            throws FileNotFoundException,
                   NullPointerException,
                   IOException,
                   ExtractionException {

        final ArrayList<String> states = new ArrayList<String>();

        // might throw FileNotFound-, NullPointer-, IO- or ExtractionException;
        final String[][] matrix =
                this.readMarkovMatrixFromCSVFile(templateFilename);

        for (int i = 1, n = matrix[0].length; i < n; i++) {

            final String state = matrix[0][i];

            if ( this.finalStateName.equals(state) ) {

                // ignore final state, a dedicated state will be added later;
                continue;

            } else {

                states.add(state);
            }
        }

        return states.toArray( new String[]{} );
    }

    /**
     * Creates a new matrix for a given list of states.
     *
     * @param states
     *     the states which span the new matrix; the exit state must be included
     *     as the <i>final</i> element of this list.
     *
     * @return
     *     the newly created matrix.
     */
    public String[][] createEmptyMarkovMatrixForStates (
            final List<String> states) {

        final String[][] matrix;  // to be returned;

        // note that "$" is already included in "vertices";
        // (the use case assigned to that vertex is null);
        final int m = states.size();

        if (m == 0) {

            // if no states are available, the newly created matrix will contain
            // the exit state only.
            matrix = new String[][]{ {this.finalStateName} };

        } else {

            final int n = m + 1;  // 1 additional column for header column;

            matrix = new String[m][n];

            // ----  fill header row with names  ----;

            matrix[0][0] = "";  // first column is header column;

            for (int j = 1; j < n; j++) {

                matrix[0][j] = states.get(j - 1);
            }

            // ----  fill header column with names  ----;

            for (int i = 1; i < m; i++) {

                // first column contains header name;
                matrix[i][0] = states.get(i - 1);
            }

            // ----  fill remaining entries with default values  ----;

            this.resetMatrix(matrix);
        }

        return matrix;
    }

    /**
     * Resets a given matrix; all transition probabilities will be set to 0.0,
     * except the values of the final-state column, which will be set to 1.0;
     * the think times will be initialized with the default value that has been
     * passed to the constructor.
     *
     * @param matrix  matrix to be reset.
     */
    public void resetMatrix (final String[][] matrix) {

        for (int i = 1, m = matrix.length; i < m; i++) {

            for (int j = 1, n = matrix[0].length; j < n; j++) {

                matrix[i][j] = "0.0; " + this.thinkTimeDefaultValue;
            }

            final String columnHeaderValue = matrix[i][0];

            this.setValueAtCell(
                    "1.0; " + this.thinkTimeDefaultValue,
                    columnHeaderValue,
                    this.finalStateName,
                    matrix);
        }
    }

    /**
     * Sets a value at a certain position in a given mxn-matrix.
     *
     * <p><u>Precondition:</u>
     * it is assumed that the matrix contains identifiers in the first row as
     * well as in the first column.
     *
     * @param value
     *     value to be set.
     * @param rowHeaderValue
     *     row value which specifies the horizontal position index.
     * @param columnHeaderValue
     *     column value which specifies the vertical position index.
     * @param matrix
     *     matrix to be modified.
     *
     * @throws IllegalArgumentException
     *     in case the value could not be set at the specified position.
     */
    public void setValueAtCell (
            final String value,
            final String rowHeaderValue,
            final String columnHeaderValue,
            final String matrix[][]) throws IllegalArgumentException {

        for (int i = 0, m = matrix.length; i < m; i++) {

            for (int j = 0, n = matrix[i].length;
                 j < n && matrix[i][0].equals(rowHeaderValue);
                 j++) {

                if ( matrix[0][j].equals(columnHeaderValue) ) {

                    matrix[i][j] = value;
                    return;
                }
            }
        }

        final String message = String.format(
                MarkovMatrixHandler.ERROR_MATRIX_VALUE_SETTING_FAILED,
                rowHeaderValue,
                columnHeaderValue);

        throw new IllegalArgumentException(message);
    }

    /**
     * Reads the content of a CSV file as mxn-matrix.
     *
     * <p><u>Precondition:</u>
     * it is assumed that the CSV file contains a matrix with a consistent
     * number of columns.
     *
     * @param filePath
     *     path to the CSV file to be read.
     *
     * @return
     *     an mxn-matrix representing the content of the given CSV file.
     *
     * @throws FileNotFoundException
     *     in case the denoted file does not exist.
     * @throws IOException
     *     if any reading error occurs.
     * @throws NullPointerException
     *     if <code>null</code> is passed as file path.
     * @throws ExtractionException
     *     if the number of matrix columns is inconsistent.
     */
    public String[][] readMarkovMatrixFromCSVFile (final String filePath)
            throws IOException,
                   FileNotFoundException,
                   NullPointerException,
                   ExtractionException {

        // might throw a FileNotFound-, IO- or NullPointerException;
        final String[][] matrix = this.csvHandler.readValues(filePath);

        final int m = matrix.length;

        if (m > 0) {

            for (int i = 1, n = matrix[0].length; i < m; i++) {

                if (matrix[i].length != n) {

                    final String message = String.format(
                            MarkovMatrixHandler.
                            ERROR_INCONSISTENT_NUMBER_OF_MATRIX_COLUMNS,
                            filePath);

                    throw new ExtractionException(message);
                }
            }
        }

        return matrix;
    }

    /**
     * Writes the content of a Markov matrix to the specified output file.
     *
     * @param filePath
     *     Path to the file to be written.
     * @param matrix
     *     Matrix to be written.
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
    public void writeMarkovMatrixToCSVFile (
            final String filePath,
            final String[][] matrix) throws FileNotFoundException,
                                            IOException,
                                            SecurityException,
                                            NullPointerException {

        // might throw FileNotFound-, IO-, Security- or NullPointerException;
        this.csvHandler.writeValues(filePath, matrix);
    }

    /**
     * Returns the used think time default value.
     *
     * @return
     *     the value that has been passed to the constructor,
     *     e.g. <code>"n(0 0)"</code>.
     */
    public String getDefaultThinkTimeValue () {

        return this.thinkTimeDefaultValue;
    }

    /**
     * Returns the instance which is used for reading and writing CSV-files.
     *
     * @return  a valid instance of {@link CSVHandler}.
     */
    public CSVHandler getCsvHandler () {

        return this.csvHandler;
    }

    /**
     * Returns the name of the final state.
     *
     * @return  name of the final state, generally defined as "<code>$</code>".
     */
    public String getFinalStateName () {

        return this.finalStateName;
    }
}
