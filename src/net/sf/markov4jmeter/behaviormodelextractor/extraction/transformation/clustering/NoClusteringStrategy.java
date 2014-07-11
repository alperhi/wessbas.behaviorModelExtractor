package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;

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
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // Behavior Mix to be returned;
        final BehaviorMix behaviorMix = this.createBehaviorMix();

        final String behaviorModelName =
                AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME;

        final int n = behaviorModelsRelative.length;
        final double frequency = MathUtil.round(1.0d / n);
        double frequencySum = 0.0d;

        for (int i = 0; i < n; i++, frequencySum += frequency) {

            // ensure that the frequencies sum is always 1.0, by setting the
            // last frequency as 1.0 minus the sum of all other frequencies;
            double freq = (i < n - 1) ? frequency : (1.0d - frequencySum);

            final BehaviorMixEntry behaviorMixEntry =
                    this.createBehaviorMixEntry(
                            behaviorModelName + i,
                            MathUtil.round(freq),
                            behaviorModelsRelative[i]);

            behaviorMix.getEntries().add(behaviorMixEntry);
        }

        return behaviorMix;
    }
}