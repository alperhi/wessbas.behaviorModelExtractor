/**
 */
package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.SessionRepository#getSessions <em>Sessions</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSessionRepository()
 * @model
 * @generated
 */
public interface SessionRepository extends EObject {
    /**
	 * Returns the value of the '<em><b>Sessions</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.markov4jmeter.behavior.Session}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sessions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Sessions</em>' containment reference list.
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getSessionRepository_Sessions()
	 * @model containment="true"
	 * @generated
	 */
    EList<Session> getSessions();

} // SessionRepository
