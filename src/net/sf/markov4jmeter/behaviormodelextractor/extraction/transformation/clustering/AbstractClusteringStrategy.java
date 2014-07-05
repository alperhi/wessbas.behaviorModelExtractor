package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.clustering;

import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.Vertex;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;

public abstract class AbstractClusteringStrategy {

    public abstract BehaviorMixEntry[] transform (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository)
                    throws ExtractionException;


    protected Transition findTransitionByUseCaseIDs (
            final BehaviorModelRelative behaviorModelRelative,
            final String srcUseCaseId,
            final String dstUseCaseId) {

        final Vertex srcVertex = this.findVertexByUseCaseId(
                behaviorModelRelative, srcUseCaseId);

        if (srcVertex != null) {

            final List<Transition> transitions =
                    srcVertex.getOutgoingTransitions();

            for (final Transition transition : transitions) {

                final UseCase useCase =
                        transition.getTargetVertex().getUseCase();

                if (useCase != null && useCase.getId().equals(dstUseCaseId) ) {

                    return transition;
                }
            }
        }

        return null;  // no matching transition found;
    }

    /**
     * Searches for a transition between two given vertices.
     *
     * @param srcVertex
     *     source vertex of the searched transition.
     * @param dstVertex
     *     destination vertex of the searched transition.
     *
     * @return
     *     a transition between the given vertices, if one exists;
     *     otherwise <code>null</code> will be returned.
     */
/*
    protected Transition findTransitionByUseCaseNames (
            final BehaviorModelRelative behaviorModelRelative,
            final String srcUseCaseName,
            final String dstUseCaseName) {

        final Vertex srcVertex = this.findVertexByUseCaseName(
                behaviorModelRelative, srcUseCaseName);

        if (srcVertex != null) {

            final List<Transition> transitions =
                    srcVertex.getOutgoingTransitions();

            for (final Transition transition : transitions) {

                if (dstUseCaseName ==
                        transition.getTargetVertex().getUseCase().getName()) {

                    return transition;
                }
            }
        }

        return null;  // no matching transition found;
    }
*/
    /**
     * Searches for a vertex which is associated with a use case of certain ID.
     *
     * @param useCaseId
     *     ID of the use case.
     * @param vertices
     *     list of vertices to be searched through.
     *
     * @return
     *     a matching vertex, or <code>null</code> if such a vertex does not
     *     exist.
     */
    protected Vertex findVertexByUseCaseId (
            final BehaviorModelRelative behaviorModelRelative,
            final String useCaseId) {

        final List<Vertex> vertices = behaviorModelRelative.getVertices();

        for (final Vertex vertex : vertices) {

            final UseCase vertexUseCase = vertex.getUseCase();

            if (vertexUseCase != null) {  // final vertex has no use case;

                final String vertexUseCaseId  = vertexUseCase.getId();

                if ( useCaseId.equals(vertexUseCaseId) ) {

                    return vertex;
                }

            } else {

                if (useCaseId == null) {

                    return vertex;
                }
            }
        }

        return null;  // no matching vertex found;
    }

    /**
     * Searches for a vertex which is associated with a use case of certain ID.
     *
     * @param useCaseId
     *     ID of the use case.
     * @param vertices
     *     list of vertices to be searched through.
     *
     * @return
     *     a matching vertex, or <code>null</code> if such a vertex does not
     *     exist.
     */
    /*
    protected Vertex findVertexByUseCaseName (
            final BehaviorModelRelative behaviorModelRelative,
            final String useCaseName) {

        final List<Vertex> vertices = behaviorModelRelative.getVertices();

        for (final Vertex vertex : vertices) {

            final UseCase vertexUseCase = vertex.getUseCase();

            if (vertexUseCase != null) {  // final vertex has no use case;

                final String vertexUseCaseName  = vertexUseCase.getName();

                if (useCaseName.equals(vertexUseCaseName)) {

                    return vertex;
                }
            }
        }

        return null;  // no matching vertex found;
    }
     */
    protected BehaviorModelRelative createBehaviorModelWithoutTransitions (
            final List<UseCase> useCases) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        final BehaviorModelRelative behaviorModel =
                factory.createBehaviorModelRelative();

        final List<Vertex> vertices = behaviorModel.getVertices();

        Vertex vertex;

        for (final UseCase useCase : useCases) {

            vertex = factory.createVertex();

            vertex.setUseCase(useCase);
            vertices.add(vertex);
        }

        // add the final vertex;

        vertex = factory.createVertex();

        vertex.setUseCase(null);  // no use case indicates the exit state;
        vertices.add(vertex);

        return behaviorModel;
    }

    protected BehaviorMixEntry createBehaviorMixEntry (
            final String behaviorModelName,
            final double frequency,
            final BehaviorModelRelative behaviorModel) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        final BehaviorMixEntry behaviorMixEntry =
                factory.createBehaviorMixEntry();  // to be returned;

        behaviorMixEntry.setBehaviorModelName(behaviorModelName);
        behaviorMixEntry.setRelativeFrequency(frequency);
        behaviorMixEntry.setBehaviorModel(behaviorModel);

        return behaviorMixEntry;
    }

    /**
     * Installs a new transition between two vertices; the (newly created)
     * transition will be added to the outgoing transitions of the source
     * vertex.
     *
     * @param sourceVertex  source vertex of the transition.
     * @param targetVertex  target vertex of the transition.
     *
     * @return  the installed transition.
     */
    protected Transition installTransition (
            final Vertex sourceVertex,
            final Vertex targetVertex) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;
        final Transition transition = factory.createTransition();

        transition.setTargetVertex(targetVertex);
        transition.setValue(0);  // TODO: set default values;

        sourceVertex.getOutgoingTransitions().add(transition);

        return transition;
    }}
