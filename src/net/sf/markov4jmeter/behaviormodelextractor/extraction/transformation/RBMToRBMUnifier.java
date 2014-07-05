package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation;

import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.AbstractClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.MenasceClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.NoClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.SimpleClusteringStrategy;

public class RBMToRBMUnifier {


    /* *****************************  constants  **************************** */


    public final static String CLUSTERING_METHOD_NONE = "none";
    public final static String CLUSTERING_METHOD_SIMPLE = "simple";
    public final static String CLUSTERING_METHOD_MENASCE = "menasce";

    private final static String WARNING_UNKNOWN_CLUSTERING_METHOD =
            "unknown clustering method \"%s\"; no clustering will be applied";


    /* **************************  public methods  ************************** */


    /**
     *
     * @param behaviorModelRelative
     * @return
     * @throws ExtractionException
     */
    public BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final String clusteringMethod,
            final UseCaseRepository useCaseRepository,
            final String finalVertexName) throws ExtractionException {

        final AbstractClusteringStrategy clusteringStrategy =
                this.getClusteringStrategy(clusteringMethod);

        return clusteringStrategy.transform(
                behaviorModelsRelative,
                useCaseRepository);
    }


    private AbstractClusteringStrategy getClusteringStrategy (
            String clusteringMethod) {

        // to be returned;
        final AbstractClusteringStrategy clusteringStrategy;

        if (clusteringMethod == null) {

            // use no clustering by default;
            clusteringMethod = RBMToRBMUnifier.CLUSTERING_METHOD_NONE;
        }

        switch ( clusteringMethod.trim().toLowerCase() ) {

            case RBMToRBMUnifier.CLUSTERING_METHOD_NONE:

                clusteringStrategy = new NoClusteringStrategy();
                break;

            case RBMToRBMUnifier.CLUSTERING_METHOD_SIMPLE:

                clusteringStrategy = new SimpleClusteringStrategy();
                break;

            case RBMToRBMUnifier.CLUSTERING_METHOD_MENASCE:

                clusteringStrategy = new MenasceClusteringStrategy();
                break;

            default:

                final String message = String.format(
                        RBMToRBMUnifier.WARNING_UNKNOWN_CLUSTERING_METHOD,
                        clusteringMethod);

                System.out.println(message);

                // use no clustering by default;
                clusteringStrategy = new NoClusteringStrategy();
        }

        return clusteringStrategy;
    }
}
