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

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
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
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;

/**
 * Abstract base class for all clustering strategies.
 * 
 * <p>
 * This class defines the abstract
 * {@link #apply(BehaviorModelRelative[], UseCaseRepository)} method, which has
 * to be implemented by each subclass, according to the associated clustering
 * strategy. Furthermore, helping methods for searching and creating Behavior
 * Model elements are provided.
 * 
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 * @version 1.0
 */
public abstract class AbstractClusteringStrategy {

	/* ***************************** constants **************************** */

	/**
	 * Generic name for generated Behavior Models; if multiple models are
	 * generated, an index will be appended to this name.
	 */
	protected final static String GENERIC_BEHAVIOR_MODEL_NAME = "gen_behavior_model";

	/* ************************** public methods ************************** */

	/**
	 * Applies the associated clustering strategy to a given set of Behavior
	 * Models.
	 * 
	 * @param behaviorModelsRelative
	 *            set of input Behavior Models on which the clustering strategy
	 *            shall be applied.
	 * @param useCaseRepository
	 *            use case repository which provides all available use cases;
	 *            the use cases generally indicate the states ("vertices") of
	 *            each Behavior Model, with a dedicated final state in addition.
	 *            Final states are not associated with any use case, indicated
	 *            by their regarding attribute being set to <code>null</code>.
	 * 
	 * @return the resulting Behavior Mix; each Behavior Mix entry includes a
	 *         (generated) name of its related Behavior Model, a computed
	 *         relative frequency and a reference to the Behavior Model itself.
	 * 
	 * @throws ExtractionException
	 *             if any clustering error occurs.
	 */
	public abstract BehaviorMix apply(
			final BehaviorModelAbsolute[] behaviorModelsAbsolute,
			final UseCaseRepository useCaseRepository,
			String outputDirectory)
			throws ExtractionException;

	/* ************************* protected methods ************************ */

	/**
	 * Searches for a Behavior Model transition between two use cases,
	 * respectively their associated vertices.
	 * 
	 * @param behaviorModelRelative
	 *            Behavior Model to be searched through.
	 * @param srcUseCaseId
	 *            identifier of the use case which is associated with the source
	 *            vertex.
	 * @param dstUseCaseId
	 *            identifier of the use case which is associated with the target
	 *            vertex; <code>null</code> might be passed for the final state.
	 * 
	 * @return a matching transition, or <code>null</code> if such a transition
	 *         does not exist.
	 */
	protected Transition findTransitionByUseCaseIDs(
			final BehaviorModelAbsolute behaviorModelAbsolute,
			final String srcUseCaseId, final String dstUseCaseId) {

		final Vertex srcVertex = this.findVertexByUseCaseId(
				behaviorModelAbsolute, srcUseCaseId);

		if (srcVertex != null) {

			for (final Transition transition : srcVertex
					.getOutgoingTransitions()) {

				final UseCase useCase = transition.getTargetVertex()
						.getUseCase();

				if ((useCase != null && useCase.getId().equals(dstUseCaseId))
						|| (useCase == null && dstUseCaseId == null)) {

					return transition;
				}
			}
		}

		return null; // no matching transition found;
	}

	/**
	 * Searches for a Behavior Model transition between two use cases,
	 * respectively their associated vertices.
	 * 
	 * @param behaviorModelRelative
	 *            Behavior Model to be searched through.
	 * @param srcUseCaseId
	 *            identifier of the use case which is associated with the source
	 *            vertex.
	 * @param dstUseCaseId
	 *            identifier of the use case which is associated with the target
	 *            vertex; <code>null</code> might be passed for the final state.
	 * 
	 * @return a matching transition, or <code>null</code> if such a transition
	 *         does not exist.
	 */
	protected Transition findTransitionByUseCaseIDs(
			final BehaviorModelRelative behaviorModelRelative,
			final String srcUseCaseId, final String dstUseCaseId) {

		final Vertex srcVertex = this.findVertexByUseCaseId(
				behaviorModelRelative, srcUseCaseId);

		if (srcVertex != null) {

			for (final Transition transition : srcVertex
					.getOutgoingTransitions()) {

				final UseCase useCase = transition.getTargetVertex()
						.getUseCase();

				if ((useCase != null && useCase.getId().equals(dstUseCaseId))
						|| (useCase == null && dstUseCaseId == null)) {

					return transition;
				}
			}
		}

		return null; // no matching transition found;
	}

	/**
	 * Searches for a Behavior Model vertex which is associated with a specific
	 * use case or with the final state.
	 * 
	 * @param behaviorModelRelative
	 *            Behavior Model whose vertices shall be searched through.
	 * @param useCaseId
	 *            an identifier of the use case which is associated with the
	 *            vertex to be searched, or <code>null</code> for searching the
	 *            vertex that represents the final state.
	 * 
	 * @return a matching vertex, or <code>null</code> if such a vertex does not
	 *         exist.
	 */
	protected Vertex findVertexByUseCaseId(
			final BehaviorModelRelative behaviorModelRelative,
			final String useCaseId) {

		final List<Vertex> vertices = behaviorModelRelative.getVertices();

		for (final Vertex vertex : vertices) {

			final UseCase vertexUseCase = vertex.getUseCase();

			if (vertexUseCase != null) {

				final String vertexUseCaseId = vertexUseCase.getId();

				if (useCaseId.equals(vertexUseCaseId)) {

					return vertex;
				}

			} else { // vertexUseCase == null --> final state found;

				if (useCaseId == null) { // searching for final state?

					return vertex;
				}
			}
		}

		return null; // no matching vertex found;
	}

	/**
	 * Searches for a Behavior Model Absolut vertex which is associated with a
	 * specific use case or with the final state.
	 * 
	 * @param behaviorModelAbsolut
	 *            Behavior Model whose vertices shall be searched through.
	 * @param useCaseId
	 *            an identifier of the use case which is associated with the
	 *            vertex to be searched, or <code>null</code> for searching the
	 *            vertex that represents the final state.
	 * 
	 * @return a matching vertex, or <code>null</code> if such a vertex does not
	 *         exist.
	 */
	protected Vertex findVertexByUseCaseId(
			final BehaviorModelAbsolute behaviorModelAbsolute,
			final String useCaseId) {

		final List<Vertex> vertices = behaviorModelAbsolute.getVertices();

		for (final Vertex vertex : vertices) {

			final UseCase vertexUseCase = vertex.getUseCase();

			if (vertexUseCase != null) {

				final String vertexUseCaseId = vertexUseCase.getId();

				if (useCaseId.equals(vertexUseCaseId)) {

					return vertex;
				}

			} else { // vertexUseCase == null --> final state found;

				if (useCaseId == null) { // searching for final state?

					return vertex;
				}
			}
		}

		return null; // no matching vertex found;
	}

	/**
	 * Creates a Behavior Model which includes the vertices for set of given use
	 * cases.
	 * 
	 * @param useCases
	 *            set of use cases which indicate the vertices to be included to
	 *            the Behavior Model; a vertex that represents the final state
	 *            will be added explicitly.
	 * 
	 * @return the newly created Behavior Model.
	 */
	protected BehaviorModelRelative createBehaviorModelRelativeWithoutTransitions(
			final List<UseCase> useCases) {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		final BehaviorModelRelative behaviorModel = factory
				.createBehaviorModelRelative(); // to be returned;

		final List<Vertex> vertices = behaviorModel.getVertices();

		Vertex vertex;

		for (final UseCase useCase : useCases) {

			vertex = factory.createVertex();

			vertex.setUseCase(useCase);
			vertices.add(vertex);
		}

		// add a vertex for the final state at last;

		vertex = factory.createVertex();

		vertex.setUseCase(null); // no use case associated with final state;
		vertices.add(vertex);

		return behaviorModel;
	}

	/**
	 * Creates a Behavior Model which includes the vertices for set of given use
	 * cases.
	 * 
	 * @param useCases
	 *            set of use cases which indicate the vertices to be included to
	 *            the Behavior Model; a vertex that represents the final state
	 *            will be added explicitly.
	 * 
	 * @return the newly created Behavior Model.
	 */
	protected BehaviorModelAbsolute createBehaviorModelAbsoluteWithoutTransitions(
			final List<UseCase> useCases) {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		final BehaviorModelAbsolute behaviorModel = factory
				.createBehaviorModelAbsolute(); // to be returned;

		final List<Vertex> vertices = behaviorModel.getVertices();

		Vertex vertex;

		for (final UseCase useCase : useCases) {

			vertex = factory.createVertex();

			vertex.setUseCase(useCase);
			vertices.add(vertex);
		}

		// add a vertex for the final state at last;

		vertex = factory.createVertex();

		vertex.setUseCase(null); // no use case associated with final state;
		vertices.add(vertex);

		return behaviorModel;
	}

	/**
	 * Creates an empty <code>BehaviorMix</code> instance.
	 * 
	 * @return the newly created instance without any Behavior Mix entries.
	 */
	protected BehaviorMix createBehaviorMix() {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		return factory.createBehaviorMix();
	}

	/**
	 * Creates a Behavior Mix entry for a given Behavior Model; the entry will
	 * include the model's (generated) name, a relative frequency and the model
	 * itself.
	 * 
	 * @param behaviorModelName
	 *            the (generated) name of the Behavior Model.
	 * @param frequency
	 *            a relative frequency.
	 * @param behaviorModel
	 *            the Behavior Model itself.
	 * 
	 * @return the newly created instance.
	 */
	protected BehaviorMixEntry createBehaviorMixEntry(
			final String behaviorModelName, final double frequency,
			final BehaviorModelRelative behaviorModel) {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		final BehaviorMixEntry behaviorMixEntry = factory
				.createBehaviorMixEntry(); // to be returned;

		behaviorMixEntry.setBehaviorModelName(behaviorModelName);
		behaviorMixEntry.setRelativeFrequency(MathUtil.round(frequency));
		behaviorMixEntry.setBehaviorModel(behaviorModel);

		return behaviorMixEntry;
	}

	/**
	 * Installs a new transition between two vertices; the (newly created)
	 * transition will be added to the outgoing transitions of the source
	 * vertex.
	 * 
	 * @param sourceVertex
	 *            source vertex of the transition.
	 * @param targetVertex
	 *            target vertex of the transition.
	 * 
	 * @return the installed transition.
	 */
	protected Transition installTransition(final Vertex sourceVertex,
			final Vertex targetVertex) {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;
		final Transition transition = factory.createTransition();

		transition.setTargetVertex(targetVertex);
		transition.setSourceVertex(sourceVertex);
		sourceVertex.getOutgoingTransitions().add(transition);

		return transition;
	}

	/**
	 * This method creates a new instance set based on the available
	 * behaviorModelsAbsolute.
	 * 
	 * @param behaviorModelsAbsolute
	 * @return instance set
	 */
	protected Instances getInstances(
			BehaviorModelAbsolute[] behaviorModelsAbsolute, String outputDirectory) throws Exception {

		// init the fastVector with attributesNames from the first
		// behaviorModel.
		FastVector fastVector = getFastVector(behaviorModelsAbsolute[0]);

		// create empty instance set with the number of behaviorModelsRelative.
		Instances instances = new Instances("BehaviorModelAbsoluteInstanceSet",
				fastVector, behaviorModelsAbsolute.length);

		// set the last attribute as class index
		instances.setClassIndex(instances.numAttributes() - 1);

		// Each behaviorModelsRelative will be transformed to an instance. To do
		// that, that transition matrix will be
		// transformed in a vector. Set number of attributes of instance: n x (n
		// +1) exit state
		// Matrix.
		for (BehaviorModelAbsolute behaviorModelAbsolute : behaviorModelsAbsolute) {
			// retieve instance from behaviorModelRelative
			Instance instance = getInstance(behaviorModelAbsolute, instances);
			// add instance to instanceset, at the end of the set
			instances.add(instance);
		}

		// save input data as arff file. This arff file can be opened with weka
		// application.
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		saver.setFile(new File(outputDirectory
				+ "/data_clustering.arff"));
		saver.writeBatch();

		// Remove UseLess
		// weka.filters.unsupervised.attribute.RemoveUseless filterUseLess = new
		// weka.filters.unsupervised.attribute.RemoveUseless();
		// filterUseLess.setInputFormat(instances);
		// Instances returnInstances = Filter.useFilter(instances,
		// filterUseLess);

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
	protected Instance getInstance(BehaviorModelAbsolute behaviorModelAbsolute,
			Instances instances) {

		// create new instance with size n x (n + 1)
		int nrVertices = behaviorModelAbsolute.getVertices().size() - 1;
		Instance instance = new Instance((nrVertices * (nrVertices + 1)) + 1);
		final List<Vertex> vertices = behaviorModelAbsolute.getVertices();
		int indexOfAttribute = 0;
		for (final Vertex srcVertex : vertices) {
			if (srcVertex.getUseCase() != null) { // no final state
				// for each transition set the value of the instance vector
				for (final Vertex dstVertex : vertices) {
					final UseCase srcUseCase = srcVertex.getUseCase();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String srcUseCaseId = srcUseCase.getId();

					// if dstUseCase is null, its vertex denotes the final state
					// (no ID);
					final String dstUseCaseId = (dstUseCase != null) ? dstUseCase
							.getId() : null;
					Transition transition = this.findTransitionByUseCaseIDs(
							behaviorModelAbsolute, srcUseCaseId, dstUseCaseId);

					if (transition != null) {
						instance.setValue(indexOfAttribute,
								transition.getValue());
					} else {
						instance.setValue(indexOfAttribute, 0);
					}
					indexOfAttribute++;
				}
			} else {
				continue; // skip final state ("$");
			}
		}

		instance.setDataset(instances);

		// set attribute for classification
		instance.setValue(indexOfAttribute,
				behaviorModelAbsolute.getTransactionType());
		return instance;
	}

	/**
	 * Returns a FactVector which defines the attributes of the Instance set.
	 * 
	 * @param behaviorModelRelative
	 * @return FastVector defines the attributes of the Instance set
	 */
	protected FastVector getFastVector(
			BehaviorModelAbsolute behaviorModelAbsolute) {
		FastVector fastVector = new FastVector();
		final List<Vertex> vertices = behaviorModelAbsolute.getVertices();
		for (final Vertex srcVertex : vertices) {
			if (srcVertex.getUseCase() != null) { // no final state

				for (final Vertex dstVertex : vertices) {
					final UseCase srcUseCase = srcVertex.getUseCase();
					final String srcUseCaseName = srcUseCase.getName();
					final UseCase dstUseCase = dstVertex.getUseCase();
					final String dstUseCaseName = (dstUseCase != null) ? dstUseCase
							.getName() : "exit";

					// set AttributeName
					fastVector.addElement(new Attribute(srcUseCaseName + "_"
							+ dstUseCaseName, fastVector.size()));
				}
			} else {
				continue; // skip final state ("$");
			}
		}

		// Add dummyClassification. A valid instance set always seams to need a
		// classification attribute. Otherwise the clustering is not working.
		FastVector classification = new FastVector(3);
		classification.addElement("doBrowseVehicles-1");
		classification.addElement("doManageInventory-1");
		classification.addElement("doPurchaseVehicles-1");
		classification.addElement("noSessionType");
		Attribute classAttribute = new Attribute("theClass", classification);
		fastVector.addElement(classAttribute);

		return fastVector;
	}

	/**
	 * Create new Transitions with the values calculated by the clustering.
	 * 
	 * @param behaviorModel
	 * @param centroid
	 * @param centroidInstances
	 * @param centroidIndex
	 */
	protected void installTransitions(
			final BehaviorModelAbsolute[] behaviorModelsAbsolute,
			final BehaviorModelAbsolute behaviorModel, final Instance centroid,
			final int[] assignments, final int centroidIndex) {

		// List<Transition> transitionList = new ArrayList<Transition>();
		// int transitionCountAll = 0;

		final List<Vertex> vertices = behaviorModel.getVertices();
		for (final Vertex srcVertex : vertices) {

			if (srcVertex.getUseCase() != null) { // no final state
				for (final Vertex dstVertex : vertices) {

					Transition newTransition = installTransition(srcVertex,
							dstVertex);
					// transitionList.add(newTransition);

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
					double transitionCount = 0;
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
											behaviorModelsAbsolute[i],
											srcUseCaseId, dstUseCaseId);

							if (transition != null) {
								// store tinkTimeDiffs
								timeDiffs.addAll(transition.getTimeDiffs());
								transitionCount += transition.getValue();
								// transitionCountAll += transition.getValue();
							}
						}
					}

					if (timeDiffs.size() > 0) {
						newTransition.getTimeDiffs().addAll(timeDiffs);
					}

					if (transitionCount > 0) {
						newTransition.setValue(transitionCount);
					} else {
						newTransition.setValue(0);
					}
				}
			} else {
				continue; // skip final state ("$");
			}
		}

	}

}
