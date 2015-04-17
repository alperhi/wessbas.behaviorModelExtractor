/**
 */
package net.sf.markov4jmeter.behavior.impl;

import net.sf.markov4jmeter.behavior.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehaviorFactoryImpl extends EFactoryImpl implements BehaviorFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static BehaviorFactory init() {
		try {
			BehaviorFactory theBehaviorFactory = (BehaviorFactory)EPackage.Registry.INSTANCE.getEFactory(BehaviorPackage.eNS_URI);
			if (theBehaviorFactory != null) {
				return theBehaviorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BehaviorFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BehaviorPackage.USE_CASE: return createUseCase();
			case BehaviorPackage.USE_CASE_REPOSITORY: return createUseCaseRepository();
			case BehaviorPackage.SESSION_REPOSITORY: return createSessionRepository();
			case BehaviorPackage.SESSION: return createSession();
			case BehaviorPackage.OBSERVED_USE_CASE_EXECUTION: return createObservedUseCaseExecution();
			case BehaviorPackage.VERTEX: return createVertex();
			case BehaviorPackage.TRANSITION: return createTransition();
			case BehaviorPackage.BEHAVIOR_MODEL_ABSOLUTE: return createBehaviorModelAbsolute();
			case BehaviorPackage.BEHAVIOR_MODEL_RELATIVE: return createBehaviorModelRelative();
			case BehaviorPackage.BEHAVIOR_MIX: return createBehaviorMix();
			case BehaviorPackage.BEHAVIOR_MIX_ENTRY: return createBehaviorMixEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UseCase createUseCase() {
		UseCaseImpl useCase = new UseCaseImpl();
		return useCase;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UseCaseRepository createUseCaseRepository() {
		UseCaseRepositoryImpl useCaseRepository = new UseCaseRepositoryImpl();
		return useCaseRepository;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SessionRepository createSessionRepository() {
		SessionRepositoryImpl sessionRepository = new SessionRepositoryImpl();
		return sessionRepository;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Session createSession() {
		SessionImpl session = new SessionImpl();
		return session;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ObservedUseCaseExecution createObservedUseCaseExecution() {
		ObservedUseCaseExecutionImpl observedUseCaseExecution = new ObservedUseCaseExecutionImpl();
		return observedUseCaseExecution;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Vertex createVertex() {
		VertexImpl vertex = new VertexImpl();
		return vertex;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorModelAbsolute createBehaviorModelAbsolute() {
		BehaviorModelAbsoluteImpl behaviorModelAbsolute = new BehaviorModelAbsoluteImpl();
		return behaviorModelAbsolute;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorModelRelative createBehaviorModelRelative() {
		BehaviorModelRelativeImpl behaviorModelRelative = new BehaviorModelRelativeImpl();
		return behaviorModelRelative;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorMix createBehaviorMix() {
		BehaviorMixImpl behaviorMix = new BehaviorMixImpl();
		return behaviorMix;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorMixEntry createBehaviorMixEntry() {
		BehaviorMixEntryImpl behaviorMixEntry = new BehaviorMixEntryImpl();
		return behaviorMixEntry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public BehaviorPackage getBehaviorPackage() {
		return (BehaviorPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static BehaviorPackage getPackage() {
		return BehaviorPackage.eINSTANCE;
	}

} //BehaviorFactoryImpl
