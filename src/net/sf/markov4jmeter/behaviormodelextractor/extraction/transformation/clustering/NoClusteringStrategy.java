package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;

public class NoClusteringStrategy extends AbstractClusteringStrategy {

    @Override
    public BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        final int n = behaviorModelsRelative.length;

        // Behavior Mix entries to be returned;
        final BehaviorMixEntry[] behaviorMixEntries = new BehaviorMixEntry[n];

        final String behaviorModelName = "gen_behavior_model";
        final double frequency = 1.0 / n;

        double frequencySum = 0;

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        for (int i = 0; i < n; i++, frequencySum += frequency) {

            final BehaviorMixEntry behaviorMixEntry =
                    factory.createBehaviorMixEntry();

            behaviorMixEntry.setBehaviorModelName(behaviorModelName + i);

            // ensure that the frequencies sum is always 1.0, by setting the
            // last frequency as 1.0 minus the sum of all other frequencies;
            behaviorMixEntry.setRelativeFrequency(
                    (i < n - 1) ? frequency : (1.0d - frequencySum) );

            behaviorMixEntry.setBehaviorModel(behaviorModelsRelative[i]);

            behaviorMixEntries[i] = behaviorMixEntry;
        }

        return behaviorMixEntries;
    }
}
