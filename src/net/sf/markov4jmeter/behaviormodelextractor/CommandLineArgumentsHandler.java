package net.sf.markov4jmeter.behaviormodelextractor;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class defines the command-line options accepted by the Behavior Model
 * Extractor. Each option is initiated by a leading hyphen; an overview is
 * given below, followed by several examples.
 *
 * <p>Available command-line options:
 * <table border="1">
 *   <tr><th>Long         </th>
 *       <th> Short       </th>
 *       <th> Description </th>
 *
 *   <tr><td><code> input </code></td>
 *       <td><code> i     </code></td>
 *       <td> Input file which includes the sessions data, e.g.,
 *       "examples/sessions.dat".
 *       </td>
 *
 *   <tr><td><code> output </code></td>
 *       <td><code> o      </code></td>
 *       <td> Output CSV-file which includes the resulting Behavior Model, e.g.,
 *       "output/behaviormodel.csv".
 *       </td>
 *
 *   <tr><td colspan="3" align="center">
 *       <i>Optional Arguments</i>
 *       </td></tr>
 *
 *   <tr><td><code> linebreak </code></td>
 *       <td><code> l         </code></td>
 *       <td> (Optional) OS-specific line-break type for CSV output files
 *       (0 = Windows, 1 = Unix, 2 = MacOS); the default value is 0 (Windows).
 *       </td>
 *
 *   <tr><td><code> template </code></td>
 *       <td><code> t        </code></td>
 *       <td> (Optional) input CSV-file which includes a template matrix, e.g.,
 *       "examples/template.csv".
 *       </td>
 *
 *   <tr><td><code> mapping </code></td>
 *       <td><code> m       </code></td>
 *       <td> (Optional) use case mapping file, e.g.,
 *       "examples/mapping.csv".
 *       </td>
 *
 *   <tr><td><code> graph </code></td>
 *       <td><code> g     </code></td>
 *       <td> (Optional) DOT output file which includes the graph, e.g.,
 *       "output/graph.dot".
 *       </td>
 * </table>
 *
 * <p>Examples:
 * <ul>
 *   <li>The options sequence
 *   <blockquote>
 *     <code>-i "./examples/sessions.dat" -o "./output/behaviormodel.csv"</code>
 *   </blockquote>
 *   denotes a minimum start configuration for the Behavior Model Extractor,
 *   defining the files "examples/sessions.dat" and "output/behaviormodel.csv"
 *   to be used as input file and output file respectively.
 *   </li>
 *
 *   <li>The options sequence
 *   <blockquote>
 *     <code>-i "./examples/sessions.dat" -o "./output/behaviormodel.csv"
 *     -l 2</code>
 *   </blockquote>
 *   has the same effect as the first one, but it additionally defines a
 *   MacOS-specific line-break type to be used for the output content.
 *
 *   <li>The options sequence
 *   <blockquote>
 *     <code>-i "./examples/sessions.dat" -o "./output/behaviormodel.csv"
 *     -l 2 -t "./examples/template.csv" -g "./output/graph.dot"</code>
 *   </blockquote>
 *   has the same effect as the second one, but it additionally passes a
 *   template file for being filled with values, as well as a graph output file.
 *   </li>
 * </ul>
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class CommandLineArgumentsHandler {

    /** Input file which includes the sessions data. */
    private final static Option INPUT_FILE =
            CmdlOptionFactory.createOption(
                    "i",                                   // opt;
                    "input",                               // longOpt;
                    "Input file which includes the "       // description;
                    + "sessions data.",
                    true,                                  // isRequired;
                    "sessions.dat",                        // argName;
                    false);                                // !hasOptionalArg;

    /** Output CSV-file which includes the resulting Behavior Model. */
    private final static Option OUTPUT_FILE =
            CmdlOptionFactory.createOption(
                    "o",                                    // opt;
                    "output",                               // longOpt;
                    "Output CSV-file which includes the "  // description;
                    + "resulting Behavior Model.",
                    true,                                   // isRequired;
                    "behaviormodel.csv",                    // argName;
                    false);                                 // !hasOptionalArg;

    /** (Optional) OS-specific line-break type for CSV output files
     *  (0 = Windows, 1 = Unix, 2 = MacOS); the default value is 0 (Windows). */
    private final static Option LINE_BREAK_TYPE =
            CmdlOptionFactory.createOption(
                    "l",                                    // opt;
                    "linebreak",                            // longOpt;
                    "(Optional) OS-specific line-break "    // description;
                    + "type for CSV output files (0 = Windows, 1 = Unix, "
                    + "2 = MacOS); the default value is 0 (Windows).",
                    false,                                  // !isRequired;
                    "0",                                    // argName;
                    false);                                 // !isRequired;

    /** (Optional) input CSV-file which includes a template matrix. */
    private final static Option TEMPLATE_FILE =
            CmdlOptionFactory.createOption(
                    "t",                                   // opt;
                    "template",                            // longOpt;
                    "(Optional) input CSV-file which "     // description;
                    + "includes a template matrix.",
                    false,                                 // isRequired;
                    "template.csv",                        // argName;
                    false);                                // !hasOptionalArg;

    /** (Optional) use case mapping file. */
    private final static Option USE_CASE_MAPPING_FILE =
            CmdlOptionFactory.createOption(
                    "m",                                   // opt;
                    "mapping",                             // longOpt;
                    "(Optional) use case mapping file",    // description;
                    false,                                 // isRequired;
                    "mapping.csv",                         // argName;
                    false);                                // !hasOptionalArg;

    /** (Optional) DOT output file which includes the graph. */
    private final static Option OUTPUT_DOT_FILE =
            CmdlOptionFactory.createOption(
                    "g",                                    // opt;
                    "graph",                                // longOpt;
                    "(Optional) output DOT-file which "     // description;
                    + "includes the graph.",
                    false,                                  // !isRequired;
                    "graph.dot",                                    // argName;
                    false);                                 // !isRequired;

    /** Formatter for printing the usage instructions. */
    private final static HelpFormatter HELP_FORMATTER = new HelpFormatter();

    /** Basic parser for extracting values from command-line input. */
    private final static CommandLineParser PARSER = new BasicParser();


    /* *********************  global (non-final) fields  ******************** */


    /** Input file which has been read from command-line. */
    private static String inputFile;

    /** Output CSV-file which has been read from command-line. */
    private static String outputFile;

    /** (Optional) line-break type which has been read from command-line. */
    private static int lineBreakType;

    /** (Optional) template file which has been read from command-line. */
    private static String templateFile;

    /** (Optional) use case mapping file. */
    private static String useCaseMappingFile;

    /** (Optional) DOT output file which has been read from command-line. */
    private static String outputDotFile;

    /** Command-line options to be parsed. */
    private static Options options;


    /* ***************************  static blocks  ************************** */


    static {

        // fill the options container;
        CommandLineArgumentsHandler.options = new Options();

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.INPUT_FILE);

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.OUTPUT_FILE);

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.LINE_BREAK_TYPE);

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.TEMPLATE_FILE);

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.USE_CASE_MAPPING_FILE);

        CommandLineArgumentsHandler.options.addOption(
                CommandLineArgumentsHandler.OUTPUT_DOT_FILE);
    }


    /* **************************  public methods  ************************** */


    /**
     * Returns the input file which has been read from command-line.
     *
     * @return  a valid <code>String</code> which represents a file path.
     */
    public static String getInputFile () {

        return CommandLineArgumentsHandler.inputFile;
    }

    /**
     * Returns the output file which has been read from command-line.
     *
     * @return  a valid <code>String</code> which represents a file path.
     */
    public static String getOutputCsvFile () {

        return CommandLineArgumentsHandler.outputFile;
    }

    /**
     * Returns the (optional) line-break type which has been read from
     * command-line.
     *
     * @return  an integer value which represents the line-break type.
     */
    public static int getLineBreakType () {

        return CommandLineArgumentsHandler.lineBreakType;
    }

    /**
     * Returns the (optional) template file which has been read from
     * command-line.
     *
     * @return
     *     a valid <code>String</code> which represents a file path, or
     *     <code>null</code> if no file path has been read.
     */
    public static String getTemplateFile () {

        return CommandLineArgumentsHandler.templateFile;
    }

    /**
     * Returns the (optional) use case mapping file which has been read from
     * command-line.
     *
     * @return
     *     a valid <code>String</code> which represents a file path, or
     *     <code>null</code> if no file path has been read.
     */
    public static String getUseCaseMappingFile () {

        return CommandLineArgumentsHandler.useCaseMappingFile;
    }

    /**
     * Returns the (optional) DOT output file which has been read from
     * command-line.
     *
     * @return
     *     a valid <code>String</code> which represents a file path, or
     *     <code>null</code> if no file path has been read.
     */
    public static String getOutputDotFile () {

        return CommandLineArgumentsHandler.outputDotFile;
    }

    /**
     * Prints the usage instructions to standard output.
     */
    public static void printUsage () {

        CommandLineArgumentsHandler.HELP_FORMATTER.printHelp(
            BehaviorModelExtractor.class.getSimpleName(),
            CommandLineArgumentsHandler.options);
    }

    /**
     * Initializes the handler by parsing the given array of arguments; the
     * parsed values might be requested through the <code>get()</code> methods
     * of this class.
     *
     * @param args
     *     sequence of <code>String</code>s to be parsed; might comply with
     *     the arguments which have been passed to the <code>main()</code>
     *     method of the application.
     *
     * @throws ParseException
     *     if the given arguments do not match the set of options which
     *     is predefined by this class.
     * @throws NullPointerException
     *     if <code>null</code> has been passed as a parameter, or if the value
     *     of any required option is undefined (<code>null</code>).
     * @throws IllegalArgumentException
     *     if an option flag denotes an empty <code>String</code>
     *     (<code>""</code>).
     */
    public static void init (final String[] args)
        throws ParseException, NullPointerException, IllegalArgumentException {

        // might throw a ParseException;
        final CommandLine commandLine =
                CommandLineArgumentsHandler.parseCommands(args);

        CommandLineArgumentsHandler.inputFile =
                CommandLineArgumentsHandler.readOptionValueAsString(
                        commandLine,
                        CommandLineArgumentsHandler.INPUT_FILE);

        CommandLineArgumentsHandler.outputFile =
                CommandLineArgumentsHandler.readOptionValueAsString(
                        commandLine,
                        CommandLineArgumentsHandler.OUTPUT_FILE);

        CommandLineArgumentsHandler.templateFile =
                CommandLineArgumentsHandler.readOptionValueAsString(
                        commandLine,
                        CommandLineArgumentsHandler.TEMPLATE_FILE);

        CommandLineArgumentsHandler.useCaseMappingFile =
                CommandLineArgumentsHandler.readOptionValueAsString(
                        commandLine,
                        CommandLineArgumentsHandler.USE_CASE_MAPPING_FILE);

        CommandLineArgumentsHandler.outputDotFile =
                CommandLineArgumentsHandler.readOptionValueAsString(
                        commandLine,
                        CommandLineArgumentsHandler.OUTPUT_DOT_FILE);

        CommandLineArgumentsHandler.lineBreakType =
                CommandLineArgumentsHandler.readOptionValueAsInt(
                        commandLine,
                        CommandLineArgumentsHandler.LINE_BREAK_TYPE);
    }


    /* **************************  private methods  ************************* */


    /**
     * Reads the value for a given option from the specified command-line as
     * <code>String</code>.
     *
     * @param commandLine  command-line which provides the values.
     * @param option       option whose value shall be read from command-line.
     *
     * @return
     *     a valid <code>String</code>, or <code>null</code> if the option's
     *     value is optional and undefined.
     *
     * @throws NullPointerException
     *     in case the value is required, but could not be read as
     *     <code>String</code>.
     * @throws IllegalArgumentException
     *     if an option flag denotes an empty <code>String</code>
     *     (<code>""</code>).
     */
    private static String readOptionValueAsString (
            final CommandLine commandLine,
            final Option option)
                    throws NullPointerException, IllegalArgumentException {

        String value;  // to be returned;

        final String opt = option.getOpt();

        // build an instance for reading "typed" options from command-line;
        final CmdlOptionsReader cmdlOptionsReader =
                new CmdlOptionsReader(commandLine);

        try {

            // might throw a NullPointer- or IllegalArgumentException;
            value = cmdlOptionsReader.readOptionValueAsString(opt);

        } catch (final Exception ex) {

            if ( option.isRequired() ) {

                throw ex;

            } else {

                value = null;  // accept undefined value for optional option;
            }
        }

        return value;
    }

    /**
     * Reads the value for a given option from the specified command-line as
     * <code>int</code>.
     *
     * @param commandLine  command-line which provides the values.
     * @param option       option whose value shall be read from command-line.
     *
     * @return
     *     an <code>int</code> value which is 0, if the option's value is
     *     optional and undefined.
     *
     * @throws NullPointerException
     *     in case the value is required, but could not be read as
     *     <code>int</code>.
     * @throws NumberFormatException
     *     if the parsed value does not denote an <code>int</code> value.
     */
    private static int readOptionValueAsInt (
            final CommandLine commandLine,
            final Option option)
                    throws NullPointerException, NumberFormatException {

        int value;  // to be returned;

        final String opt = option.getOpt();

        // build an instance for reading "typed" options from command-line;
        final CmdlOptionsReader cmdlOptionsReader =
                new CmdlOptionsReader(commandLine);

        try {

            // might throw a NullPointer- or NumberFormatException;
            value = cmdlOptionsReader.readOptionValueAsInt(opt);

        } catch (final Exception ex) {

            if ( option.isRequired() ) {

                throw ex;

            } else {

                value = 0;  // accept undefined value for optional option;
            }
        }

        return value;
    }

    /**
     * Parses the given user input and builds an instance of
     * {@link CommandLine}.
     *
     * @param args
     *     user input as it might have been passed to the <code>main()</code>
     *     method of the application before.
     *
     * @return
     *     an instance of {@link CommandLine} to be used for requesting any
     *     input values.
     *
     * @throws ParseException
     *     in case the given arguments do not match the predefined set of
     *     options.
     */
    private static CommandLine parseCommands (final String[] args)
            throws ParseException {

        // might throw a ParseException; returns a CommandLine, if successful;
        return CommandLineArgumentsHandler.PARSER.parse(
                CommandLineArgumentsHandler.options, args);
    }
}