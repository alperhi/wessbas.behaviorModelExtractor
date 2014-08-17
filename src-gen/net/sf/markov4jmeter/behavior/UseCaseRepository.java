/**
 */
package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.UseCaseRepository#getUseCases <em>Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getUseCaseRepository()
 * @model
 * @generated
 */
public interface UseCaseRepository extends EObject {
    /**
	 * Returns the value of the '<em><b>Use Cases</b></em>' containment reference list.
	 * The list contents are of type {@link net.sf.markov4jmeter.behavior.UseCase}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Use Cases</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Cases</em>' containment reference list.
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getUseCaseRepository_UseCases()
	 * @model containment="true"
	 * @generated
	 */
    EList<UseCase> getUseCases();

} // UseCaseRepository
