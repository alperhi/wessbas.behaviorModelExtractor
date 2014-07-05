/**
 */
package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.BehaviorMix#getEntries <em>Entries</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMix()
 * @model
 * @generated
 */
public interface BehaviorMix extends EObject {
    /**
     * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
     * The list contents are of type {@link net.sf.markov4jmeter.behavior.BehaviorMixEntry}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Entries</em>' containment reference list.
     * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMix_Entries()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BehaviorMixEntry> getEntries();

} // BehaviorMix
