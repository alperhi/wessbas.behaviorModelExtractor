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


package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.ABMToRBMTransformer;
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;

/**
 * This class represents a <i>"simple"</i> clustering strategy, which calculates
 * the mean value of probabilities for each transition, as well as the
 * mean/deviation values for cumulative time distances of a transition; the
 * resulting values will be stored in a single output Behavior Model.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class SimpleClusteringStrategy extends AbstractClusteringStrategy {


    /* **************************  public methods  ************************** */


    /**
     * {@inheritDoc}
     *
     * <p> This method is specialized for <b>"simple"</b> clustering, which
     * calculates the mean value of probabilities for each transition, as well
     * as the mean/deviation values for cumulative time distances of a
     * transition; the resulting values will be stored in a single output
     * Behavior Model.
     */
    @Override
    public BehaviorMix apply (
            final BehaviorModelAbsolute[] behaviorModelsAbsolute,
            final UseCaseRepository useCaseRepository) {

        final ABMToRBMTransformer abmToRbmTransformer =
                new ABMToRBMTransformer();       
        
        final BehaviorModelRelative[] behaviorModelsRelative =
                abmToRbmTransformer.transform(behaviorModelsAbsolute);
        
        //TODO: First calculate BehaviorModelAbsolut form all BehaviorModelsAbsolut and then 
        // BehaviorModelRelative. Otherwise the results of the simpleClustering is not correct.
    	
        // Behavior Mix to be returned;
        final BehaviorMix behaviorMix = this.createBehaviorMix();

        // create a Behavior Model, which includes all vertices only; the
        // vertices are associated with the use cases, and a dedicated vertex
        // that represents the final state will be added;
        final BehaviorModelRelative behaviorModel =
                this.createBehaviorModelRelativeWithoutTransitions(
                        useCaseRepository.getUseCases());

        // install the transitions in between vertices;
        this.installTransitions(behaviorModel, behaviorModelsRelative);

        // create the (unique) Behavior Mix entry to be returned;
        final BehaviorMixEntry behaviorMixEntry = this.createBehaviorMixEntry(
                AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME,
                1.0d,  // relative frequency;
                behaviorModel);

        behaviorMix.getEntries().add(behaviorMixEntry);

        return behaviorMix;
    }


    /* **************************  private methods  ************************* */


    /**
     * Installs the transitions of a given Behavior Model, according to the
     * "simple" clustering strategy.
     *
     * @param behaviorModel
     *     Behavior Model whose transitions shall be installed.
     * @param behaviorModelsRelative
     *     set of input Behavior Models on which the clustering strategy shall
     *     be applied.
     *
     * @return
     *    (unique) Behavior Model that results from the simple clustering
     *    strategy.
     */
    private BehaviorModelRelative installTransitions (
            final BehaviorModelRelative behaviorModel,
            final BehaviorModelRelative[] behaviorModelsRelative) {

        final List<Vertex> vertices = behaviorModel.getVertices();

        for (final Vertex srcVertex : vertices) {

            if (srcVertex.getUseCase() != null) {  // no final state

                for (final Vertex dstVertex : vertices) {           		

                    // ignore returned transition;
                    this.installTransition(
                            srcVertex,
                            dstVertex,
                            behaviorModelsRelative);
                }

            } else {

                continue;  // skip final state ("$");
            }
        }

        return behaviorModel;
    }

    /**
     * Installs a transition between two given vertices.
     *
     * @param srcVertex
     *     source vertex of the transition; the new transition will be added to
     *     the outgoing transitions of this vertex.
     * @param dstVertex
     *     destination vertex, which might be even <code>null</code> (final
     *     state, "$").
     * @param behaviorModelsRelative
     *     set of input Behavior Models on which the clustering strategy shall
     *     be applied.
     *
     * @return
     *     the newly installed transition, or <code>null</code> if no transition
     *     has been installed.
     */
    private Transition installTransition (
            final Vertex srcVertex,
            final Vertex dstVertex,
            final BehaviorModelRelative[] behaviorModelsRelative) {

        Transition newTransition = null;  // transition to be returned;

        final UseCase srcUseCase = srcVertex.getUseCase();
        final UseCase dstUseCase = dstVertex.getUseCase();

        final String srcUseCaseId = srcUseCase.getId();  // always defined here;

        final LinkedList<BigDecimal> timeDiffs = new LinkedList<BigDecimal>();

        // if dstUseCase is null, its vertex denotes the final state (no ID);
        final String dstUseCaseId =
                (dstUseCase != null) ? dstUseCase.getId() : null;

        double probabilitySum = 0.0d;

        // collect all probabilities and time ranges;
        for (final BehaviorModelRelative behaviorModelRelative :
             behaviorModelsRelative) {

            final Transition transition = this.findTransitionByUseCaseIDs(
                    behaviorModelRelative,
                    srcUseCaseId,
                    dstUseCaseId);  // dstUseCaseId might be null (final state);

            if (transition != null) {

                probabilitySum += transition.getValue();

                // collect all time ranges for computing the think time;
                timeDiffs.addAll( transition.getTimeDiffs() );
            }
        }

        probabilitySum /= behaviorModelsRelative.length;

        if (probabilitySum > 0) {

            // create new transitions by invoking helper method of parent class;
            newTransition = this.installTransition(srcVertex, dstVertex);

            newTransition.setValue(probabilitySum);

            if (timeDiffs.size() > 0) {

                // calculate new mean/deviation values, based on time ranges;

                newTransition.getThinkTimeParams().add(
                        MathUtil.computeMean(timeDiffs));

                newTransition.getThinkTimeParams().add(
                        MathUtil.computeDeviation(timeDiffs));
            }
        }

        return newTransition;
    }
}
