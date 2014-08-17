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

package net.sf.markov4jmeter.behaviormodelextractor.extraction.parser;

/**
 * This class represents a use case with specific start time, ending time and a
 * name.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.0 (2012-12-15)
 */
public class UseCase {

    /** Name of the use case. */
    private String name;

    /** Start time of the use case. */
    private final long startTime;

    /** Ending time of the use case. */
    private final long endTime;


    /**
     * Constructor for a <code>UseCase</code> instance with specific start time,
     * ending time and a name.
     *
     * @param name       name of the use case.
     * @param startTime  start time of the use case.
     * @param endTime    ending time of the use case.
     */
    public UseCase (
            final String name, final long startTime, final long endTime) {

        this.name      = name;
        this.startTime = startTime;
        this.endTime   = endTime;
    }


    /**
     * Returns the name of the use case.
     *
     * @return name of the use case.
     */
    public String getName () {

        return this.name;
    }
    
    /**
     * Returns the name of the use case.
     *
     * @return name of the use case.
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Returns the start time of the use case.
     *
     * @return start time of the use case.
     */
    public long getStartTime () {

        return this.startTime;
    }

    /**
     * Returns the ending time of the use case.
     *
     * @return ending time of the use case.
     */
    public long getEndTime () {

        return this.endTime;
    }

    @Override
    public String toString() {

        return name + ":" + startTime + ":" + endTime;
    }
}
