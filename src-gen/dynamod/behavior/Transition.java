/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior;

import java.math.BigDecimal;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dynamod.behavior.Transition#getTargetVertex <em>Target Vertex</em>}</li>
 *   <li>{@link dynamod.behavior.Transition#getValue <em>Value</em>}</li>
 *   <li>{@link dynamod.behavior.Transition#getTimes <em>Times</em>}</li>
 * </ul>
 * </p>
 *
 * @see dynamod.behavior.BehaviorPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
    /**
     * Returns the value of the '<em><b>Target Vertex</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Vertex</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Vertex</em>' reference.
     * @see #setTargetVertex(Vertex)
     * @see dynamod.behavior.BehaviorPackage#getTransition_TargetVertex()
     * @model required="true"
     * @generated
     */
    Vertex getTargetVertex();

    /**
     * Sets the value of the '{@link dynamod.behavior.Transition#getTargetVertex <em>Target Vertex</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Vertex</em>' reference.
     * @see #getTargetVertex()
     * @generated
     */
    void setTargetVertex(Vertex value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(double)
     * @see dynamod.behavior.BehaviorPackage#getTransition_Value()
     * @model
     * @generated
     */
    double getValue();

    /**
     * Sets the value of the '{@link dynamod.behavior.Transition#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(double value);

    /**
     * Returns the value of the '<em><b>Times</b></em>' attribute list.
     * The list contents are of type {@link java.math.BigDecimal}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Times</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Times</em>' attribute list.
     * @see dynamod.behavior.BehaviorPackage#getTransition_Times()
     * @model
     * @generated
     */
    EList<BigDecimal> getTimes();

} // Transition
