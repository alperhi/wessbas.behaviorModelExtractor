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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.BehaviorMix#getEntries <em>Entries</em>}</li>
 * </ul>
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
