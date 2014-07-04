/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Observed Use Case Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dynamod.behavior.ObservedUseCaseExecution#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link dynamod.behavior.ObservedUseCaseExecution#getEndTime <em>End Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see dynamod.behavior.BehaviorPackage#getObservedUseCaseExecution()
 * @model
 * @generated
 */
public interface ObservedUseCaseExecution extends AbstractUseCaseExecution {
    /**
     * Returns the value of the '<em><b>Start Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Time</em>' attribute.
     * @see #setStartTime(long)
     * @see dynamod.behavior.BehaviorPackage#getObservedUseCaseExecution_StartTime()
     * @model
     * @generated
     */
    long getStartTime();

    /**
     * Sets the value of the '{@link dynamod.behavior.ObservedUseCaseExecution#getStartTime <em>Start Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Time</em>' attribute.
     * @see #getStartTime()
     * @generated
     */
    void setStartTime(long value);

    /**
     * Returns the value of the '<em><b>End Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Time</em>' attribute.
     * @see #setEndTime(long)
     * @see dynamod.behavior.BehaviorPackage#getObservedUseCaseExecution_EndTime()
     * @model
     * @generated
     */
    long getEndTime();

    /**
     * Sets the value of the '{@link dynamod.behavior.ObservedUseCaseExecution#getEndTime <em>End Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Time</em>' attribute.
     * @see #getEndTime()
     * @generated
     */
    void setEndTime(long value);

} // ObservedUseCaseExecution
