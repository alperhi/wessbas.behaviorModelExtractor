package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.CommandLineArgumentsHandler;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.ABMToRBMTransformer;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.XMeans;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * This class represents a <i>xmeans</i> clustering strategy.
 * 
 * @author Christian Voegele (voegele@fortiss.org)
 * @version 1.0
 */
public class XMeansClusteringStrategy extends AbstractClusteringStrategy {

	/* ************************** public methods ************************** */

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This method is specialized for <b>xmeans</b> clustering.
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

			// XMeans --> Weka
			XMeans xmeans = new XMeans();
			xmeans.setSeed(5);

			// distance function
			DistanceFunction euclideanDistance = new EuclideanDistance();
			// String[] options = new String[1];
			// options[0] = "-D";
			// euclideanDistance.setOptions(options);
			euclideanDistance.setInstances(instances);
			xmeans.setDistanceF(euclideanDistance);

			// DistanceFunction manhattanDistance = new ManhattanDistance();
			// String[] options = new String[1];
			// options[0] = "-D";
			// manhattanDistance.setOptions(options);
			// manhattanDistance.setInstances(instances);
			// xmeans.setDistanceF(manhattanDistance);

			int[] clustersize = null;
			// create new assignments
			int[] assignments = new int[instances.numInstances()];

			// get number of clusters to be generated.
			int numberOfClustersMin = Integer
					.parseInt(CommandLineArgumentsHandler
							.getNumberOfClustersMin());
			int numberOfClustersMax = 0;
			if (CommandLineArgumentsHandler.getNumberOfClustersMax() != "") {
				numberOfClustersMax = Integer
						.parseInt(CommandLineArgumentsHandler
								.getNumberOfClustersMax());
			} else {
				numberOfClustersMax = numberOfClustersMin;
			}

			// clustering
			xmeans.setMinNumClusters(numberOfClustersMin);
			xmeans.setMaxNumClusters(numberOfClustersMax);

			// build cluster
			xmeans.buildClusterer(instances);

			ClusterEvaluation clusterEvaluation = new ClusterEvaluation();
			clusterEvaluation.setClusterer(xmeans);
			clusterEvaluation.evaluateClusterer(instances);

			// clusterSize
			clustersize = new int[xmeans.getClusterCenters().numInstances()];

			// set assignments and clustersize
			for (int s = 0; s < instances.numInstances(); s++) {
				assignments[s] = xmeans.clusterInstance(instances.instance(s));
				clustersize[xmeans.clusterInstance(instances.instance(s))]++;
			}

			ClusteringMetrics clusteringMetrics = new ClusteringMetrics();
			clusteringMetrics.calculateInterClusteringSimilarity(xmeans
					.getClusterCenters());
			clusteringMetrics.calculateIntraClusteringSimilarity(
					xmeans.getClusterCenters(), instances, assignments);
			clusteringMetrics.calculateBetas();

			clusteringMetrics.printErrorMetricsHeader();
			clusteringMetrics.printErrorMetrics(xmeans.getClusterCenters()
					.numInstances());
			clusteringMetrics.printClusteringMetrics(clustersize, assignments,
					instances);
			// clusteringMetrics.printClusterAssignmentsToSession(assignments,
			// xmeans.getClusterCenters().numInstances());

			Instances resultingCentroids = xmeans.getClusterCenters();

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