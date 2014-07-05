package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;

/**
 * Applies a "simple" clustering method on the extracted Behavior Models;
 * average values of probabilities and think times will be calculated and
 * summarized per transition, resulting in a single Behavior Model.
 *
 * @param behaviorModelsRelative
 *     set of Behavior Models on which the clustering method shall be
 *     applied.
 *
 * @return
 *     a set of Behavior Mix entries, including a single Behavior Model with
 *     a (generated) name and 1.0 as relative frequency.
 */
public class SimpleClusteringStrategy extends AbstractClusteringStrategy {

    @Override
    public BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository) {

        // create an "empty" Behavior Model, which includes all vertices only;
        final BehaviorModelRelative behaviorModel =
                this.createBehaviorModelWithoutTransitions(
                        useCaseRepository.getUseCases());

        // install the transitions in between vertices;
        this.installTransitions(behaviorModel, behaviorModelsRelative);

        // create the unique Behavior Mix entry to be returned;
        final BehaviorMixEntry behaviorMixEntry = this.createBehaviorMixEntry(
                "gen_behavior_model",  // Behavior Model name;
                1.0d,                  // relative frequency;
                behaviorModel);        // Behavior Model (vertices/transitions);

        return new BehaviorMixEntry[]{ behaviorMixEntry };
    }

    private BehaviorModelRelative installTransitions (
            final BehaviorModelRelative behaviorModel,
            final BehaviorModelRelative[] behaviorModelsRelative) {

        final BigDecimal n = new BigDecimal(behaviorModelsRelative.length);
        final List<Vertex> vertices = behaviorModel.getVertices();

        // temporary variables, just used in for-loops;
        Transition transition;
        UseCase srcUseCase, dstUseCase;
        String srcUseCaseId, dstUseCaseId;

        BigDecimal probabilitySum;
        BigDecimal meanSum;
        BigDecimal deviationSum;
        BigDecimal value;

        for (final Vertex srcVertex : vertices) {

            srcUseCase = srcVertex.getUseCase();

            if (srcUseCase != null) {

                srcUseCaseId = srcUseCase.getId();

                for (final Vertex dstVertex : vertices) {

                    probabilitySum = new BigDecimal(0.0d);
                    meanSum        = new BigDecimal(0.0d);
                    deviationSum   = new BigDecimal(0.0d);

                    dstUseCase = dstVertex.getUseCase();

                    if (dstUseCase != null) {

                        dstUseCaseId = dstUseCase.getId();

                        for (BehaviorModelRelative behaviorModelRelative :
                             behaviorModelsRelative) {

                            transition = this.findTransitionByUseCaseIDs(
                                    behaviorModelRelative, srcUseCaseId, dstUseCaseId);

                            if (transition != null) {

                                value = new BigDecimal(transition.getValue());
                                probabilitySum = probabilitySum.add(value.divide(n, RoundingMode.HALF_UP));

                                value = transition.getTimes().get(0);
                                meanSum = meanSum.add(value.divide(n, RoundingMode.HALF_UP));

                                value = transition.getTimes().get(1);
                                deviationSum = meanSum.add(value.divide(n, RoundingMode.HALF_UP));
                            }
                        }

                        Transition newTransition = this.installTransition(srcVertex, dstVertex);
                        newTransition.setValue(probabilitySum.doubleValue());
                        newTransition.getTimes().add(meanSum);
                        newTransition.getTimes().add(deviationSum);

                    } else {

                        for (BehaviorModelRelative behaviorModelRelative :
                            behaviorModelsRelative) {

                            transition = this.findTransitionByUseCaseIDs(
                                    behaviorModelRelative, srcUseCaseId, null);

                            if (transition != null) {

                                value = new BigDecimal(transition.getValue());
                                probabilitySum = probabilitySum.add(value.divide(n, RoundingMode.HALF_UP));
                            }
                        }

                        Transition newTransition = this.installTransition(srcVertex, dstVertex);
                        newTransition.setValue(probabilitySum.doubleValue());
                    }
                }

            } else {

                continue;  // skip vertex without use case (final vertex, "$");
            }
        }
        return behaviorModel;
    }
}
