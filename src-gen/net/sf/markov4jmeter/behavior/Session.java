/**
 */
package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.Session#getId <em>Id</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Session#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Session#getEndTime <em>End Time</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Session#getObservedUseCaseExecutions <em>Observed Use Case Executions</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Session#getTransactionType <em>Transaction Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession()
 * @model
 * @generated
 */
public interface Session extends EObject {
    /**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession_Id()
	 * @model required="true"
	 * @generated
	 */
    String getId();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Session#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

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
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession_StartTime()
	 * @model required="true"
	 * @generated
	 */
    long getStartTime();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Session#getStartTime <em>Start Time</em>}' attribute.
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
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession_EndTime()
	 * @model required="true"
	 * @generated
	 */
    long getEndTime();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Session#getEndTime <em>End Time</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Time</em>' attribute.
	 * @see #getEndTime()
	 * @generated
	 */
    void setEndTime(long value);

    /**
	 * Returns the value of the '<em><b>Observed Use Case Executions</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.markov4jmeter.behavior.ObservedUseCaseExecution}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Observed Use Case Executions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Use Case Executions</em>' containment reference list.
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession_ObservedUseCaseExecutions()
	 * @model containment="true"
	 * @generated
	 */
    EList<ObservedUseCaseExecution> getObservedUseCaseExecutions();

				/**
	 * Returns the value of the '<em><b>Transaction Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction Type</em>' attribute.
	 * @see #setTransactionType(String)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSession_TransactionType()
	 * @model
	 * @generated
	 */
	String getTransactionType();

				/**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Session#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Type</em>' attribute.
	 * @see #getTransactionType()
	 * @generated
	 */
	void setTransactionType(String value);

} // Session
