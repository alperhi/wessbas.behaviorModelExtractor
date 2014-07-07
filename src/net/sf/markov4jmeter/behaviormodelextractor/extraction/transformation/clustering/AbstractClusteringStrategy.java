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

/**
 * Abstract base class for all clustering strategies.
 *
 * <p> This class defines the abstract
 * {@link #apply(BehaviorModelRelative[], UseCaseRepository)} method, which has
 * to be implemented by each subclass, according to the associated clustering
 * strategy. Furthermore, helping methods for searching and creating Behavior
 * Model elements are provided.
 *
 * @author   Eike Schulz (esc@informatik.uni-kiel.de)
 * @version  1.0
 */
public abstract class AbstractClusteringStrategy {


    /* *****************************  constants  **************************** */


    protected final static String GENERIC_BEHAVIOR_MODEL_NAME =
            "gen_behavior_model";


    /* **************************  public methods  ************************** */

    /**
     * Applies the associated clustering strategy to a given set of Behavior
     * Models.
     *
     * @param behaviorModelsRelative
     *     set of input Behavior Models on which the clustering strategy shall
     *     be applied.
     * @param useCaseRepository
     *     use case repository which provides all available use cases; the use
     *     cases generally indicate the states ("vertices") of each Behavior
     *     Model, with a dedicated final state in addition. Final states are not
     *     associated with any use case, indicated by their regarding attribute
     *     being set to <code>null</code>.
     *
     * @return
     *     a set of Behavior Mix entries; each Behavior Mix entry includes a
     *     (generated) name of its related Behavior Model, a computed relative
     *     frequency and a reference to the Behavior Model itself.
     *
     * @throws ExtractionException
     *     if any clustering error occurs.
     */
    public abstract BehaviorMixEntry[] apply (
            final BehaviorModelRelative[] behaviorModelsRelative,
            final UseCaseRepository useCaseRepository)
                    throws ExtractionException;


    /* *************************  protected methods  ************************ */


    /**
     * Searches for a Behavior Model transition between two use cases,
     * respectively their associated vertices.
     *
     * @param behaviorModelRelative
     *     Behavior Model to be searched through.
     * @param srcUseCaseId
     *     identifier of the use case which is associated with the source
     *     vertex.
     * @param dstUseCaseId
     *     identifier of the use case which is associated with the target
     *     vertex.
     *
     * @return
     *     a matching transition, or <code>null</code> if such a transition
     *     does not exist.
     */
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
     * Searches for a Behavior Model vertex which is associated with a specified
     * use case or with the final state.
     *
     * @param behaviorModelRelative
     *     Behavior Model whose vertices shall be searched through.
     * @param useCaseId
     *     an identifier of the use case which is associated with the searched
     *     vertex, or <code>null</code> for searching the vertex that represents
     *     the final state.
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

            if (vertexUseCase != null) {

                final String vertexUseCaseId  = vertexUseCase.getId();

                if ( useCaseId.equals(vertexUseCaseId) ) {

                    return vertex;
                }

            } else {  // vertexUseCase == null  -->  final state found;

                if (useCaseId == null) {  // searching for final state?

                    return vertex;
                }
            }
        }

        return null;  // no matching vertex found;
    }

    /**
     * Creates a Behavior Model which includes vertices indicated by a set of
     * given use cases.
     *
     * @param useCases
     *     set of use cases which indicate the vertices to be included to the
     *     Behavior Model; a vertex that represents the final state will be
     *     added explicitly.
     *
     * @return
     *     the newly created Behavior Model.
     */
    protected BehaviorModelRelative createBehaviorModelWithoutTransitions (
            final List<UseCase> useCases) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        final BehaviorModelRelative behaviorModel =
                factory.createBehaviorModelRelative();  // to be returned;

        final List<Vertex> vertices = behaviorModel.getVertices();

        Vertex vertex;

        for (final UseCase useCase : useCases) {

            vertex = factory.createVertex();

            vertex.setUseCase(useCase);
            vertices.add(vertex);
        }

        // add vertex for the final state at last;

        vertex = factory.createVertex();

        vertex.setUseCase(null);  // no use case associated with final state;
        vertices.add(vertex);

        return behaviorModel;
    }

    /**
     * Creates a Behavior Mix entry for a given Behavior Model, its (generated)
     * name and frequency.
     *
     * @param behaviorModelName
     *     the (generated) name of the Behavior Model.
     * @param frequency
     *     a relative frequency.
     * @param behaviorModel
     *     the Behavior Model itself.
     *
     * @return
     *     the newly created instance.
     */
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
    }
}