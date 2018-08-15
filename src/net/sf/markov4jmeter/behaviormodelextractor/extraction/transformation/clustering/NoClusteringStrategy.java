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

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.ABMToRBMTransformer;

/**
 * This class represents a <i>no-clustering</i> strategy, that is each single
 * session trace indicates an own Behavior Model.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class NoClusteringStrategy extends AbstractClusteringStrategy {


    /* **************************  public methods  ************************** */


    /**
     * {@inheritDoc}
     *
     * <p> This method is specialized for <b>no clustering</b>, that is each
     * single session trace indicates an own Behavior Model; in particular,
     * the given Behavior Models will be included to the resulting Behavior Mix
     * as they are.
     */
    @Override
    public BehaviorMix apply (
            final BehaviorModelAbsolute[] behaviorModelsAbsolute,
            final UseCaseRepository useCaseRepository,
            final String outputDirectory) {

        final ABMToRBMTransformer abmToRbmTransformer =
                new ABMToRBMTransformer();
        
        final BehaviorModelRelative[] behaviorModelsRelative =
                abmToRbmTransformer.transform(behaviorModelsAbsolute);

        // Behavior Mix to be returned;
        final BehaviorMix behaviorMix = this.createBehaviorMix();

        final String behaviorModelName =
                AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME;

        final int n = behaviorModelsRelative.length;
        final double frequency = 1.0d / n;

        for (int i = 0; i < n; i++) {

            final BehaviorMixEntry behaviorMixEntry =
                    this.createBehaviorMixEntry(
                            behaviorModelName + i,
                            frequency,
                            behaviorModelsRelative[i]);

            behaviorMix.getEntries().add(behaviorMixEntry);
        }

        return behaviorMix;
    }
}
