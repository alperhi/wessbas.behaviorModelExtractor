/***************************************************************************
 * Copyright 2012 by
 *  Christian-Albrechts-University of Kiel, 24098 Kiel, Germany
 *    + Department of Computer Science
 *     + Software Engineering Group
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
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.util.MathUtil;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * This class provides methods for transforming "absolute" Behavior Models to
 * "relative" Behavior Models.
 * 
 * @see SessionToABMTransformer
 * @see RBMToMarkovMatrixTransformer
 * 
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 * 
 * @version 1.0
 */
public class ABMToRBMTransformer {

	/* ************************** public methods ************************** */

	/**
	 * Transforms a set of "absolute" Behavior Models to "relative" Behavior
	 * Models.
	 * 
	 * @param behaviorModelsAbsolute
	 *            the absolute Behavior Models to be transformed to relative
	 *            Behavior Models.
	 * 
	 * @return the resulting relative Behavior Models.
	 */
	public BehaviorModelRelative[] transform(
			final BehaviorModelAbsolute[] behaviorModelsAbsolute) {

		final int n = behaviorModelsAbsolute.length;

		// to models to be returned;
		final BehaviorModelRelative[] behaviorModelsRelative = new BehaviorModelRelative[n];

		for (int i = 0; i < n; i++) {

			final BehaviorModelAbsolute behaviorModelAbsolute = behaviorModelsAbsolute[i];

			final BehaviorModelRelative behaviorModelRelative = this
					.transform(behaviorModelAbsolute);

			behaviorModelsRelative[i] = behaviorModelRelative;
		}
		return behaviorModelsRelative;
	}

	/* ************************** private methods ************************* */

	/**
	 * Transforms an "absolute" Behavior Model to a "relative" Behavior Model.
	 * 
	 * @param behaviorModelAbsolute
	 *            the absolute Behavior Model to be transformed to a relative
	 *            Behavior Model.
	 * 
	 * @return the resulting relative Behavior Model.
	 */
	public BehaviorModelRelative transform(
			final BehaviorModelAbsolute behaviorModelAbsolute) {

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		// to model to be returned;
		final BehaviorModelRelative behaviorModelRelative = factory
				.createBehaviorModelRelative();

		final List<Vertex> aVertices = behaviorModelAbsolute.getVertices();
		final List<Vertex> rVertices = behaviorModelRelative.getVertices();

		for (final Vertex aVertex : aVertices) {

			// clone the original model recursively (including transitions);
			final Vertex rVertex = EcoreUtil.copy(aVertex);

			this.convertOutgoingTransitionValues(rVertex);
			rVertices.add(rVertex);
		}

		return behaviorModelRelative;
	}

	/**
	 * Converts the label values of all outgoing transitions of a given vertex
	 * from absolute to relative values.
	 * 
	 * @param vertex
	 *            the vertex whose outgoing transitions' labels will be
	 *            converted.
	 */
	private void convertOutgoingTransitionValues(final Vertex vertex) {

		final List<Transition> outgoingTransitions = vertex
				.getOutgoingTransitions();

		// count number of transition occurrences (note that each transition
		// might fire several times to a certain target vertex);
		double n = 0;
		double sumProbability = 0;

		for (final Transition outgoingTransition : outgoingTransitions) {
			final double value = outgoingTransition.getValue();
			n += value;
		}

		for (final Transition outgoingTransition : outgoingTransitions) {

			// conversion: absolute values -> relative values;

			final double value = outgoingTransition.getValue();

			if (n > 0) {
				// final double relValue = MathUtil.round(value / n); // n > 0
				// here;
				final double relValue = MathUtil.round(value / n); // n > 0
																	// here;
				outgoingTransition.setValue(relValue);
			}

			// conversion: times -> think times;

			final BigDecimal mean;
			final BigDecimal deviation;

			final List<BigDecimal> timeDiffs = outgoingTransition
					.getTimeDiffs();

			if (timeDiffs.size() > 0) {

				mean = MathUtil.computeMean(timeDiffs);
				deviation = MathUtil.computeDeviation(timeDiffs);

			} else { // times.size() == 0;

				mean = BigDecimal.ZERO;
				deviation = BigDecimal.ZERO;
			}

			// store mean and deviation values as think time parameters;

			final List<BigDecimal> thinkTimeParams = outgoingTransition
					.getThinkTimeParams();

			thinkTimeParams.add(mean);
			thinkTimeParams.add(deviation);

		}

		// ensure that the sum of the probabilities is one
		for (final Transition outgoingTransition : outgoingTransitions) {
			sumProbability += outgoingTransition.getValue();
		}

		if (sumProbability != 1.0 && n > 0) {

			boolean diffIsDistributed = false;
			double diff = 1.0 - sumProbability;

			for (final Transition outgoingTransition : outgoingTransitions) {
				if (outgoingTransition.getValue() > 0) {
					outgoingTransition.setValue(outgoingTransition.getValue()
							+ diff);
					diffIsDistributed = true;
					break;
				}
			}

			if (!diffIsDistributed) {
				throw new RuntimeException(
						"Probabilities do not sum up to one!");
			}

		}

	}
}