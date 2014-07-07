package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
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
    public BehaviorMixEntry[] apply (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // TODO: implementation of the Menascé-based clustering;

        // if any error occurs, an ExtractionExeption should be thrown,
        // indicating the error that occurred;

        // the classes "NoClusteringStrategy" and "SimpleClusteringStrategy"
        // should give an idea of how to handle the Behavior Models and how to
        // use the helping methods of the abstract base class.

        return null;  // will currently throw a NullPointerException, if
                      // option "-c menasce" is passed via command-line;
    }
}
