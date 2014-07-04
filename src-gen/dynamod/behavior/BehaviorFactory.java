/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see dynamod.behavior.BehaviorPackage
 * @generated
 */
public interface BehaviorFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    BehaviorFactory eINSTANCE = dynamod.behavior.impl.BehaviorFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Use Case</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Use Case</em>'.
     * @generated
     */
    UseCase createUseCase();

    /**
     * Returns a new object of class '<em>Use Case Repository</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Use Case Repository</em>'.
     * @generated
     */
    UseCaseRepository createUseCaseRepository();

    /**
     * Returns a new object of class '<em>Session Repository</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session Repository</em>'.
     * @generated
     */
    SessionRepository createSessionRepository();

    /**
     * Returns a new object of class '<em>Session</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Session</em>'.
     * @generated
     */
    Session createSession();

    /**
     * Returns a new object of class '<em>Observed Use Case Execution</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Observed Use Case Execution</em>'.
     * @generated
     */
    ObservedUseCaseExecution createObservedUseCaseExecution();

    /**
     * Returns a new object of class '<em>Vertex</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Vertex</em>'.
     * @generated
     */
    Vertex createVertex();

    /**
     * Returns a new object of class '<em>Transition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Transition</em>'.
     * @generated
     */
    Transition createTransition();

    /**
     * Returns a new object of class '<em>Model Absolute</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Model Absolute</em>'.
     * @generated
     */
    BehaviorModelAbsolute createBehaviorModelAbsolute();

    /**
     * Returns a new object of class '<em>Model Relative</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Model Relative</em>'.
     * @generated
     */
    BehaviorModelRelative createBehaviorModelRelative();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    BehaviorPackage getBehaviorPackage();

} //BehaviorFactory
