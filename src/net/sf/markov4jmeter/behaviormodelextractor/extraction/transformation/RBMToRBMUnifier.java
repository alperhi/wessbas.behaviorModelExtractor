package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;

public class RBMToRBMUnifier {


    /* *****************************  constants  **************************** */


    public final static String CLUSTERING_METHOD_NONE = "none";
    public final static String CLUSTERING_METHOD_SIMPLE = "simple";
    public final static String CLUSTERING_METHOD_MENASCE = "menasce";

    private final static String WARNING_UNKNOWN_CLUSTERING_METHOD =
            "unknown clustering method \"%s\"; no clustering will be applied";


    /* *************************  global variables  ************************* */


    private final String clusteringMethod;


    /* ***************************  constructors  *************************** */


    /**
     *
     * @param clusteringMethod
     */
    public RBMToRBMUnifier (final String clusteringMethod) {

        this.clusteringMethod = (clusteringMethod == null) ?
                RBMToRBMUnifier.CLUSTERING_METHOD_NONE : clusteringMethod;
    }


    /* **************************  public methods  ************************** */


    /**
     *
     * @param behaviorModelRelative
     * @return
     */
    public BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelRelative) {

        switch (this.clusteringMethod) {

            case RBMToRBMUnifier.CLUSTERING_METHOD_NONE:

                return this.transform_noClustering(behaviorModelRelative);

            case RBMToRBMUnifier.CLUSTERING_METHOD_SIMPLE:

                return this.transform_simpleClustering(behaviorModelRelative);

            case RBMToRBMUnifier.CLUSTERING_METHOD_MENASCE:

                return this.transform_menasceClustering(behaviorModelRelative);

            default:

                final String message = String.format(
                        RBMToRBMUnifier.WARNING_UNKNOWN_CLUSTERING_METHOD,
                        this.clusteringMethod);

                System.out.println(message);

                // use no clustering by default;
                return this.transform_noClustering(behaviorModelRelative);
        }
    }

    /**
     *
     * @param behaviorModelsRelative
     * @return
     */
    private BehaviorMixEntry[] transform_noClustering (
            final BehaviorModelRelative[] behaviorModelsRelative) {

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

    /**
     * Applies a "simple" clustering method on the extracted Behavior Models;
     * average values of probabilities and think times will be calculated and
     * summarized per transition, resulting in a single Behavior Model.
     *
     * @param behaviorModelsRelative
     *     set of Behavior Models on which the clustering method shall be
     *     applied.
     *
     * @return
     *     a set of Behavior Mix entries, including a single Behavior Model with
     *     a (generated) name and 1.0 as relative frequency.
     */
    private BehaviorMixEntry[] transform_simpleClustering (
            final BehaviorModelRelative[] behaviorModelsRelative) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        // the unique Behavior Mix entry to be returned (as an array);
        final BehaviorMixEntry behaviorMixEntry =
                factory.createBehaviorMixEntry();

        final String behaviorModelName = "gen_behavior_model";
        final double frequency = 1.0;

        BehaviorModelRelative behaviorModel = null;

        for (int i = 0, n = behaviorModelsRelative.length; i < n; i++) {

            // TODO: implementation of average values computation;
            behaviorModel = behaviorModelsRelative[i];
        }

        behaviorMixEntry.setBehaviorModelName(behaviorModelName);
        behaviorMixEntry.setRelativeFrequency(frequency);
        behaviorMixEntry.setBehaviorModel(behaviorModel);

        return new BehaviorMixEntry[]{ behaviorMixEntry };
    }

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
    private BehaviorMixEntry[] transform_menasceClustering (
            final BehaviorModelRelative[] behaviorModelsRelative) {

        // TODO: implementation; if any error occurs, an ExtractionExeption
        // should be thrown, including an informational message.
        return null;
    }
}
