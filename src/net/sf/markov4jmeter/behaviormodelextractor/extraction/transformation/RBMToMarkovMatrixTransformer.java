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

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.MarkovMatrixHandler;
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;

/**
 * This class provides methods for transforming "relative" Behavior Models to
 * Markov Matrices.
 * 
 * <p>
 * Relative Behavior Models are equipped with probabilities and think times.
 * They correspond to Behavior Models as defined for Markov4JMeter; the prefix
 * "Relative" just emphasizes their difference to "absolute" Behavior Models,
 * from which they originate.
 * 
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 * 
 * @version 1.0
 */
public class RBMToMarkovMatrixTransformer {

	/* ************************* global variables ************************* */

	/** Instance for creating and modifying Markov matrices. */
	private final MarkovMatrixHandler markovMatrixHandler;

	/* *************************** constructors *************************** */

	/**
	 * Constructor for an RBM-to-(Markov-)matrix Transformer.
	 * 
	 * @param markovMatrixHandler
	 *            instance for creating and modifying Markov matrices.
	 */
	public RBMToMarkovMatrixTransformer(
			final MarkovMatrixHandler markovMatrixHandler) {

		this.markovMatrixHandler = markovMatrixHandler;
	}

	/* ************************** public methods ************************** */

	/**
	 * Transforms a given "relative" Behavior Model to a Markov matrix.
	 * 
	 * @param behaviorModelRelative
	 *            the Behavior Model to be transformed.
	 * 
	 * @return the resulting Markov matrix.
	 */
	public String[][] transform(
			final BehaviorModelRelative behaviorModelRelative) {

		final List<Vertex> vertices = behaviorModelRelative.getVertices();
		final LinkedList<String> states = new LinkedList<String>();

		for (final Vertex vertex : vertices) {

			states.add(this.getVertexName(vertex));
		}

		final String initialStateName = states.getFirst();

		final String[][] matrix = this.markovMatrixHandler
				.createEmptyMarkovMatrixForStates(states);

		// ignore returned matrix, since it is the same as passed;
		this.storeModelInMatrix(behaviorModelRelative, matrix);

		this.markovMatrixHandler.setInitialState(initialStateName, matrix);

		return matrix;
	}

	/**
	 * Returns the associated instance for creating and modifying Markov
	 * matrices.
	 * 
	 * @return the associated instance of {@link MarkovMatrixHandler}.
	 */
	public MarkovMatrixHandler getMarkovMatrixHandler() {

		return markovMatrixHandler;
	}

	/* ************************** private methods ************************* */

	/**
	 * Fills a given Matrix matrix with the values, which are indicated by the
	 * vertices of a given Behavior Model graph.
	 * 
	 * @param abstractBehaviorModelGraph
	 *            Behavior Model graph to be stored in a given Markov matrix.
	 * @param matrix
	 *            the Markov matrix to be filled with values.
	 * 
	 * @return the filled Markov matrix.
	 */
	private String[][] storeModelInMatrix(
			final AbstractBehaviorModelGraph abstractBehaviorModelGraph,
			final String[][] matrix) {

		final List<Vertex> vertices = abstractBehaviorModelGraph.getVertices();

		final String finalStateName = this.markovMatrixHandler
				.getFinalStateName();

		this.markovMatrixHandler.clearMatrix(matrix);

		for (final Vertex srcVertex : vertices) {

			double valueSum = 0;

			final String srcVertexName = this.getVertexName(srcVertex);

			if (!finalStateName.equals(srcVertexName)) {

				for (final Transition transition : srcVertex
						.getOutgoingTransitions()) {

					// round the probability to 4 digits behind the comma, to
					// avoid rounding errors when the sum of the outgoing
					// transition probabilities of a state is validated;
					double probability = transition.getValue();

					final Vertex dstVertex = transition.getTargetVertex();
					final String dstVertexName = this.getVertexName(dstVertex);

					valueSum += probability;

					final List<BigDecimal> thinkTimeParams = transition
							.getThinkTimeParams();

					final double mean;
					final double deviation;

					if (thinkTimeParams.size() == 2) {

						// use milli- instead of nanoseconds for Thread.sleep();
						mean = this.convertNsToMs(thinkTimeParams.get(0)
								.longValue());

						deviation = this.convertNsToMs(thinkTimeParams.get(1)
								.longValue());

					} else {

						mean = 0.0d;
						deviation = 0.0d;
					}

					double diff = 0.0;
					if (finalStateName.equals(dstVertexName)) {
						if (valueSum != 1.0 && valueSum > 0) {
							diff = 1.0 - valueSum;
							probability += diff;
						}
					}

					this.markovMatrixHandler.setValueAtCell(
							MathUtil.round(probability) + "; n(" + (long) mean
									+ " " + (long) deviation + ")",
							srcVertexName, dstVertexName, matrix);
				}

			}
		}

		return matrix;
	}

	/**
	 * Converts a given time range from nanoseconds to milliseconds.
	 * 
	 * @param durationNs
	 *            time range in nanoseconds to be converted.
	 * 
	 * @return the time range in milliseconds.
	 */
	private long convertNsToMs(long durationNs) {

		return TimeUnit.MILLISECONDS.convert(durationNs, TimeUnit.NANOSECONDS);
	}

	/**
	 * Returns the name of the use case which is associated with a given vertex.
	 * 
	 * @param vertex
	 *            the vertex whose associated use case's name shall be returned.
	 * 
	 * @return the name of the use case which is associated with the given
	 *         vertex; if no use case is associated with the vertex, the name of
	 *         the final state will be returned.
	 */
	private String getVertexName(final Vertex vertex) {

		final UseCase useCase = vertex.getUseCase();

		if (useCase == null) { // vertex for final state has no use case;

			return this.markovMatrixHandler.getFinalStateName();
		}

		return useCase.getName();
	}
}
