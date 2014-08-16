package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.markov4jmeter.behaviormodelextractor.CommandLineArgumentsHandler;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instances;

/**
 * This class represents the metrics of a clustering. 
 * 
 * @author Christian Voegele (voegele@fortiss.org)
 * @version 1.0
 */
public class ClusteringMetrics {

	private double sampleMeanInterCluster = 0;
	private double sampleVarianceInterCluster = 0;
	private double sampleCoefficientOfVarianceInterCluster = 0;
	private double sampleMeanIntraCluster = 0;
	private double sampleVarianceIntraCluster = 0;
	private double sampleCoefficientOfVarianceIntraCluster = 0;
	private double betacv = 0;
	private double betavar = 0;
	
	/**
	 * Calculates the distance between clusters. 
	 * 
	 * @param centroids
	 * @return
	 */
	protected void calculateInterClusteringSimilarity(Instances centroids) {		
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
	protected void calculateIntraClusteringSimilarity(Instances centroids, Instances instances, int[] assignments) {		
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
	protected void calculateBetas () {		
		this.betavar = this.sampleVarianceIntraCluster / this.sampleVarianceInterCluster;
		this.betacv = this.sampleCoefficientOfVarianceIntraCluster / this.sampleCoefficientOfVarianceInterCluster;
	}
	
	 /**
     * Print Names of ErrorMetrics.
     */
	protected void printErrorMetricsHeader() {
		System.out.println("Cluster" + ";" +
				"sampleMeanInterCluster" + ";" +
				"sampleVarianceInterCluster" + ";" +
				"sampleCoefficientOfVarianceInterCluster" + ";" +
				"sampleMeanIntraCluster" + ";" +
				"sampleVarianceIntraCluster" + ";" +
				"sampleCoefficientOfVarianceIntraCluster" + ";" +
				"betavar" + ";" +
				"betacv"
				); 
    }
    
	protected void printErrorMetrics(final int cnt) {
    	System.out.println(
				cnt + ";" + 
		        this.sampleMeanInterCluster + ";" +
		        this.sampleVarianceInterCluster + ";" +
		        this.sampleCoefficientOfVarianceInterCluster + ";" + 
		        this.sampleMeanIntraCluster  + ";" + 
		        this.sampleVarianceIntraCluster + ";" +
		        this.sampleCoefficientOfVarianceIntraCluster + ";" +
		        this.betavar  + ";" + 
		        this.betacv 				        
		); 
    }    
    
    /**
     * Print other metrics.
     * 
     * @param clustersize
     * @param assignments
     * @param instances
     */
	protected void printClusteringMetrics(final int[] clustersize, final int[] assignments, final Instances instances) {
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
			System.out.println("Clustersize " + clustersize[i] + ";" +
					          " Clusterpercentage " + (double)clustersize[i]/(double)instances.numInstances() + ";" +
					          " avg sessionLength " + sumAttributes/sumInstances + ";" +
					          " min session length " + minSessionLength + ";" +
					          " max session length " + maxSessionLength);
			sumInstances = 0;
			sumAttributes = 0;
			minSessionLength = 999999999;
			maxSessionLength = 0;					
		}		
    }
	
	 /**
     * Create sessionFile with clusterInformation.
     * 
     * @param assignments
     */
    protected void printClusterAssignmentsToSession(final int[] assignments, final int clusterSize) {    	
    	try {
			FileReader fr = new FileReader(new File(CommandLineArgumentsHandler.getInputFile()));
            BufferedReader br = new BufferedReader(fr);  
	        String line = null;
	        int counter = 0;
	        List<String> tmpString = new ArrayList<String>();
	        while ( (line = br.readLine()) != null) {
	        	tmpString.add(assignments[counter] + ";" + line);
	        	counter++;
	        }	      
	        
	        FileWriter fw = new FileWriter(new File(CommandLineArgumentsHandler.getOutputDirectory() 
	        		+ "/data_clustering_" +  clusterSize + ".dat"));
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	        for (String stringInstance: tmpString) {
	        	bw.append(stringInstance);	
	        	bw.append("\n");
	        }
	        
	        tmpString = null;	        
	        bw.close();
	        br.close();	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
	
}
