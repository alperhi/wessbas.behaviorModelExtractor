package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.util.List;

import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;

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
        
        try {
        	       	
        	// Returns a valid instances set, generated based on the relative behavior models 
        	Instances instances = getInstances(behaviorModelsRelative);     
        	
        	// KMeans --> Weka
        	SimpleKMeans kmeans = new SimpleKMeans();
        	
        	// set option -init, 0 = default kmeans, 1 = kmeans++,, apparently this makes no difference in the results
        	String[] options = new String[2];
        	options[0] = "-init";
        	options[1] = "0";
        	
        	kmeans.setOptions(options);
        	
        	// must be specified in a fix way
        	kmeans.setNumClusters(3);
        	
        	// build cluster
        	kmeans.buildClusterer(instances);
        	
        	//  inter-cluster similarity
        	kmeans.getSquaredError();
        	
        	// intra cluster similarity --> must be implemented
        	
        	// prints resulting centroids
        	// these results are not yet tested regarding validity
      	    Instances resultingCentroids = kmeans.getClusterCentroids();
      	    int[] clustersize = kmeans.getClusterSizes();
      	    for (int i = 0; i < clustersize.length; i++) {
      	    	System.out.println("Cluster " + i+1 + " size " + clustersize[i]);
      	    }
      	 
      	    for (int i = 0; i < resultingCentroids.numInstances(); i++) {
      	    	Instance centroid = resultingCentroids.instance(i);      	    	      	    	
      	    	for (int s = 0; s < centroid.numAttributes(); s++) {      	    	
      	    		System.out.println("Attribute nr: " + s + " " +  centroid.value(s));
      	    	}      	    	      	    	
      	    }
			
		} catch (ExtractionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // TODO: transform centroids to BehaviorModelRelative
        // TODO: Check the calculation of the centriods, Kmeans calculates the mean <---> Formula Menascé
        // TODO: Calculate  think time matrix for each centroid
        
        // if any error occurs, an ExtractionExeption should be thrown,
        // indicating the error that occurred;

        // the classes "NoClusteringStrategy" and "SimpleClusteringStrategy"
        // should give an idea for handling the Behavior Models and how to
        // use the helping methods of the (abstract) parent class.

        return behaviorMix;
    }
    
    /**
     * This method creates a new instance set based on the available BehaviorModelRelative.
     * 
     * @param behaviorModelsRelative
     * @return
     * 			instance set
     */
    private Instances getInstances(BehaviorModelRelative[] behaviorModelsRelative) throws Exception{   	   	
    	
    	// init the fastVector with attributesNames
    	FastVector fastVector = getFastVector(behaviorModelsRelative[0]);
    	
    	// create empty instance set with the number of behaviorModelsRelative. 
    	Instances instances = new Instances("BehaviorModelReleativeInstanceSet", fastVector, behaviorModelsRelative.length);
    	    	
    	// each behaviorModelsRelative will be transformed to an instance. To do that, that transition matrix will be
    	// transformed in a vector. Set number of attributes of instance: n x n Matrix.         
    	for (BehaviorModelRelative behaviorModelRelative:behaviorModelsRelative) {
    		
    		// retieve instance from behaviorModelRelative
    		Instance instance = getInstance(behaviorModelRelative);		
    		
    		// not sure if we have to set that reference?
    		instance.setDataset(instances); 
    		
    		// add instance to instanceset
    		instances.add(instance);    		
    	}    	  	
    	
    	// set class index -1 as the last attribute is always a classification attribute
    	instances.setClassIndex(instances.numAttributes() - 1);
    	
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
     * @return 
     * 	a instance, transitions of behaviorModelRelativ as input vector 
     */
    private Instance getInstance(BehaviorModelRelative behaviorModelRelative) {
    	
    	// create new instance with size n x n 
    	Instance instance = new Instance(behaviorModelRelative.getVertices().size() * (behaviorModelRelative.getVertices().size()));
    	
    	final List<Vertex> vertices = behaviorModelRelative.getVertices();    	
    	
    	int counter = 0;
    	
    	for (final Vertex srcVertex : vertices) {
    		
    		if (srcVertex.getUseCase() != null) {  // no final state?
    		
    			// for each transition set the value of the instance vector
	    		for (final Vertex dstVertex : vertices) {
	    			 
	    			 final UseCase srcUseCase = srcVertex.getUseCase();
	    		     final UseCase dstUseCase = dstVertex.getUseCase();
	    		     
	    		     final String srcUseCaseId = srcUseCase.getId();	    		     
	    		        // if dstUseCase is null, its vertex denotes the final state (no ID);
	    		     final String dstUseCaseId =
	    		                (dstUseCase != null) ? dstUseCase.getId() : null;
	    			 
	    			 final Transition transition = this.findTransitionByUseCaseIDs(
	    	                    behaviorModelRelative,
	    	                    srcUseCaseId,
	    	                    dstUseCaseId); 
	    			 
	    			 if (transition == null) {
	    				 instance.setValue(counter, 0.0); 
	    			 } else {
	    				 instance.setValue(counter, transition.getValue()); 
	    			 }
	    			 
	    			 counter++;
	    		 
	    		 }   		
	    		
    		} else {
	               continue;  // skip final state ("$");
	        }	    		
    	}    	
    	return instance;
    }
    
    /**
     * Returns a FactVector which defines the attributes of the Instance set.
     * 
     * @param behaviorModelRelative
     * @return
     *     FastVector defines the attributes of the Instance set
     */
    private FastVector getFastVector(BehaviorModelRelative behaviorModelRelative) {
    	FastVector fastVector = new FastVector();    
    	
    	final List<Vertex> vertices = behaviorModelRelative.getVertices();   
    	
    	for (final Vertex srcVertex : vertices) {
    		
    		if (srcVertex.getUseCase() != null) {  // no final state?
    		
	    		for (final Vertex dstVertex : vertices) {	    			 
	    			 
	    			 final UseCase srcUseCase = srcVertex.getUseCase();
	    		     final UseCase dstUseCase = dstVertex.getUseCase();
	    		     
	    		     final String srcUseCaseId = srcUseCase.getId();	    		     
	    		        // if dstUseCase is null, its vertex denotes the final state (no ID);
	    		     final String dstUseCaseId =
	    		                (dstUseCase != null) ? dstUseCase.getId() : "noID";
	    			
	    		     fastVector.addElement(new Attribute(srcUseCaseId+dstUseCaseId, fastVector.size()));
	    		    
	    		 }   		
	    		
    		} else {

	               continue;  // skip final state ("$");
	                
	        }	    		
    	}    	
    	
    	// add dummyClassification. A valid instance set always seams to need a classification attribute. 
    	FastVector dummyClassification = new FastVector(2);
    	dummyClassification.addElement("a");
    	dummyClassification.addElement("b");
    	Attribute classAttribute = new Attribute("theClass", dummyClassification);
    	fastVector.addElement(classAttribute);
    	
    	return fastVector;
    }
    
}