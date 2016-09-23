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

import java.util.HashMap;

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

		for (int i = 0; i < k; i++) {
			for (int j = i + 1; j < k; j++) {
				sumDistance += euclideanDistance.distance(
						centroids.instance(i), centroids.instance(j));
			}
		}

		this.sampleMeanInterCluster = (1 / (k * (k - 1) / 2)) * sumDistance;

		for (int i = 0; i < k; i++) {
			for (int j = i + 1; j < k; j++) {
				sumVariance += Math
						.pow((euclideanDistance.distance(centroids.instance(i),
								centroids.instance(j)) - this.sampleMeanInterCluster),
								2);
			}
		}

		this.sampleVarianceInterCluster = (1 / ((k * (k - 1) / 2) - 1))
				* sumVariance;

		this.sampleCoefficientOfVarianceInterCluster = Math
				.sqrt(this.sampleVarianceInterCluster)
				/ this.sampleMeanInterCluster;

	}

	/**
	 * Calculates the distance within a cluster.
	 * 
	 * @param centroids
	 * @return
	 */
	protected void calculateIntraClusteringSimilarity(Instances centroids,
			Instances instances, int[] assignments) {
		DistanceFunction euclideanDistance = new EuclideanDistance();
		euclideanDistance.setInstances(instances);

		double[] avgIntraClusterSimilarity = new double[centroids
				.numInstances()];
		double k = (double) centroids.numInstances();
		double sumDistance = 0;
		double counter = 0;
		double sumDistanceAllClusters = 0;
		double sumVariance = 0;

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < instances.numInstances(); j++) {
				if (assignments[j] == i) {
					sumDistance += euclideanDistance.distance(
							instances.instance(j), centroids.instance(i));
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

		this.sampleMeanIntraCluster = (1 / k) * sumDistanceAllClusters;

		for (int i = 0; i < k; i++) {
			sumVariance += Math
					.pow((avgIntraClusterSimilarity[i] - this.sampleMeanIntraCluster),
							2);
		}

		this.sampleVarianceIntraCluster = (1 / (k - 1)) * sumVariance;

		this.sampleCoefficientOfVarianceIntraCluster = Math
				.sqrt(this.sampleVarianceIntraCluster)
				/ this.sampleMeanIntraCluster;

	}

	/**
	 * calculateBetas beta values.
	 */
	protected void calculateBetas() {
		this.betavar = this.sampleVarianceIntraCluster
				/ this.sampleVarianceInterCluster;
		this.betacv = this.sampleCoefficientOfVarianceIntraCluster
				/ this.sampleCoefficientOfVarianceInterCluster;
	}

	/**
	 * Print Names of ErrorMetrics.
	 */
	protected void printErrorMetricsHeader() {
		System.out.println("Cluster" + ";" + "sampleMeanInterCluster" + ";"
				+ "sampleVarianceInterCluster" + ";"
				+ "sampleCoefficientOfVarianceInterCluster" + ";"
				+ "sampleMeanIntraCluster" + ";" + "sampleVarianceIntraCluster"
				+ ";" + "sampleCoefficientOfVarianceIntraCluster" + ";"
				+ "betavar" + ";" + "betacv");
	}

	protected void printErrorMetrics(final int cnt) {
		System.out.println(cnt + ";" + this.sampleMeanInterCluster + ";"
				+ this.sampleVarianceInterCluster + ";"
				+ this.sampleCoefficientOfVarianceInterCluster + ";"
				+ this.sampleMeanIntraCluster + ";"
				+ this.sampleVarianceIntraCluster + ";"
				+ this.sampleCoefficientOfVarianceIntraCluster + ";"
				+ this.betavar + ";" + this.betacv);
	}

	/**
	 * Print other metrics.
	 * 
	 * @param clustersize
	 * @param assignments
	 * @param instances
	 */
	protected void printClusteringMetrics(final int[] clustersize,
			final int[] assignments, final Instances instances) {
		double sumAttributes = 0;
		double sumInstances = 0;
		double minSessionLength = 999999999;
		double maxSessionLength = 0;
		HashMap<String, Double> attributeMap = new HashMap<String, Double>();
		for (int i = 0; i < clustersize.length; i++) {
			for (int j = 0; j < assignments.length; j++) {
				if (assignments[j] == i) {
					double sum = 0;
					for (int a = 0; a < instances.instance(j).numAttributes(); a++) {
						if (instances.instance(j).value(a) > 0) {
							int instr = instances.instance(j).attribute(a)
									.name().indexOf("_");
							String attributeNameTemp = instances.instance(j)
									.attribute(a).name().substring(0, instr);
							if (attributeMap.get(attributeNameTemp) == null) {
								attributeMap.put(attributeNameTemp, instances
										.instance(j).value(a));
							} else {
								double value = attributeMap
										.get(attributeNameTemp);
								attributeMap.put(attributeNameTemp, value
										+ instances.instance(j).value(a));
							}
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
			System.out.println("Clustersize " + clustersize[i] + ";"
					+ " Clusterpercentage " + (double) clustersize[i]
					/ (double) instances.numInstances() + ";"
					+ " avg sessionLength " + sumAttributes / sumInstances
					+ ";" + " min session length " + minSessionLength + ";"
					+ " max session length " + maxSessionLength);

			sumInstances = 0;
			sumAttributes = 0;
			minSessionLength = 999999999;
			maxSessionLength = 0;
			attributeMap.clear();
		}
	}

}
