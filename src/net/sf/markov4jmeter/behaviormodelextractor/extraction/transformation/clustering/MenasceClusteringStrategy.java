package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;

/**
 * This class represents a <i>Menascé-based</i> clustering strategy.
 * 
 * @author Christian Voegele (voegele@fortiss.org)
 * @version 1.0
 */
public class MenasceClusteringStrategy extends AbstractClusteringStrategy {

	/* ************************** public methods ************************** */

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This method is specialized for <b>Menascé-based</b> clustering.
	 */
	@Override
	public BehaviorMix apply(
			final BehaviorModelRelative[] behaviorModelsRelative,
			final UseCaseRepository useCaseRepository) {

		// Behavior Mix to be returned;
		final BehaviorMix behaviorMix = this.createBehaviorMix();

		try {

			// Returns a valid instances set, generated based on the relative
			// behavior models
			Instances instances = getInstances(behaviorModelsRelative);

			// KMeans --> Weka
			SimpleKMeans kmeans = new SimpleKMeans();

			// set option -init, 0 = default kmeans, 1 = kmeans++, apparently
			// this makes no difference in the results
			String[] options = new String[2];
			options[0] = "-init";
			options[1] = "0";

			kmeans.setOptions(options);
			kmeans.setPreserveInstancesOrder(true);

			// must be specified in a fix way
			kmeans.setNumClusters(3);

			// build cluster
			kmeans.buildClusterer(instances);

			// inter-cluster similarity
			kmeans.getSquaredError();

			// TODO: intra cluster similarity must be implemented

			int[] clustersize = kmeans.getClusterSizes();

			// returns array where each
			int[] assignments = kmeans.getAssignments();

			// prints resulting centroids
			// these results are not yet tested regarding validity
			Instances resultingCentroids = kmeans.getClusterCentroids();

			// for each centroid instance, create new behaviorModelRelative
			for (int i = 0; i < resultingCentroids.numInstances(); i++) {

				Instance centroid = resultingCentroids.instance(i);
				// create a Behavior Model, which includes all vertices only;
				// the
				// vertices are associated with the use cases, and a dedicated
				// vertex
				// that represents the final state will be added;
				final BehaviorModelRelative behaviorModel = this
						.createBehaviorModelWithoutTransitions(useCaseRepository
								.getUseCases());

				// install the transitions in between vertices;
				this.installTransitions(behaviorModelsRelative, behaviorModel,
						centroid, assignments, i);

				// relative Frequency of cluster i
				double relativeFrequency = (double) clustersize[i]
						/ (double) instances.numInstances();

				// create the (unique) Behavior Mix entry to be returned;
				final BehaviorMixEntry behaviorMixEntry = this
						.createBehaviorMixEntry(
								AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME,
								relativeFrequency, // relative frequency;
								behaviorModel);

				// add to resulting behaviorMix
				behaviorMix.getEntries().add(behaviorMixEntry);

			}

			return behaviorMix;

		} catch (ExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO: Check the calculation of the centriods, Kmeans calculates the
		// mean <---> Formula Menascé

		// if any error occurs, an ExtractionExeption should be thrown,
		// indicating the error that occurred;

		// the classes "NoClusteringStrategy" and "SimpleClusteringStrategy"
		// should give an idea for handling the Behavior Models and how to
		// use the helping methods of the (abstract) parent class.

		return behaviorMix;
	}

	/**
	 * Create new Transitions with the values calculated by the clustering.
	 * 
	 * @param behaviorModel
	 * @param centroid
	 * @param centroidInstances
	 * @param centroidIndex
	 */
	private void installTransitions(
			final BehaviorModelRelative[] behaviorModelsRelative,
			final BehaviorModelRelative behaviorModel, final Instance centroid,
			final int[] assignments, final int centroidIndex) {

		final List<Vertex> vertices = behaviorModel.getVertices();
		int indexOfAttribute = 0;
		for (final Vertex srcVertex : vertices) {
			if (srcVertex.getUseCase() != null) { // no final state?
				for (final Vertex dstVertex : vertices) {
					Transition newTransition = installTransition(srcVertex,
							dstVertex);
					
					// set Value for transition probability
					newTransition.setValue(centroid.value(indexOfAttribute));

					// CalculateThinkTimeParams. Think Times must be calculated
					// based on the original behaviorModelsRelative, as the
					// clusters are only build based on the transition
					// probabilities.

					// first get srcUseCaseId and dstUseCaseID
					final UseCase srcUseCase = srcVertex.getUseCase();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String srcUseCaseId = srcUseCase.getId(); // always defined here;
					// if dstUseCase is null, its vertex denotes the final state
					// (no ID);
					final String dstUseCaseId = (dstUseCase != null) ? dstUseCase
							.getId() : null;
					final LinkedList<BigDecimal> timeDiffs = new LinkedList<BigDecimal>();

					// iterate assignments
					for (int i = 0; i < assignments.length; i++) {
						// if assignment at position i is equal to
						// centroidIndex, then take the behaviorModelsRelative
						// at index i
						if (assignments[i] == centroidIndex) {
							// get transition of behaviorModel which belongs to
							// the cluster.
							Transition transition = this
									.findTransitionByUseCaseIDs(
											behaviorModelsRelative[i],
											srcUseCaseId, dstUseCaseId);

							if (transition != null) {

								// store tinkTimeDiffs
								timeDiffs.addAll(transition.getTimeDiffs());

							}
						}
					}

					if (timeDiffs.size() > 0) {
						// calculate new mean/deviation values, based on time
						// ranges;
						newTransition.getThinkTimeParams().add(
								MathUtil.computeMean(timeDiffs));
						newTransition.getThinkTimeParams().add(
								MathUtil.computeDeviation(timeDiffs));
					}
					indexOfAttribute++;
				}
			} else {
				continue; // skip final state ("$");
			}
		}
	}

	/**
	 * This method creates a new instance set based on the available
	 * BehaviorModelRelative.
	 * 
	 * @param behaviorModelsRelative
	 * @return instance set
	 */
	private Instances getInstances(
			BehaviorModelRelative[] behaviorModelsRelative) throws Exception {
		// init the fastVector with attributesNames
		FastVector fastVector = getFastVector(behaviorModelsRelative[0]);
		// create empty instance set with the number of behaviorModelsRelative.
		Instances instances = new Instances(
				"BehaviorModelReleativeInstanceSet", fastVector,
				behaviorModelsRelative.length);
		// each behaviorModelsRelative will be transformed to an instance. To do
		// that, that transition matrix will be
		// transformed in a vector. Set number of attributes of instance: n x (n +1) exit state
		// Matrix.
		for (BehaviorModelRelative behaviorModelRelative : behaviorModelsRelative) {
			// retieve instance from behaviorModelRelative
			Instance instance = getInstance(behaviorModelRelative);
			// not sure if we have to set that reference?
			instance.setDataset(instances);
			// add instance to instanceset, at the end of the set
			instances.add(instance);
		}

		// set  the last attribute as class index 
		instances.setClassIndex(instances.numAttributes() -1);

		// filter instances
		weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
		filter.setAttributeIndices("" + (instances.classIndex() + 1));
		filter.setInputFormat(instances);
		Instances returnInstances = Filter.useFilter(instances, filter);

		return returnInstances;
	}

	/**
	 * Returns a instance as vector based on a single BehaviorModelRelative.
	 * 
	 * @param behaviorModelsRelative
	 * @return a instance, transitions of behaviorModelRelativ as input vector
	 */
	private Instance getInstance(BehaviorModelRelative behaviorModelRelative) {
		
		// create new instance with size n x (n + 1)
		int nrVertices = behaviorModelRelative.getVertices()
				.size() - 1; // -1 as srcVertex has one usecase with which is null (exit state)
		
		Instance instance = new Instance( (nrVertices * ( nrVertices + 1)) + 1 ); // +1 as dummyAttribute for classification
		
		final List<Vertex> vertices = behaviorModelRelative.getVertices();
		int indexOfAttribute = 0;
		for (final Vertex srcVertex : vertices) {					
			if (srcVertex.getUseCase() != null) { // no final state?
				// for each transition set the value of the instance vector				
				for (final Vertex dstVertex : vertices) {					
					final UseCase srcUseCase = srcVertex.getUseCase();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String srcUseCaseId = srcUseCase.getId();
					// if dstUseCase is null, its vertex denotes the final state
					// (no ID);
					final String dstUseCaseId = (dstUseCase != null) ? dstUseCase
							.getId() : null;
					final Transition transition = this
							.findTransitionByUseCaseIDs(behaviorModelRelative,
									srcUseCaseId, dstUseCaseId);
					if (transition == null) {
						instance.setValue(indexOfAttribute, 0.0);
					} else {
						instance.setValue(indexOfAttribute, transition.getValue());
					}
					indexOfAttribute++;
				}
			} else {
				continue; // skip final state ("$");
			}
		}
		
		// set dummy attribute for classification, will be removed by the instance filter
		instance.setValue(indexOfAttribute, 0);		
		return instance;
	}

	/**
	 * Returns a FactVector which defines the attributes of the Instance set.
	 * 
	 * @param behaviorModelRelative
	 * @return FastVector defines the attributes of the Instance set
	 */
	private FastVector getFastVector(BehaviorModelRelative behaviorModelRelative) {
		FastVector fastVector = new FastVector();
		final List<Vertex> vertices = behaviorModelRelative.getVertices();
		for (final Vertex srcVertex : vertices) {
			if (srcVertex.getUseCase() != null) { // no final state?
				for (final Vertex dstVertex : vertices) {
					final UseCase srcUseCase = srcVertex.getUseCase();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String srcUseCaseId = srcUseCase.getId();
					// if dstUseCase is null, its vertex denotes the final state
					// (no ID);
					final String dstUseCaseId = (dstUseCase != null) ? dstUseCase
							.getId() : "noID";
					fastVector.addElement(new Attribute(srcUseCaseId
							+ dstUseCaseId, fastVector.size()));
     			}
			} else {
				continue; // skip final state ("$");
			}
		}


		// add dummyClassification. A valid instance set always seams to need a
		// classification attribute. Otherwise the clustering is not working. 
		FastVector dummyClassification = new FastVector(2);
		dummyClassification.addElement("a");
		dummyClassification.addElement("b");
		Attribute classAttribute = new Attribute("theClass",
				dummyClassification);
		fastVector.addElement(classAttribute);

		return fastVector;
	}

}