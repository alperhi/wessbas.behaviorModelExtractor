/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Behavior Model Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dynamod.behavior.AbstractBehaviorModelGraph#getVertices <em>Vertices</em>}</li>
 * </ul>
 * </p>
 *
 * @see dynamod.behavior.BehaviorPackage#getAbstractBehaviorModelGraph()
 * @model abstract="true"
 * @generated
 */
public interface AbstractBehaviorModelGraph extends EObject {
    /**
     * Returns the value of the '<em><b>Vertices</b></em>' containment reference list.
     * The list contents are of type {@link dynamod.behavior.Vertex}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Vertices</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Vertices</em>' containment reference list.
     * @see dynamod.behavior.BehaviorPackage#getAbstractBehaviorModelGraph_Vertices()
     * @model containment="true"
     * @generated
     */
    EList<Vertex> getVertices();

} // AbstractBehaviorModelGraph
