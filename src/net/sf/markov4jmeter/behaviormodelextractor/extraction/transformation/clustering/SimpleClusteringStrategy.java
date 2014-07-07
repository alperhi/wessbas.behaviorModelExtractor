package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;

/**
 * This class represents a <i>"simple"</i> strategy, that is average values of
 * probabilities and think times will be calculated and summarized for each
 * transition, resulting in a single Behavior Model.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public class SimpleClusteringStrategy extends AbstractClusteringStrategy {


    /* **************************  public methods  ************************** */


    /**
     * {@inheritDoc}
     *
     * <p> This method is specialized for <b>"simple"</b> clustering, that is
     * average values of probabilities and think times will be calculated and
     * summarized for each transition, resulting in a single Behavior Model.
     */
    @Override
    public BehaviorMix apply (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // Behavior Mix to be returned;
        final BehaviorMix behaviorMix = this.createBehaviorMix();

        // create an "empty" Behavior Model, which includes all vertices only;
        final BehaviorModelRelative behaviorModel =
                this.createBehaviorModelWithoutTransitions(
                        useCaseRepository.getUseCases());

        // install the transitions in between vertices;
        this.installTransitions(behaviorModel, behaviorModelsRelative);

        // create the (unique) Behavior Mix entry to be returned;
        final BehaviorMixEntry behaviorMixEntry = this.createBehaviorMixEntry(
                AbstractClusteringStrategy.GENERIC_BEHAVIOR_MODEL_NAME,
                1.0d,  // relative frequency;
                behaviorModel);

        behaviorMix.getEntries().add(behaviorMixEntry);

        return behaviorMix;
    }


    /* **************************  private methods  ************************* */


    /**
     * Installs the transitions of a given Behavior Model, according to the
     * "simple" clustering strategy.
     *
     * <p> For all transitions of a set of input Behavior Models, the
     * probabilities, mean and deviation values will be divided by the number
     * of input models. Each transition between two vertices of the resulting
     * Behavior Model will be initialized with the sums of its average values.
     *
     * @param behaviorModel
     *     Behavior Model whose transitions shall be installed.
     * @param behaviorModelsRelative
     *     set of input Behavior Models on which the clustering strategy shall
     *     be applied.
     *
     * @return
     *    (unique) Behavior Model that results from the simple clustering
     *    strategy.
     */
    private BehaviorModelRelative installTransitions (
            final BehaviorModelRelative behaviorModel,
            final BehaviorModelRelative[] behaviorModelsRelative) {

        final List<Vertex> vertices = behaviorModel.getVertices();

        for (final Vertex srcVertex : vertices) {

            if (srcVertex.getUseCase() != null) {  // no final state?

                for (final Vertex dstVertex : vertices) {

                    this.installTransition(
                            srcVertex,
                            dstVertex,
                            behaviorModelsRelative);
                }

            } else {

                continue;  // skip final state ("$");
            }
        }

        return behaviorModel;
    }

    /**
     * Installs a transition between two given vertices.
     *
     * @param srcVertex
     *     source vertex of the transition; the transition will be added to
     *     the outgoing transitions of that vertex.
     * @param dstVertex
     *     destination vertex, which might be even <code>null</code> (final
     *     state, "$").
     * @param behaviorModelsRelative
     *     set of input Behavior Models on which the clustering strategy shall
     *     be applied.
     */
    private void installTransition (
            final Vertex srcVertex,
            final Vertex dstVertex,
            final BehaviorModelRelative[] behaviorModelsRelative) {

        final UseCase srcUseCase = srcVertex.getUseCase();
        final UseCase dstUseCase = dstVertex.getUseCase();

        final String srcUseCaseId = srcUseCase.getId();

        final BigDecimal n = new BigDecimal(behaviorModelsRelative.length);

        // calculate the sums for probability, mean and deviation
        // of the current transition;
        BigDecimal probabilitySum = new BigDecimal(0.0d);
        BigDecimal meanSum        = new BigDecimal(0.0d);
        BigDecimal deviationSum   = new BigDecimal(0.0d);

        // temporary variables, just to be used in for-loops;
        Transition transition, newTransition;
        BigDecimal value;

        // if dstUseCase is null, its vertex denotes the final state (no ID);
        final String dstUseCaseId =
                (dstUseCase != null) ? dstUseCase.getId() : null;

        for (BehaviorModelRelative behaviorModelRelative :
             behaviorModelsRelative) {

            transition = this.findTransitionByUseCaseIDs(
                    behaviorModelRelative,
                    srcUseCaseId,
                    dstUseCaseId);  // dstUseCaseId might be null (final state);

            if (transition != null) {

                value = new BigDecimal(transition.getValue());

                probabilitySum = probabilitySum.add(
                        value.divide(n, RoundingMode.HALF_UP));

                // first component of times list is mean;
                value = transition.getTimes().get(0);

                meanSum = meanSum.add(
                        value.divide(n, RoundingMode.HALF_UP));

                // second component of times list is deviation;
                value = transition.getTimes().get(1);

                deviationSum = meanSum.add(
                        value.divide(n, RoundingMode.HALF_UP));
            }
        }

        // invoke method of parent class;
        newTransition = this.installTransition(srcVertex, dstVertex);

        newTransition.setValue(probabilitySum.doubleValue());
        newTransition.getTimes().add(meanSum);
        newTransition.getTimes().add(deviationSum);
    }
}
