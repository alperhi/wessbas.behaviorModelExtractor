/***************************************************************************
 * Copyright (c) 2016 the WESSBAS project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/


package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mix Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModelName <em>Behavior Model Name</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getRelativeFrequency <em>Relative Frequency</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModel <em>Behavior Model</em>}</li>
 * </ul>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMixEntry()
 * @model
 * @generated
 */
public interface BehaviorMixEntry extends EObject {
    /**
	 * Returns the value of the '<em><b>Behavior Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Model Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Model Name</em>' attribute.
	 * @see #setBehaviorModelName(String)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMixEntry_BehaviorModelName()
	 * @model required="true"
	 * @generated
	 */
    String getBehaviorModelName();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModelName <em>Behavior Model Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Model Name</em>' attribute.
	 * @see #getBehaviorModelName()
	 * @generated
	 */
    void setBehaviorModelName(String value);

    /**
	 * Returns the value of the '<em><b>Relative Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Relative Frequency</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative Frequency</em>' attribute.
	 * @see #setRelativeFrequency(double)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMixEntry_RelativeFrequency()
	 * @model
	 * @generated
	 */
    double getRelativeFrequency();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getRelativeFrequency <em>Relative Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative Frequency</em>' attribute.
	 * @see #getRelativeFrequency()
	 * @generated
	 */
    void setRelativeFrequency(double value);

    /**
	 * Returns the value of the '<em><b>Behavior Model</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior Model</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior Model</em>' reference.
	 * @see #setBehaviorModel(BehaviorModelRelative)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getBehaviorMixEntry_BehaviorModel()
	 * @model required="true"
	 * @generated
	 */
    BehaviorModelRelative getBehaviorModel();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModel <em>Behavior Model</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior Model</em>' reference.
	 * @see #getBehaviorModel()
	 * @generated
	 */
    void setBehaviorModel(BehaviorModelRelative value);

} // BehaviorMixEntry
