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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage
 * @generated
 */
public class BehaviorAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static BehaviorPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = BehaviorPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected BehaviorSwitch<Adapter> modelSwitch =
        new BehaviorSwitch<Adapter>() {
			@Override
			public Adapter caseUseCase(UseCase object) {
				return createUseCaseAdapter();
			}
			@Override
			public Adapter caseUseCaseRepository(UseCaseRepository object) {
				return createUseCaseRepositoryAdapter();
			}
			@Override
			public Adapter caseSessionRepository(SessionRepository object) {
				return createSessionRepositoryAdapter();
			}
			@Override
			public Adapter caseSession(Session object) {
				return createSessionAdapter();
			}
			@Override
			public Adapter caseObservedUseCaseExecution(ObservedUseCaseExecution object) {
				return createObservedUseCaseExecutionAdapter();
			}
			@Override
			public Adapter caseAbstractUseCaseExecution(AbstractUseCaseExecution object) {
				return createAbstractUseCaseExecutionAdapter();
			}
			@Override
			public Adapter caseAbstractBehaviorModelGraph(AbstractBehaviorModelGraph object) {
				return createAbstractBehaviorModelGraphAdapter();
			}
			@Override
			public Adapter caseVertex(Vertex object) {
				return createVertexAdapter();
			}
			@Override
			public Adapter caseTransition(Transition object) {
				return createTransitionAdapter();
			}
			@Override
			public Adapter caseBehaviorModelAbsolute(BehaviorModelAbsolute object) {
				return createBehaviorModelAbsoluteAdapter();
			}
			@Override
			public Adapter caseBehaviorModelRelative(BehaviorModelRelative object) {
				return createBehaviorModelRelativeAdapter();
			}
			@Override
			public Adapter caseBehaviorMix(BehaviorMix object) {
				return createBehaviorMixAdapter();
			}
			@Override
			public Adapter caseBehaviorMixEntry(BehaviorMixEntry object) {
				return createBehaviorMixEntryAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    @Override
    public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.UseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.UseCase
	 * @generated
	 */
    public Adapter createUseCaseAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.UseCaseRepository <em>Use Case Repository</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.UseCaseRepository
	 * @generated
	 */
    public Adapter createUseCaseRepositoryAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.SessionRepository <em>Session Repository</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.SessionRepository
	 * @generated
	 */
    public Adapter createSessionRepositoryAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.Session <em>Session</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.Session
	 * @generated
	 */
    public Adapter createSessionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.ObservedUseCaseExecution <em>Observed Use Case Execution</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.ObservedUseCaseExecution
	 * @generated
	 */
    public Adapter createObservedUseCaseExecutionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.AbstractUseCaseExecution <em>Abstract Use Case Execution</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.AbstractUseCaseExecution
	 * @generated
	 */
    public Adapter createAbstractUseCaseExecutionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph <em>Abstract Behavior Model Graph</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph
	 * @generated
	 */
    public Adapter createAbstractBehaviorModelGraphAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.Vertex
	 * @generated
	 */
    public Adapter createVertexAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.Transition
	 * @generated
	 */
    public Adapter createTransitionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.BehaviorModelAbsolute <em>Model Absolute</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.BehaviorModelAbsolute
	 * @generated
	 */
    public Adapter createBehaviorModelAbsoluteAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.BehaviorModelRelative <em>Model Relative</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.BehaviorModelRelative
	 * @generated
	 */
    public Adapter createBehaviorModelRelativeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.BehaviorMix <em>Mix</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMix
	 * @generated
	 */
    public Adapter createBehaviorMixAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry <em>Mix Entry</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMixEntry
	 * @generated
	 */
    public Adapter createBehaviorMixEntryAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter() {
		return null;
	}

} //BehaviorAdapterFactory
