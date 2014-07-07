package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;

/**
 * This class represents a <i>Menascé-based</i> clustering strategy.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class MenasceClusteringStrategy extends AbstractClusteringStrategy {


    /* **************************  public methods  ************************** */


    /**
     * {@inheritDoc}
     *
     * <p> This method is specialized for <b>Menascé-based</b> clustering.
     */
    @Override
    public BehaviorMix apply (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // Behavior Mix to be returned;
        final BehaviorMix behaviorMix = this.createBehaviorMix();

        // TODO: implementation of the Menascé-based clustering;

        // if any error occurs, an ExtractionExeption should be thrown,
        // indicating the error that occurred;

        // the classes "NoClusteringStrategy" and "SimpleClusteringStrategy"
        // should give an idea for handling the Behavior Models and how to
        // use the helping methods of the abstract base class.

        return behaviorMix;
    }
}