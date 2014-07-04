/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Use Case Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dynamod.behavior.AbstractUseCaseExecution#getUseCase <em>Use Case</em>}</li>
 * </ul>
 * </p>
 *
 * @see dynamod.behavior.BehaviorPackage#getAbstractUseCaseExecution()
 * @model abstract="true"
 * @generated
 */
public interface AbstractUseCaseExecution extends EObject {
    /**
     * Returns the value of the '<em><b>Use Case</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Case</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Use Case</em>' reference.
     * @see #setUseCase(UseCase)
     * @see dynamod.behavior.BehaviorPackage#getAbstractUseCaseExecution_UseCase()
     * @model required="true"
     * @generated
     */
    UseCase getUseCase();

    /**
     * Sets the value of the '{@link dynamod.behavior.AbstractUseCaseExecution#getUseCase <em>Use Case</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Case</em>' reference.
     * @see #getUseCase()
     * @generated
     */
    void setUseCase(UseCase value);

} // AbstractUseCaseExecution
