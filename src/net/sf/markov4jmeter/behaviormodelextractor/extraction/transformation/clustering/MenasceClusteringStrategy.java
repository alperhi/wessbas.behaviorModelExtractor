package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;

public class MenasceClusteringStrategy extends AbstractClusteringStrategy {

    /**
     * Applies the Menascé-based clustering method on the extracted Behavior
     * Models.
     *
     * @param behaviorModelsRelative
     *     set of Behavior Models on which the clustering method shall be
     *     applied.
     *
     * @return
     *     a set of Behavior Mix entries, including the related Behavior Models,
     *     their (generated) names and computed relative frequencies.
     */
    @Override
    public BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // TODO: implementation; if any error occurs, an ExtractionExeption
        // should be thrown, indicating the error that occurred;
        return null;
    }
}
