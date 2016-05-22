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
import net.sf.markov4jmeter.behaviormodelextractor.CommandLineArgumentsHandler;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.ABMToRBMTransformer;
import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This class represents a <i>kmeans</i> clustering strategy.
 * 
 * @author Christian Voegele (voegele@fortiss.org)
 * @version 1.0
 */
public class KMeansClusteringStrategy extends AbstractClusteringStrategy {

	/* ************************** public methods ************************** */

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This method is specialized for <b>kmeans</b> clustering.
	 */
	@Override
	public BehaviorMix apply(
			final BehaviorModelAbsolute[] behaviorModelsAbsolute,
			final UseCaseRepository useCaseRepository) {

		final ABMToRBMTransformer abmToRbmTransformer = new ABMToRBMTransformer();

		// Behavior Mix to be returned;
		final BehaviorMix behaviorMix = this.createBehaviorMix();

		try {

			// Returns a valid instances set, generated based on the absolut
			// behavior models
			Instances instances = getInstances(behaviorModelsAbsolute);

			// KMeans --> Weka
			SimpleKMeans kmeans = new SimpleKMeans();

			// DistanceFunction manhattanDistance = new ManhattanDistance();
			// String[] options = new String[1];
			// options[0] = "-D";
			// manhattanDistance.setOptions(options);
			// manhattanDistance.setInstances(instances);
			// kmeans.setDistanceFunction(manhattanDistance);

			// distance function with option don*t normalize
			DistanceFunction euclideanDistance = new EuclideanDistance();
			// String[] options = new String[1];
			// options[0] = "-D";
			// euclideanDistance.setOptions(options);
			euclideanDistance.setInstances(instances);
			kmeans.setDistanceFunction(euclideanDistance);
			kmeans.setPreserveInstancesOrder(true);

			int[] clustersize = null;
			int[] assignments = null;

			// get number of clusters to be generated.
			int numberOfClusters = Integer.parseInt(CommandLineArgumentsHandler
					.getNumberOfClustersMin());

			// clustering
			for (int clusterSize = numberOfClusters; clusterSize <= numberOfClusters; clusterSize++) {
				// must be specified in a fix way
				kmeans.setNumClusters(clusterSize);

				// build cluster
				kmeans.buildClusterer(instances);

				clustersize = kmeans.getClusterSizes();
				assignments = kmeans.getAssignments();

				ClusteringMetrics clusteringMetrics = new ClusteringMetrics();
				clusteringMetrics.calculateInterClusteringSimilarity(kmeans
						.getClusterCentroids());
				clusteringMetrics.calculateIntraClusteringSimilarity(
						kmeans.getClusterCentroids(), instances, assignments);
				clusteringMetrics.calculateBetas();

				clusteringMetrics.printErrorMetricsHeader();
				clusteringMetrics.printErrorMetrics(kmeans
						.getClusterCentroids().numInstances());
				clusteringMetrics.printClusteringMetrics(clustersize,
						assignments, instances);
				// clusteringMetrics.printClusterAssignmentsToSession(assignments,
				// clusterSize);

			}

			Instances resultingCentroids = kmeans.getClusterCentroids();

			// for each centroid instance, create new behaviorModelRelative
			for (int i = 0; i < resultingCentroids.numInstances(); i++) {

				Instance centroid = resultingCentroids.instance(i);

				// create a Behavior Model, which includes all vertices only;
				// the vertices are associated with the use cases, and a
				// dedicated
				// vertex that represents the final state will be added;
				final BehaviorModelAbsolute behaviorModelAbsoluteCentroid = this
						.createBehaviorModelAbsoluteWithoutTransitions(useCaseRepository
								.getUseCases());

				// install the transitions in between vertices;
				this.installTransitions(behaviorModelsAbsolute,
						behaviorModelAbsoluteCentroid, centroid, assignments, i);

				// convert absolute to relative behaviorModel
				final BehaviorModelRelative behaviorModelRelative = abmToRbmTransformer
						.transform(behaviorModelAbsoluteCentroid);

				// relative Frequency of cluster i
				double relativeFrequency = (double) clustersize[i]
						/ (double) instances.numInstances();

				// create the (unique) Behavior Mix entry to be returned;
				final BehaviorMixEntry behaviorMixEntry = this
						.createBehaviorMixEntry(
								AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME,
								relativeFrequency, // relative frequency;
								behaviorModelRelative);

				// add to resulting behaviorMix
				behaviorMix.getEntries().add(behaviorMixEntry);

			}

			return behaviorMix;

		} catch (ExtractionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// if any error occurs, an ExtractionExeption should be thrown,
		// indicating the error that occurred;

		// the classes "NoClusteringStrategy" and "SimpleClusteringStrategy"
		// should give an idea for handling the Behavior Models and how to
		// use the helping methods of the (abstract) parent class.

		return behaviorMix;
	}

}
