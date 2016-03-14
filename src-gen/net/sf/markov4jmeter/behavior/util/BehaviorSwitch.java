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


package net.sf.markov4jmeter.behavior.util;

import net.sf.markov4jmeter.behavior.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage
 * @generated
 */
public class BehaviorSwitch<T> extends Switch<T> {
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static BehaviorPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorSwitch() {
		if (modelPackage == null) {
			modelPackage = BehaviorPackage.eINSTANCE;
		}
	}

    /**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BehaviorPackage.USE_CASE: {
				UseCase useCase = (UseCase)theEObject;
				T result = caseUseCase(useCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.USE_CASE_REPOSITORY: {
				UseCaseRepository useCaseRepository = (UseCaseRepository)theEObject;
				T result = caseUseCaseRepository(useCaseRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.SESSION_REPOSITORY: {
				SessionRepository sessionRepository = (SessionRepository)theEObject;
				T result = caseSessionRepository(sessionRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.SESSION: {
				Session session = (Session)theEObject;
				T result = caseSession(session);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.OBSERVED_USE_CASE_EXECUTION: {
				ObservedUseCaseExecution observedUseCaseExecution = (ObservedUseCaseExecution)theEObject;
				T result = caseObservedUseCaseExecution(observedUseCaseExecution);
				if (result == null) result = caseAbstractUseCaseExecution(observedUseCaseExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ABSTRACT_USE_CASE_EXECUTION: {
				AbstractUseCaseExecution abstractUseCaseExecution = (AbstractUseCaseExecution)theEObject;
				T result = caseAbstractUseCaseExecution(abstractUseCaseExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH: {
				AbstractBehaviorModelGraph abstractBehaviorModelGraph = (AbstractBehaviorModelGraph)theEObject;
				T result = caseAbstractBehaviorModelGraph(abstractBehaviorModelGraph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.VERTEX: {
				Vertex vertex = (Vertex)theEObject;
				T result = caseVertex(vertex);
				if (result == null) result = caseAbstractUseCaseExecution(vertex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.TRANSITION: {
				Transition transition = (Transition)theEObject;
				T result = caseTransition(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAVIOR_MODEL_ABSOLUTE: {
				BehaviorModelAbsolute behaviorModelAbsolute = (BehaviorModelAbsolute)theEObject;
				T result = caseBehaviorModelAbsolute(behaviorModelAbsolute);
				if (result == null) result = caseAbstractBehaviorModelGraph(behaviorModelAbsolute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAVIOR_MODEL_RELATIVE: {
				BehaviorModelRelative behaviorModelRelative = (BehaviorModelRelative)theEObject;
				T result = caseBehaviorModelRelative(behaviorModelRelative);
				if (result == null) result = caseAbstractBehaviorModelGraph(behaviorModelRelative);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAVIOR_MIX: {
				BehaviorMix behaviorMix = (BehaviorMix)theEObject;
				T result = caseBehaviorMix(behaviorMix);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BehaviorPackage.BEHAVIOR_MIX_ENTRY: {
				BehaviorMixEntry behaviorMixEntry = (BehaviorMixEntry)theEObject;
				T result = caseBehaviorMixEntry(behaviorMixEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseUseCase(UseCase object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Repository</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseUseCaseRepository(UseCaseRepository object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Session Repository</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Session Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseSessionRepository(SessionRepository object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Session</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Session</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseSession(Session object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Observed Use Case Execution</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Observed Use Case Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseObservedUseCaseExecution(ObservedUseCaseExecution object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Use Case Execution</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Use Case Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseAbstractUseCaseExecution(AbstractUseCaseExecution object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Behavior Model Graph</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Behavior Model Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseAbstractBehaviorModelGraph(AbstractBehaviorModelGraph object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseVertex(Vertex object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseTransition(Transition object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Model Absolute</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Absolute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseBehaviorModelAbsolute(BehaviorModelAbsolute object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Model Relative</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Relative</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseBehaviorModelRelative(BehaviorModelRelative object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Mix</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mix</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseBehaviorMix(BehaviorMix object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Mix Entry</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mix Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public T caseBehaviorMixEntry(BehaviorMixEntry object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    @Override
    public T defaultCase(EObject object) {
		return null;
	}

} //BehaviorSwitch
