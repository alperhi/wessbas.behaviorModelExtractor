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

package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import net.sf.markov4jmeter.behaviormodelextractor.util.Util;

import org.eclipse.emf.ecore.util.EcoreUtil;

import dynamod.behavior.BehaviorFactory;
import dynamod.behavior.BehaviorModelAbsolute;
import dynamod.behavior.BehaviorModelRelative;
import dynamod.behavior.Transition;
import dynamod.behavior.Vertex;

/**
 * This class provides methods for transforming "absolute" Behavior Models to
 * "relative" Behavior Models.
 *
 * @see SessionToABMTransformer
 * @see RBMToMarkovMatrixTransformer
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.0
 */
public class ABMToRBMTransformer {


    /* **************************  public methods  ************************** */


    /**
     * Transforms a set of "absolute" Behavior Models to "relative" Behavior
     * Models.
     *
     * @param behaviorModelsAbsolute
     *     the absolute Behavior Models to be transformed to relative Behavior
     *     Models.
     *
     * @return
     *     the resulting relative Behavior Models.
     */
    public BehaviorModelRelative[] transform (
            final BehaviorModelAbsolute[] behaviorModelsAbsolute) {

        final int n = behaviorModelsAbsolute.length;

        // to models to be returned;
        final BehaviorModelRelative[] behaviorModelsRelative =
                new BehaviorModelRelative[n];

        for (int i = 0; i < n; i++) {

            final BehaviorModelAbsolute behaviorModelAbsolute =
                    behaviorModelsAbsolute[i];

            final BehaviorModelRelative behaviorModelRelative =
                    this.transform(behaviorModelAbsolute);

            behaviorModelsRelative[i] = behaviorModelRelative;
        }
        return behaviorModelsRelative;
    }


    /* **************************  private methods  ************************* */


    /**
     * Transforms an "absolute" Behavior Model to a "relative" Behavior Model.
     *
     * @param behaviorModelAbsolute
     *     the absolute Behavior Model to be transformed to a relative Behavior
     *     Model.
     *
     * @return
     *     the resulting relative Behavior Model.
     */
    private BehaviorModelRelative transform (
            final BehaviorModelAbsolute behaviorModelAbsolute) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        // to model to be returned;
        final BehaviorModelRelative behaviorModelRelative =
                factory.createBehaviorModelRelative();

        final List<Vertex> aVertices = behaviorModelAbsolute.getVertices();
        final List<Vertex> rVertices = behaviorModelRelative.getVertices();

        for (final Vertex aVertex : aVertices) {

            // clone the original model recursively (including transitions);
            final Vertex rVertex = EcoreUtil.copy(aVertex);

            this.convertOutgoingTransitionValues(rVertex);
            rVertices.add(rVertex);
        }

        return behaviorModelRelative;
    }

    /**
     * Converts the values (labels) of the outgoing transitions of a given
     * vertex from absolute to relative values.
     *
     * @param vertex the vertex whose outgoing transitions will be converted.
     */
    private void convertOutgoingTransitionValues (final Vertex vertex) {

        final List<Transition> outgoingTransitions =
                vertex.getOutgoingTransitions();

        // count number of transition occurrences (note that each transition
        // might fire several times to a certain target vertex);
        int n = 0;

        for (final Transition outgoingTransition : outgoingTransitions) {

            final double value = outgoingTransition.getValue();
            n += value;
        }

        for (final Transition outgoingTransition : outgoingTransitions) {

            // conversion: absolute values -> relative values;

            final double value = outgoingTransition.getValue();
            final double relValue = value / n;  // n > 0 here;

            outgoingTransition.setValue(relValue);

            // conversion: times -> think times;

            final double mean;
            final double deviation;

            final List<BigDecimal> times = outgoingTransition.getTimes();

            if (times.size() > 0) {

                // compute mean;

                mean = this.computeMean(times);
                deviation = this.computeDeviation(times, mean);

                // remove all time values before storing the think time;
                times.clear();

            } else {  // times.size() == 0;

                mean = 0.0d;
                deviation = 0.0d;
            }

            times.add( new BigDecimal(mean) );
            times.add( new BigDecimal(deviation) );
        }
    }

    /**
     * Calculates the mean value for a given set of time ranges.
     *
     * @param times  set of time ranges whose mean value shall be calculated.
     *
     * @return a non-negative mean value.
     */
    private double computeMean (final List<BigDecimal> times) {

        BigDecimal sum = new BigDecimal(0L);

        for (final BigDecimal time : times) {

            sum = sum.add(time);
        }

        return sum.divide(
                new BigDecimal( times.size() ),
                0,  // precision 0  -->  no digits behind the comma;
                RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Calculates the deviation value for a given set of time ranges and their
     * mean value.
     *
     * @param times
     *     set of time ranges whose deviation value shall be calculated.
     * @param mean
     *     mean value of the given time ranges.
     *
     * @return  a non-negative deviation value.
     */
    private double computeDeviation (
            final List<BigDecimal> times,
            final double mean) {

        // sqrt( sum( (d_{ij} - mean)^2 ) / n );

        final LinkedList<BigDecimal> dTimes = new LinkedList<BigDecimal>();

        final BigDecimal subtrahend = new BigDecimal(mean);

        for (final BigDecimal time : times) {

            final BigDecimal diff = time.subtract(subtrahend);

            dTimes.add( diff.multiply(diff) );
        }

        BigDecimal variance = new BigDecimal(0L);

        for (final BigDecimal dTime : dTimes) {

            variance = variance.add(dTime);
        }

        variance = variance.divide(
                new BigDecimal( dTimes.size() ),
                0,  // precision 0  -->  no digits behind the comma;
                RoundingMode.HALF_UP);

        // use RoundingMode.DOWN, to avoid 1 being returned if variance is 0;
        return Util.sqrt(variance, RoundingMode.DOWN).doubleValue();
    }
}