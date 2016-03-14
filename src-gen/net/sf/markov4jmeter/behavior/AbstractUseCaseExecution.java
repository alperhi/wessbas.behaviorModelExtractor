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
 * A representation of the model object '<em><b>Abstract Use Case Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.AbstractUseCaseExecution#getUseCase <em>Use Case</em>}</li>
 * </ul>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getAbstractUseCaseExecution()
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
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getAbstractUseCaseExecution_UseCase()
	 * @model required="true"
	 * @generated
	 */
    UseCase getUseCase();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.AbstractUseCaseExecution#getUseCase <em>Use Case</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Case</em>' reference.
	 * @see #getUseCase()
	 * @generated
	 */
    void setUseCase(UseCase value);

} // AbstractUseCaseExecution
