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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage
 * @generated
 */
public interface BehaviorFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    BehaviorFactory eINSTANCE = net.sf.markov4jmeter.behavior.impl.BehaviorFactoryImpl.init();

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
	 * Returns a new object of class '<em>Mix</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mix</em>'.
	 * @generated
	 */
    BehaviorMix createBehaviorMix();

    /**
	 * Returns a new object of class '<em>Mix Entry</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mix Entry</em>'.
	 * @generated
	 */
    BehaviorMixEntry createBehaviorMixEntry();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    BehaviorPackage getBehaviorPackage();

} //BehaviorFactory
