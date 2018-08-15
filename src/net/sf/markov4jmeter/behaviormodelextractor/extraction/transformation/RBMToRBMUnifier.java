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


package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.AbstractClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.KMeansClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.NoClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.SimpleClusteringStrategy;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering.XMeansClusteringStrategy;

/**
 * This class provides methods for transforming Behavior Models to a Behavior
 * Mix, which includes Behavior Models that result from a specific clustering
 * strategy being applied to the input models.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.0
 */
public class RBMToRBMUnifier {


    /* *****************************  constants  **************************** */


    /** Clustering type constant for "no-clustering". */
    public final static String CLUSTERING_TYPE_NONE = "none";

    /** Clustering type constant for "simple" clustering. */
    public final static String CLUSTERING_TYPE_SIMPLE = "simple";

    /** Clustering type constant for kmeans-based clustering. */
    public final static String CLUSTERING_TYPE_KMEANS = "kmeans";
    
    /** Clustering type constant for xmeans-based clustering. */
    public final static String CLUSTERING_TYPE_XMEANS = "xmeans";

    /** Warning message for the case that an unknown clustering type has been
     *  specified. */
    private final static String WARNING_UNKNOWN_CLUSTERING_METHOD =
            "unknown clustering method \"%s\"; no clustering will be applied";


    /* **************************  public methods  ************************** */


    /**
     * Transforms a set of input Behavior Models to a corresponding Behavior
     * Mix, which includes Behavior Models that result from a specific
     * clustering strategy being applied to the input models.
     *
     * @param behaviorModelsRelative
     *     the set of input Behavior Models to be transformed.
     * @param clusteringType
     *     one of the <code>CLUSTERING_TYPE</code> constants in class
     *     {@link RBMToRBMUnifier}; if <code>null</code> or any invalid type
     *     value is passed, the default clustering type "no-clustering" will
     *     be used, and a warning will be given.
     * @param useCaseRepository
     *     repository which contains all use cases of the input Behavior Models.
     *
     * @return
     *     the Behavior Mix which results from the transformation.
     *
     * @throws ExtractionException
     *     if any error during the transformation process occurs.
     */
    public BehaviorMix transform (
            final BehaviorModelAbsolute[] behaviorModelsAbsolute,
            final String clusteringType,
            final UseCaseRepository useCaseRepository,
            final String outputDirectory)
                    throws ExtractionException {

        final AbstractClusteringStrategy clusteringStrategy =
                this.getClusteringStrategy(clusteringType);

        return clusteringStrategy.apply(
        		behaviorModelsAbsolute,
                useCaseRepository,
                outputDirectory);
    }


    /* **************************  private methods  ************************* */


    /**
     * Returns a clustering strategy instance, which corresponds the given
     * clustering type.
     *
     * @param clusteringType
     *     one of the <code>CLUSTERING_TYPE</code> constants in class
     *     {@link RBMToRBMUnifier}; if <code>null</code> or any invalid type
     *     value is passed, the default clustering type "no-clustering" will
     *     be used, and a warning will be given.
     *
     * @return
     *     a clustering strategy instance, which corresponds to the given
     *     clustering type.
     */
    private AbstractClusteringStrategy getClusteringStrategy (
            String clusteringType) {

        // to be returned;
        final AbstractClusteringStrategy clusteringStrategy;

        if (clusteringType == null) {

            // use no clustering by default;
            clusteringType = RBMToRBMUnifier.CLUSTERING_TYPE_NONE;
        }

        switch ( clusteringType.trim().toLowerCase() ) {

            case RBMToRBMUnifier.CLUSTERING_TYPE_NONE:

                clusteringStrategy = new NoClusteringStrategy();
                break;

            case RBMToRBMUnifier.CLUSTERING_TYPE_SIMPLE:

                clusteringStrategy = new SimpleClusteringStrategy();
                break;

            case RBMToRBMUnifier.CLUSTERING_TYPE_KMEANS:

                clusteringStrategy = new KMeansClusteringStrategy();
                break;
                
            case RBMToRBMUnifier.CLUSTERING_TYPE_XMEANS:

                clusteringStrategy = new XMeansClusteringStrategy();
                break;

            default:

                final String message = String.format(
                        RBMToRBMUnifier.WARNING_UNKNOWN_CLUSTERING_METHOD,
                        clusteringType);

                System.out.println(message);

                // use no clustering by default;
                clusteringStrategy = new NoClusteringStrategy();
        }

        return clusteringStrategy;
    }
}
