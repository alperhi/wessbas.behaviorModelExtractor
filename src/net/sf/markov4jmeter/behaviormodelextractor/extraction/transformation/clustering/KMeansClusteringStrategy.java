package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.CommandLineArgumentsHandler;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.ABMToRBMTransformer;

/**
 * This class represents a <i>Menascé-based</i> clustering strategy.
 * 
 * @author Christian Voegele (voegele@fortiss.org)
 * @version 1.0
 */
public class KMeansClusteringStrategy extends AbstractClusteringStrategy {	
	
	private double sampleMeanInterCluster = 0;
	private double sampleVarianceInterCluster = 0;
	private double sampleCoefficientOfVarianceInterCluster = 0;
	private double sampleMeanIntraCluster = 0;
	private double sampleVarianceIntraCluster = 0;
	private double sampleCoefficientOfVarianceIntraCluster = 0;
	private double betacv = 0;
	private double betavar = 0;

	/* ************************** public methods ************************** */

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This method is specialized for <b>Menascé-based</b> clustering.
	 */
    @Override
    public BehaviorMix apply (
            final BehaviorModelAbsolute[] behaviorModelsAbsolute,
            final UseCaseRepository useCaseRepository) {

        final ABMToRBMTransformer abmToRbmTransformer =
                new ABMToRBMTransformer();
        
		// Behavior Mix to be returned;
		final BehaviorMix behaviorMix = this.createBehaviorMix();

		try {

			// Returns a valid instances set, generated based on the absolut
			// behavior models
			Instances instances = getInstances(behaviorModelsAbsolute); 
			
			// save input data as arff file. This arff file can be opened with weka application.
			ArffSaver saver = new ArffSaver();
			saver.setInstances(instances);
			saver.setFile(new File(CommandLineArgumentsHandler.getOutputDirectory() + "/data_clustering.arff"));
			saver.writeBatch();	
		
			// KMeans --> Weka
			SimpleKMeans kmeans = new SimpleKMeans();
		
			kmeans.setDontReplaceMissingValues(false);
			kmeans.setPreserveInstancesOrder(true);
			
			DistanceFunction euclideanDistance = new EuclideanDistance();		
			String[] options = new String[1];
			options[0] = "-D";
			euclideanDistance.setInstances(instances);
			euclideanDistance.setOptions(options);			
			kmeans.setDistanceFunction(euclideanDistance);
			
			int[] clustersize = null;
			int[] assignments = null;
			
			System.out.println("Cluster" + ";" +
					"SqrtError" + ";" +
					"sampleMeanInterCluster" + ";" +
					"sampleVarianceInterCluster" + ";" +
					"sampleCoefficientOfVarianceInterCluster" + ";" +
					"sampleMeanIntraCluster" + ";" +
					"sampleVarianceIntraCluster" + ";" +
					"sampleCoefficientOfVarianceIntraCluster" + ";" +
					"betavar" + ";" +
					"betacv"
					); 
			
			// TODO : Metriken berechnen: AVG Session length, Histogramm TreeMap
		
			for (int cnt = 6; cnt <= 6; cnt++) {
				// must be specified in a fix way
				kmeans.setNumClusters(cnt);

				// build cluster
				kmeans.buildClusterer(instances);

				clustersize = kmeans.getClusterSizes();
				assignments = kmeans.getAssignments();		
				
				this.calculateInterClusteringSimilarity(kmeans.getClusterCentroids());

				this.calculateIntraClusteringSimilarity(kmeans.getClusterCentroids(), instances, assignments);
				
				this.calculateBetas();

				System.out.println(
						cnt + ";" + 
				        kmeans.getSquaredError() + ";" + 
				        this.sampleMeanInterCluster + ";" +
				        this.sampleVarianceInterCluster + ";" +
				        this.sampleCoefficientOfVarianceInterCluster + ";" + 
				        this.sampleMeanIntraCluster  + ";" + 
				        this.sampleVarianceIntraCluster + ";" +
				        this.sampleCoefficientOfVarianceIntraCluster + ";" +
				        this.betavar  + ";" + 
				        this.betacv 				        
				); 
				
				
				double sumAttributes = 0;
				double sumInstances = 0;
				double minSessionLength = 999999999;
				double maxSessionLength = 0;
				for (int i = 0; i < clustersize.length; i++) {
					for (int j = 0; j < assignments.length; j++) {
						if (assignments[j] == i) {
							double sum = 0;							
							for (int a = 0; a < instances.instance(j).numAttributes(); a++) {	
								    if (instances.instance(j).value(a) > 0) {
								    	sum += instances.instance(j).value(a);		
								    }													
							}
							if (sum > maxSessionLength) {
								maxSessionLength = sum;
							}								
							if (sum < minSessionLength) {
								minSessionLength = sum;
							}								
							sumAttributes += sum;
							sumInstances++;
						}
					}
					System.out.println("Clustersize " + ";" +  (double)clustersize[i]/(double)instances.numInstances() + 
							          " avg sessionLength " + sumAttributes/sumInstances + 
							          " min session length " + minSessionLength + 
							          " max session length " + maxSessionLength);
					sumInstances = 0;
					sumAttributes = 0;
					minSessionLength = 999999999;
					maxSessionLength = 0;					
				}			
			}			

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
				final BehaviorModelAbsolute behaviorModelAbsoluteCentroid = this
						.createBehaviorModelAbsoluteWithoutTransitions(useCaseRepository
								.getUseCases());

				// install the transitions in between vertices;
				this.installTransitions(behaviorModelsAbsolute, behaviorModelAbsoluteCentroid,
						centroid, assignments, i);
				
				// convert absolute to relative behaviorModel
		        final BehaviorModelRelative behaviorModelRelative =
		                abmToRbmTransformer.transform(behaviorModelAbsoluteCentroid); 

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

	/**
	 * Create new Transitions with the values calculated by the clustering.
	 * 
	 * @param behaviorModel
	 * @param centroid
	 * @param centroidInstances
	 * @param centroidIndex
	 */
	private void installTransitions(
			final BehaviorModelAbsolute[] behaviorModelsAbsolute,
			final BehaviorModelAbsolute behaviorModel,
			final Instance centroid,
			final int[] assignments, 
			final int centroidIndex) {

		final List<Vertex> vertices = behaviorModel.getVertices();
		int indexOfAttribute = 0;
		for (final Vertex srcVertex : vertices) {
			if (srcVertex.getUseCase() != null) {  // no final state
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
					final String srcUseCaseId = srcUseCase.getId();
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
							
							Transition transition =  this
										.findTransitionByUseCaseIDs(behaviorModelsAbsolute[i],
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
						
						for (BigDecimal timeDiff : timeDiffs) {
							newTransition.getTimeDiffs().add(timeDiff);
						}						
						
					}
					indexOfAttribute++;
				}
			} else {
                continue;  // skip final state ("$");
            }
		}
	}

	/**
	 * This method creates a new instance set based on the available
	 * behaviorModelsAbsolute.
	 * 
	 * @param behaviorModelsAbsolute
	 * @return instance set
	 */
	private Instances getInstances(
			BehaviorModelAbsolute[] behaviorModelsAbsolute) throws Exception {
		
		// init the fastVector with attributesNames from the first behaviorModel. 
		FastVector fastVector = getFastVector(behaviorModelsAbsolute[0]);
		
		// create empty instance set with the number of behaviorModelsRelative.
		Instances instances = new Instances(
				"BehaviorModelAbsoluteInstanceSet", fastVector,
				behaviorModelsAbsolute.length);
		
		// Each behaviorModelsRelative will be transformed to an instance. To do
		// that, that transition matrix will be
		// transformed in a vector. Set number of attributes of instance: n x (n +1) exit state
		// Matrix.
		for (BehaviorModelAbsolute behaviorModelAbsolute : behaviorModelsAbsolute) {
			// retieve instance from behaviorModelRelative
			Instance instance = getInstance(behaviorModelAbsolute);
			// not sure if we have to set that reference?
			instance.setDataset(instances);
			// add instance to instanceset, at the end of the set
			instances.add(instance);
		}

		// set the last attribute as class index 
		instances.setClassIndex(instances.numAttributes() -1);
		
		// Remove UseLess
//		weka.filters.unsupervised.attribute.RemoveUseless filterUseLess = new weka.filters.unsupervised.attribute.RemoveUseless();
//		filterUseLess.setInputFormat(instances);
//		Instances returnInstances = Filter.useFilter(instances, filterUseLess);

		// filter instances
		weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
		filter.setAttributeIndices("" + (instances.classIndex() + 1));
		filter.setInputFormat(instances);
		Instances filteredInstances = Filter.useFilter(instances, filter);
		
		return filteredInstances;
	}

	/**
	 * Returns a instance as vector based on a single BehaviorModelAbsolute.
	 * 
	 * @param behaviorModelsAbsolute
	 * @return a instance, transitions of behaviorModelAbsolute as input vector
	 */
	private Instance getInstance(BehaviorModelAbsolute behaviorModelAbsolute) {
		
		// create new instance with size n x (n + 1)
		int nrVertices = behaviorModelAbsolute.getVertices()
				.size() -1 ; // -1 as srcVertex has one usecase with which is null (exit state)
		
		Instance instance = new Instance( (nrVertices * (nrVertices +1)) + 1); // +1 as dummyAttribute for classification
		
		final List<Vertex> vertices = behaviorModelAbsolute.getVertices();
		int indexOfAttribute = 0;
		for (final Vertex srcVertex : vertices) {					
			if (srcVertex.getUseCase() != null) {  // no final state
				// for each transition set the value of the instance vector				
				for (final Vertex dstVertex : vertices) {					
					final UseCase srcUseCase = srcVertex.getUseCase();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String srcUseCaseId = srcUseCase.getId();
					
					// if dstUseCase is null, its vertex denotes the final state
					// (no ID);
					final String dstUseCaseId = (dstUseCase != null) ? dstUseCase
							.getId() : null;
					Transition transition = this
								.findTransitionByUseCaseIDs(behaviorModelAbsolute,
										srcUseCaseId, dstUseCaseId);									
									
					if (transition != null) {						
						instance.setValue(indexOfAttribute, transition.getValue());
					}
					else {
						instance.setValue(indexOfAttribute, 0);
					}
					indexOfAttribute++;
				}
			 } else {
	                continue;  // skip final state ("$");
	         }
		}
		
		// set dummy attribute for classification, will be removed by the instance filter
		instance.setValue(indexOfAttribute, 5);		
		return instance;
	}

	/**
	 * Returns a FactVector which defines the attributes of the Instance set.
	 * 
	 * @param behaviorModelRelative
	 * @return FastVector defines the attributes of the Instance set
	 */
	private FastVector getFastVector(BehaviorModelAbsolute behaviorModelAbsolute) {
		FastVector fastVector = new FastVector();
		final List<Vertex> vertices = behaviorModelAbsolute.getVertices();
		for (final Vertex srcVertex : vertices) {
			 if (srcVertex.getUseCase() != null) {  // no final state
				for (final Vertex dstVertex : vertices) {
					final UseCase srcUseCase = srcVertex.getUseCase();
					final String srcUseCaseName = srcUseCase.getName();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String dstUseCaseName = (dstUseCase != null) ? dstUseCase.getName()
							 : "exit";
									
					// set AttributeName
					fastVector.addElement(new Attribute(srcUseCaseName
							+ dstUseCaseName, fastVector.size()));
     			}
            } else {
                continue;  // skip final state ("$");
            }
		}


		// Add dummyClassification. A valid instance set always seams to need a
		// classification attribute. Otherwise the clustering is not working. 
		FastVector dummyClassification = new FastVector(2);
		dummyClassification.addElement("a");
		dummyClassification.addElement("b");
		Attribute classAttribute = new Attribute("theClass",
				dummyClassification);
		fastVector.addElement(classAttribute);

		return fastVector;
	}
	
	/**
	 * Calculates the distance between clusters. 
	 * 
	 * @param centroids
	 * @return
	 */
	private void calculateInterClusteringSimilarity(Instances centroids) {		
		DistanceFunction euclideanDistance = new EuclideanDistance();
		euclideanDistance.setInstances(centroids);
		
		double k = (double) centroids.numInstances();		
		double sumDistance = 0;		
		double sumVariance = 0;
		
		for (int i = 0; i < k ; i++) {
			for (int j = i + 1; j < k ; j++) {
				sumDistance += euclideanDistance.distance(centroids.instance(i), centroids.instance(j));
			}
		}		

		this.sampleMeanInterCluster =  (1 /  ( k * ( k - 1 ) / 2 ) )   * sumDistance;	

		for (int i = 0; i < k ; i++) {
			for (int j = i + 1; j < k ; j++) {
				sumVariance += Math.pow( ( euclideanDistance.distance(centroids.instance(i), centroids.instance(j)) - this.sampleMeanInterCluster ) , 2);
			}
		}
		
		this.sampleVarianceInterCluster =  ( 1 /  ( ( k * ( k - 1 ) / 2 ) - 1 ) )  * sumVariance;	
				
		this.sampleCoefficientOfVarianceInterCluster = Math.sqrt(this.sampleVarianceInterCluster) / this.sampleMeanInterCluster;

	}
	
	/**
	 *  Calculates the distance within a cluster. 
	 * 
	 * @param centroids
	 * @return
	 */
	private void calculateIntraClusteringSimilarity(Instances centroids, Instances instances, int[] assignments) {		
		DistanceFunction euclideanDistance = new EuclideanDistance();
		euclideanDistance.setInstances(instances);
		
		double[] avgIntraClusterSimilarity = new double[centroids.numInstances()];
		double k = (double) centroids.numInstances() ;		
		double sumDistance = 0;		
		double counter = 0;
		double sumDistanceAllClusters = 0;
		double sumVariance = 0;
		
		for (int i = 0; i < k ; i++) {
			for (int j = 0; j < instances.numInstances() ; j++) {
				if (assignments[j] == i) {					
					sumDistance += euclideanDistance.distance(instances.instance(j), centroids.instance(i));
					counter += 1;
				}				
			}
			avgIntraClusterSimilarity[i] = (1 / counter) * sumDistance;
			sumDistance = 0;		
			counter = 0;
		}			

		for (double clusterDistance : avgIntraClusterSimilarity) {
			sumDistanceAllClusters += clusterDistance;
		}

		this.sampleMeanIntraCluster = ( 1 / k ) * sumDistanceAllClusters;	

		for (int i = 0; i < k ; i++) {
			sumVariance += Math.pow( (avgIntraClusterSimilarity[i] - this.sampleMeanIntraCluster) , 2);
		}
		
		this.sampleVarianceIntraCluster = ( 1 / ( k - 1 ) ) * sumVariance;	
				
		this.sampleCoefficientOfVarianceIntraCluster = Math.sqrt(this.sampleVarianceIntraCluster ) / this.sampleMeanIntraCluster;

	}
	
	/**
	 *  calculateBetas beta values.
	 */
	private void calculateBetas () {		
		this.betavar = this.sampleVarianceIntraCluster / this.sampleVarianceInterCluster;
		this.betacv = this.sampleCoefficientOfVarianceIntraCluster / this.sampleCoefficientOfVarianceInterCluster;
	}

}