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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class generates a "dot" graph for Graphviz with nodes and transitions
 * taken from a given matrix.
 * <p>The matrix needs to contain the node names to be listed in the first row
 * ("header row") as well as in the first column ("header column"), both in the
 * same order. Each (i, j)-entry of all other matrix rows/columns except (0, 0)
 * will be interpreted as a transition from node "i" to node "j", with the
 * entry's value as label.
 *
 * <p><u>Example:</u>
 * For node names a, b and c and labels 1, 2, 3 and 4, the matrix
 * <p><table border=1>
 *   <tr><td>        </td><td><b>a</b></td> <td><b>b</b></td><td><b>c</b></td>
 *   <tr><td><b>a</b></td><td>   1    </td> <td>        </td><td>        </td>
 *   <tr><td><b>b</b></td><td>   2    </td> <td>        </td><td>   3    </td>
 *   <tr><td><b>c</b></td><td>        </td> <td>   4    </td><td>        </td>
 * </table>
 * <p>defines the following transitions:
 * <ul>
 *   <li> a -1-> a
 *   <li> b -2-> a
 *   <li> b -3-> c
 *   <li> c -4-> b
 * </ul>
 * Note that the value of the entry (0, 0) is always irrelevant.
 *
 * @see <a href="http://www.graphviz.org/">http://www.graphviz.org/</a>
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.0 (2012-12-15)
 */
public class DotGraphGenerator {

    /** Type constant for Windows-specific newline patterns
     *  (<code>"\r\n"</code>) */
    public final static int LINEBREAK_WINDOWS = 0;

    /** Type constant for Unix-specific newline patterns
     *  (<code>"\n"</code>) */
    public final static int LINEBREAK_UNIX = 1;

    /** Type constant for MacOS-specific newline patterns
     *  (<code>"\r"</code>) */
    public final static int LINEBREAK_MAC = 2;

    /** Regular expression specifying the separator for parameters. */
    private final static String PARAMETER_SEPARATOR = "\\s+";

    /** Error/warning message for occurrence of invalid matrix values. */
    private final static String ERROR_INVALID_MATRIX_VALUE =
            "Invalid matrix value in (%d, %d): \"%s\"";

    /** Format to be used for any numbers contained in labels. */
    private static NumberFormat NUMBER_FORMAT;

    static {

        DotGraphGenerator.NUMBER_FORMAT = NumberFormat.getInstance(Locale.UK);
        DotGraphGenerator.NUMBER_FORMAT.setMaximumFractionDigits(4);
    }

    /** OS-specific newline pattern to be used for a line-break. */
    private final String linebreakPattern;


    /**
     * Constructor for a <code>DotGraphGenerator</code> with a specific type of
     * newlines to be used for output content.
     *
     * @param linebreakType
     *     One of the <code>LINEBREAK</code>-constants of this class.
     */
    public DotGraphGenerator (final int linebreakType) {

        this.linebreakPattern = this.getLinebreakPattern(linebreakType);
    }


    /**
     * Returns the OS-related newline pattern for the given type.
     *
     * @param linebreakType
     *     One of the <code>LINEBREAK</code>-constants of this class.
     * @return
     *     The OS-related newline pattern for the given type.
     */
    private String getLinebreakPattern (final int linebreakType) {

        switch (linebreakType) {

            case LINEBREAK_WINDOWS :
                return "\r\n";

            case LINEBREAK_UNIX :
                return "\n";

            case LINEBREAK_MAC :
                return "\r";

            default:
                return "\r\n";  // use Windows newlines by default;
        }
    }

    /**
     * Generates a graph consisting of the nodes and transitions defined by
     * the given matrix. The graph will be stored in the given file path.
     * In case the file at the specified path does not exist, it will be
     * created; if it already exists, its content will be overwritten.
     *
     * @param matrix
     *     Matrix which defines the nodes and transitions of the graph.
     * @param filePath
     *     Path of the file to be (created and) written.
     *
     * @throws SecurityException
     *     If a security manager exists and its <code>checkWrite</code> method
     *     denies write access to the file.
     * @throws IOException
     *     If an I/O error occurs.
     */
    public void generateDotGraphForMarkovMatrix (
            final String[][] matrix,
            final String filePath) throws SecurityException, IOException {

        final String[] nodes = this.readNodesFromMatrix(matrix);
        final Transition[] transitions = this.readTransitionsFromMatrix(matrix);

        // might throw a Security- or IOException;
        this.writeGraphToFile(nodes, transitions, filePath);
    }

    /**
     * Collects all graph nodes from a given matrix.
     *
     * @param matrix
     *     Matrix which contains the graph nodes to be collected.
     *
     * @return
     *     All graph nodes contained in the given matrix.
     */
    private String[] readNodesFromMatrix (final String[][] matrix) {

        final int m = matrix.length;
        final String[] nodes = new String[m - 1];

        for (int i = m - 1; i > 0; i--) {

            nodes[i - 1] = matrix[i][0];
        }
        return nodes;
    }

    /**
     * Collects all graph transitions from a given matrix. Matrix entries with
     * 0-values will be ignored.
     *
     * @param matrix
     *     Matrix which contains the graph transitions to be collected.
     *
     * @return
     *     All graph transitions contained in the given matrix.
     */
    private Transition[] readTransitionsFromMatrix (final String[][] matrix) {

        final ArrayList<Transition> transitions = new ArrayList<Transition>();

        final int m = matrix.length;
        final int n = matrix[0].length;

        for (int i = 1; i < m; i++) {  // first row is header (use case names);

            for (int j = 1; j < n; j++) {  // first column is header (UC names);

                final Value value = this.parseValue(matrix, i, j);

                final double probabilty = value.probability;

                if (probabilty != 0) {

                    final String srcNode = matrix[i][0];
                    final String dstNode = matrix[0][j];

                    final String label =
                            DotGraphGenerator.NUMBER_FORMAT.format(probabilty)
                            + ((value.thinkTime != null) ? ("; " + value.thinkTime) : "");

                    final Transition transition =
                            new Transition(srcNode, label, dstNode);

                    transitions.add(transition);
                }
            }
        }
        return transitions.toArray( new Transition[]{} );
    }

    /**
     * Parses the entry at the given (i, j) position of a given matrix as a
     * <code>double</code> value. In case the value is invalid (that is, it
     * cannot be parsed as a <code>double</code> value), 0 will be returned and
     * a warning message will be given.
     *
     * @param matrix
     *     Matrix which contains the value to be parsed.
     * @param i
     *     Matrix row index of the entry to be parsed.
     * @param j
     *     Matrix column index of the entry to be parsed.
     *
     * @return
     *     The parsed matrix entry as a <code>double</code> value.
     */
    private Value parseValue (final String[][] matrix, final int i, int j) {

        final String str = matrix[i][j].trim();
        Value value;

        try {

            // might throw a PatternSyntaxException (should never happen);
            final String[] tokens = str.split("\\s*;\\s*");

            // might throw a NullPointer- or NumberFormatException
            // (NullPointerException should never happen here);
            final double probability = Double.parseDouble( tokens[0] );
            final String thinkTime = !tokens[1].equals("n(0 0)") ? tokens[1] : null;

            value = new Value(probability, thinkTime);

        } catch (final NumberFormatException
                     | ArrayIndexOutOfBoundsException ex) {

            value = new Value(
                    0.0d,  // this makes the value to be ignored in the graph;
                    null);

            // print a warning message;
            final String message = String.format(
                    DotGraphGenerator.ERROR_INVALID_MATRIX_VALUE,
                    i, j, str);

            System.err.println(message);
        }

        return value;
    }

    private class Value {

        final double probability;
        final String thinkTime;

        public Value (final double probability, final String thinkTime) {

            this.probability = probability;
            this.thinkTime = thinkTime;
        }
    }

    /**
     * Parses a given <code>String</code> which specifies a think time
     * according to a certain distribution.
     *
     * @param str
     *     <code>String</code> which provides a distribution function
     *     descriptor and the required parameters as well. The leading
     *     function descriptor indicates the distribution type and therewith
     *     the type of {@link ThinkTime} to be returned. The passed
     *     <code>String</code> might be wrapped into whitespace, which will
     *     be removed before parsing starts.
     *
     * @return
     *     a valid instance of a {@link ThinkTime} sub-class, or
     *     <code>null</code> if parsing fails for any reason.
     */
    public boolean isThinkTime (String str) {

        boolean isThinkTime = true;  // to be returned;

        if (str == null) {

            isThinkTime = false;

        } else {

            str = str.trim();

            // ensure that at least one leading character exists prior the
            // first opening bracket; closing bracket must be at last
            // position;
            if ( str.indexOf('(') > 0 &&
                 str.lastIndexOf(')') == str.length() - 1) {

                isThinkTime &= this.hasValidFunctionDescriptor(str);

                isThinkTime &=  this.hasValidParameters(str);
            }
        }

        return isThinkTime;
    }

    /**
     * TODO: comments;
     * Extracts the function descriptor which indicates the distribution
     * type.
     *
     * @param str
     *     <code>String</code> which specifies the distribution type as well
     *     as required parameters; it must contain at least an opening
     *     bracket.
     *
     * @return
     *     a valid <code>String</code> instance, or <code>null</code> if no
     *     function descriptor can be found.
     */
    private boolean hasValidFunctionDescriptor (final String str) {

        final String functionDecriptor = str.split("\\(")[0].trim();

        // TODO: generic regular expression for function descriptor;
        return true;
    }

    /**
     * TODO: comments;
     * Extracts the parameters which are required by the regarding
     * distribution type. The parameters will be returned as (unparsed)
     * <code>String</code>s.
     *
     * @param function
     *     <code>String</code> which specifies the distribution type as well
     *     as required parameters.
     *
     * @return
     *     a valid array of <code>String</code> instances; each entry
     *     denotes an unparsed parameter.
     */
    private boolean hasValidParameters (final String function) {

        final String str = function.substring(
                function.indexOf('(') + 1,
                function.length() - 1).trim();

        final String[] parameters =
                str.split(DotGraphGenerator.PARAMETER_SEPARATOR);

        // TODO: generic test for parameter sequence;
        return true;
    }

    /**
     * Writes a graph to a specified file. In case the file does not exist, it
     * will be created; if it already exists, its content will be overwritten.
     *
     * @param nodes
     *     Nodes of the graph to be written to file.
     * @param transitions
     *     Transitions of the graph to be written to file.
     * @param filePath
     *     Path of the file to be (created and) written.
     *
     * @throws SecurityException
     *     If a security manager exists and its <code>checkWrite</code> method
     *     denies write access to the file.
     * @throws IOException
     *     If an I/O error occurs.
     */
    private void writeGraphToFile (
            final String[] nodes,
            final Transition[] transitions,
            final String filePath) throws SecurityException, IOException {

        // FileOutputStream constructor might throw a FileNotFoundException or
        // a SecurityException;
        final FileOutputStream fos = new FileOutputStream(filePath);
        final OutputStreamWriter osw = new OutputStreamWriter(fos);
        final BufferedWriter bufferedWriter = new BufferedWriter(osw);

        try {
            // might throw an IOException;
            this.writeLineToFile("digraph G {", bufferedWriter);

            for (final String node: nodes) {

                // might throw an IOException;
                this.writeNodeToFile(node, bufferedWriter);
            }
            for (final Transition transition : transitions) {

                    // might throw an IOException;
            		this.writeTransitionToFile(transition, bufferedWriter);
            }
            // might throw an IOException;
            this.writeLineToFile("}", bufferedWriter);

        } finally {

            bufferedWriter.close();  // might throw an IOException;
        }
    }

    /**
     * Writes a single node to an output stream, which is given by an
     * instance of <code>BufferedInputWriter</code>.
     *
     * @param node
     *     Transition to be written to stream.
     * @param bufferedWriter
     *     Instance which is associated with the stream to be written into.
     *
     * @throws IOException
     *     If an I/O error occurs.
     */
    private void writeNodeToFile (
            final String node,
            final BufferedWriter bufferedWriter) throws IOException {

        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("\"")
                    .append(node)
                    .append("\" [label=\"")
                    .append(node)
                    .append("\",shape=none];");

        final String line = stringBuffer.toString();

        // might throw an IOException;
        this.writeLineToFile(line, bufferedWriter);
    }

    /**
     * Writes a single transition to an output stream, which is given by an
     * instance of <code>BufferedInputWriter</code>.
     *
     * @param transition
     *     Transition to be written to stream.
     * @param bufferedWriter
     *     Instance which is associated with the stream to be written into.
     *
     * @throws IOException
     *     If an I/O error occurs.
     */
    private void writeTransitionToFile (
            final Transition transition,
            final BufferedWriter bufferedWriter) throws IOException {

        final StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("\"")
                    .append( transition.getSrcNodeName() )
                    .append("\"->\"")
                    .append( transition.getDstNodeName() )
                    .append("\" [style=solid,label=\"")  // ,arrowhead=none
                    .append( transition.getLabel() )
                    .append("\"];");

        final String line = stringBuffer.toString();

        // might throw an IOException;
        this.writeLineToFile(line, bufferedWriter);
    }

    /**
     * Writes a single text line to an output stream, which is given by an
     * instance of <code>BufferedInputWriter</code>.
     *
     * @param line
     *     Line to be written to stream.
     * @param bufferedWriter
     *     Instance which is associated with the stream to be written into.
     *
     * @throws IOException
     *     If an I/O error occurs.
     */
    private void writeLineToFile (
            final String line,
            final BufferedWriter bufferedWriter) throws IOException {

        // might throw an IOException;
        bufferedWriter.write(line + this.linebreakPattern);
    }


    /**
     * Internal class to be used for storing graph transition informations.
     *
     * @author Eike Schulz (esc@informatik.uni-kiel.de)
     *
     * @version 1.0 (2012-12-15)
     */
    private class Transition {

        /** Name of the source node. */
        private final String srcNodeName;

        /** Label of the transition. */
        private final String label;

        /** Name of the destination node. */
        private final String dstNodeName;


        /**
         * Constructor for a transition.
         *
         * @param srcNodeName  Name of the source node.
         * @param label        Label of the transition.
         * @param dstNodeName  Name of the destination node.
         */
        public Transition (
                final String srcNodeName,
                final String label,
                final String dstNodeName) {

            this.srcNodeName = srcNodeName;
            this.label       = label;
            this.dstNodeName = dstNodeName;
        }

        /**
         * Returns the name of the source node.
         *
         * @return Name of the source node.
         */
        public String getSrcNodeName () {

            return this.srcNodeName;
        }

        /**
         * Returns the name of the destination node.
         *
         * @return Name of the destination node.
         */
        public String getDstNodeName () {

            return this.dstNodeName;
        }

        /**
         * Returns the label of the transition.
         *
         * @return Label of the transition.
         */
        public String getLabel () {

            return this.label;
        }
    }
}