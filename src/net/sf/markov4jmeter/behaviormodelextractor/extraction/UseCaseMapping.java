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
import java.util.HashMap;
import java.util.Map;

import net.sf.markov4jmeter.behaviormodelextractor.util.CSVHandler;

/**
 * This class allows the definition of a use case mapping, which is required
 * when names of use cases in session traces differ from the use case names
 * which shall be written into a result matrix; such cases occur for example,
 * when monitored user session traces originate from an alternative system
 * rather than the system under test.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.1
 */
public class UseCaseMapping extends HashMap<String,String> {

    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    /** The default CSV-separator is a comma. */
    protected final static String DEFAULT_SEPARATOR = ",";


    /* *************************  global variables  ************************* */


    /** CSV-separator to be used in between values, which are provided by a
     *  CSV-file. */
    private final String csvSeparator;


    /* ***************************  constructors  *************************** */


    /**
     * Constructor for a Use Case Mapping with a specific CSV-separator.
     *
     * @param csvSeparator
     *     CSV-separator to be used in between values, which are provided by a
     *     CSV-file; if <code>null</code> is passed, a comma will be used as
     *     default separator.
     */
    public UseCaseMapping (final String csvSeparator) {

        super();
        this.csvSeparator = (csvSeparator != null) ?
                csvSeparator : UseCaseMapping.DEFAULT_SEPARATOR;
    }

    /**
     * Default constructor for a Use Case Mapping, using comma as default
     * CSV-separator.
     */
    public UseCaseMapping () {

        this(UseCaseMapping.DEFAULT_SEPARATOR);
    }


    /* **************************  public methods  ************************** */


    /**
     * Initializes the internal map with the values provided by a CSV-file.
     *
     * <p> The values in the specified CSV-file must be separated by the
     * instance-related separator. For the default separator (comma), an example
     * file content might look as follows:
     * <pre>
     *   sign in  , login
     *   sign off , logout</pre>
     * This definition assigns the use cases "login" to "sign in" and "logout"
     * to "sign off" respectively; any lines whose number of tokens does not
     * equal 2 will be ignored.
     *
     * @param filename
     *     the name of the CSV-file to be loaded; the values in that file must
     *     be separated corresponding to the CSV-separator of the related
     *     {@link UseCaseMapping} instance.
     *
     * @throws FileNotFoundException
     *     in case the specified file does not exist.
     * @throws IOException
     *     if any reading error occurs.
     * @throws NullPointerException
     *     if <code>null</code> is passed as filename.
     */
    public void initMap (final String filename)
            throws FileNotFoundException, IOException, NullPointerException {

        final CSVHandler csvHandler = new CSVHandler(this.csvSeparator);

        // might throw a FileNotFound-, NullPointer- or IOException;
        final String[][] values = csvHandler.readValues(filename);

        this.clear();

        for (final String[] row : values) {

            if (row.length == 2) {

                final String key   = row[0];
                final String value = row[1];

                this.put(key, value);
            }
        }
    }

    /**
     * Initializes the internal map with the values provided by a given map.
     *
     * @param map
     *     the instance with mapping values to be added to the internal map.
     */
    public void initMap (final Map<String, String> map) {

        this.clear();
        this.putAll(map);
    }

    /**
     * Looks up for a use case name, which is assigned to the given use case
     * name.
     *
     * @param useCaseName
     *     name of the use case whose assigned value shall be returned.
     *
     * @return
     *     the name of the use case, which is assigned to the given use case
     *     name; if no matching name can be found, the given name itself will
     *     be returned.
     */
    public String lookup (final String useCaseName) {

        final String value = this.get(useCaseName);

        return (value != null) ? value : useCaseName;
    }
}