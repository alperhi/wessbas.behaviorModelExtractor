/**
 */
package net.sf.markov4jmeter.behavior;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.sf.markov4jmeter.behavior.BehaviorFactory
 * @model kind="package"
 * @generated
 */
public interface BehaviorPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "behavior";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://behavior/1.0";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "behavior";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    BehaviorPackage eINSTANCE = net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl.init();

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.UseCaseImpl <em>Use Case</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.UseCaseImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getUseCase()
	 * @generated
	 */
    int USE_CASE = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USE_CASE__ID = 0;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USE_CASE__NAME = 1;

    /**
	 * The number of structural features of the '<em>Use Case</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USE_CASE_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.UseCaseRepositoryImpl <em>Use Case Repository</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.UseCaseRepositoryImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getUseCaseRepository()
	 * @generated
	 */
    int USE_CASE_REPOSITORY = 1;

    /**
	 * The feature id for the '<em><b>Use Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USE_CASE_REPOSITORY__USE_CASES = 0;

    /**
	 * The number of structural features of the '<em>Use Case Repository</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int USE_CASE_REPOSITORY_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.SessionRepositoryImpl <em>Session Repository</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.SessionRepositoryImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getSessionRepository()
	 * @generated
	 */
    int SESSION_REPOSITORY = 2;

    /**
	 * The feature id for the '<em><b>Sessions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION_REPOSITORY__SESSIONS = 0;

    /**
	 * The number of structural features of the '<em>Session Repository</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION_REPOSITORY_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.SessionImpl <em>Session</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.SessionImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getSession()
	 * @generated
	 */
    int SESSION = 3;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION__ID = 0;

    /**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION__START_TIME = 1;

    /**
	 * The feature id for the '<em><b>End Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION__END_TIME = 2;

    /**
	 * The feature id for the '<em><b>Observed Use Case Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION__OBSERVED_USE_CASE_EXECUTIONS = 3;

    /**
	 * The feature id for the '<em><b>Transaction Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__TRANSACTION_TYPE = 4;

				/**
	 * The number of structural features of the '<em>Session</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SESSION_FEATURE_COUNT = 5;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.AbstractUseCaseExecutionImpl <em>Abstract Use Case Execution</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.AbstractUseCaseExecutionImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getAbstractUseCaseExecution()
	 * @generated
	 */
    int ABSTRACT_USE_CASE_EXECUTION = 5;

    /**
	 * The feature id for the '<em><b>Use Case</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_USE_CASE_EXECUTION__USE_CASE = 0;

    /**
	 * The number of structural features of the '<em>Abstract Use Case Execution</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.ObservedUseCaseExecutionImpl <em>Observed Use Case Execution</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.ObservedUseCaseExecutionImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getObservedUseCaseExecution()
	 * @generated
	 */
    int OBSERVED_USE_CASE_EXECUTION = 4;

    /**
	 * The feature id for the '<em><b>Use Case</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OBSERVED_USE_CASE_EXECUTION__USE_CASE = ABSTRACT_USE_CASE_EXECUTION__USE_CASE;

    /**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OBSERVED_USE_CASE_EXECUTION__START_TIME = ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>End Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OBSERVED_USE_CASE_EXECUTION__END_TIME = ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Observed Use Case Execution</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OBSERVED_USE_CASE_EXECUTION_FEATURE_COUNT = ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl <em>Abstract Behavior Model Graph</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getAbstractBehaviorModelGraph()
	 * @generated
	 */
    int ABSTRACT_BEHAVIOR_MODEL_GRAPH = 6;

    /**
	 * The feature id for the '<em><b>Vertices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES = 0;

    /**
	 * The feature id for the '<em><b>Transaction Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE = 1;

				/**
	 * The number of structural features of the '<em>Abstract Behavior Model Graph</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ABSTRACT_BEHAVIOR_MODEL_GRAPH_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.VertexImpl <em>Vertex</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.VertexImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getVertex()
	 * @generated
	 */
    int VERTEX = 7;

    /**
	 * The feature id for the '<em><b>Use Case</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VERTEX__USE_CASE = ABSTRACT_USE_CASE_EXECUTION__USE_CASE;

    /**
	 * The feature id for the '<em><b>Outgoing Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VERTEX__OUTGOING_TRANSITIONS = ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VERTEX_FEATURE_COUNT = ABSTRACT_USE_CASE_EXECUTION_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.TransitionImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getTransition()
	 * @generated
	 */
    int TRANSITION = 8;

    /**
	 * The feature id for the '<em><b>Target Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TRANSITION__TARGET_VERTEX = 0;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TRANSITION__VALUE = 1;

    /**
	 * The feature id for the '<em><b>Time Diffs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TRANSITION__TIME_DIFFS = 2;

    /**
	 * The feature id for the '<em><b>Think Time Params</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TRANSITION__THINK_TIME_PARAMS = 3;

    /**
	 * The feature id for the '<em><b>Source Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__SOURCE_VERTEX = 4;

				/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TRANSITION_FEATURE_COUNT = 5;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorModelAbsoluteImpl <em>Model Absolute</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorModelAbsoluteImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorModelAbsolute()
	 * @generated
	 */
    int BEHAVIOR_MODEL_ABSOLUTE = 9;

    /**
	 * The feature id for the '<em><b>Vertices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MODEL_ABSOLUTE__VERTICES = ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES;

    /**
	 * The feature id for the '<em><b>Transaction Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_MODEL_ABSOLUTE__TRANSACTION_TYPE = ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE;

				/**
	 * The number of structural features of the '<em>Model Absolute</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MODEL_ABSOLUTE_FEATURE_COUNT = ABSTRACT_BEHAVIOR_MODEL_GRAPH_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorModelRelativeImpl <em>Model Relative</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorModelRelativeImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorModelRelative()
	 * @generated
	 */
    int BEHAVIOR_MODEL_RELATIVE = 10;

    /**
	 * The feature id for the '<em><b>Vertices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MODEL_RELATIVE__VERTICES = ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES;

    /**
	 * The feature id for the '<em><b>Transaction Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOR_MODEL_RELATIVE__TRANSACTION_TYPE = ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE;

				/**
	 * The number of structural features of the '<em>Model Relative</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MODEL_RELATIVE_FEATURE_COUNT = ABSTRACT_BEHAVIOR_MODEL_GRAPH_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixImpl <em>Mix</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorMixImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorMix()
	 * @generated
	 */
    int BEHAVIOR_MIX = 11;

    /**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX__ENTRIES = 0;

    /**
	 * The number of structural features of the '<em>Mix</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl <em>Mix Entry</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl
	 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorMixEntry()
	 * @generated
	 */
    int BEHAVIOR_MIX_ENTRY = 12;

    /**
	 * The feature id for the '<em><b>Behavior Model Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME = 0;

    /**
	 * The feature id for the '<em><b>Relative Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY = 1;

    /**
	 * The feature id for the '<em><b>Behavior Model</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL = 2;

    /**
	 * The number of structural features of the '<em>Mix Entry</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BEHAVIOR_MIX_ENTRY_FEATURE_COUNT = 3;


    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.UseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case</em>'.
	 * @see net.sf.markov4jmeter.behavior.UseCase
	 * @generated
	 */
    EClass getUseCase();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.UseCase#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.sf.markov4jmeter.behavior.UseCase#getId()
	 * @see #getUseCase()
	 * @generated
	 */
    EAttribute getUseCase_Id();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.UseCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.sf.markov4jmeter.behavior.UseCase#getName()
	 * @see #getUseCase()
	 * @generated
	 */
    EAttribute getUseCase_Name();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.UseCaseRepository <em>Use Case Repository</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Repository</em>'.
	 * @see net.sf.markov4jmeter.behavior.UseCaseRepository
	 * @generated
	 */
    EClass getUseCaseRepository();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.UseCaseRepository#getUseCases <em>Use Cases</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Use Cases</em>'.
	 * @see net.sf.markov4jmeter.behavior.UseCaseRepository#getUseCases()
	 * @see #getUseCaseRepository()
	 * @generated
	 */
    EReference getUseCaseRepository_UseCases();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.SessionRepository <em>Session Repository</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session Repository</em>'.
	 * @see net.sf.markov4jmeter.behavior.SessionRepository
	 * @generated
	 */
    EClass getSessionRepository();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.SessionRepository#getSessions <em>Sessions</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sessions</em>'.
	 * @see net.sf.markov4jmeter.behavior.SessionRepository#getSessions()
	 * @see #getSessionRepository()
	 * @generated
	 */
    EReference getSessionRepository_Sessions();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.Session <em>Session</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session
	 * @generated
	 */
    EClass getSession();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.Session#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session#getId()
	 * @see #getSession()
	 * @generated
	 */
    EAttribute getSession_Id();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.Session#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session#getStartTime()
	 * @see #getSession()
	 * @generated
	 */
    EAttribute getSession_StartTime();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.Session#getEndTime <em>End Time</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session#getEndTime()
	 * @see #getSession()
	 * @generated
	 */
    EAttribute getSession_EndTime();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.Session#getObservedUseCaseExecutions <em>Observed Use Case Executions</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Observed Use Case Executions</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session#getObservedUseCaseExecutions()
	 * @see #getSession()
	 * @generated
	 */
    EReference getSession_ObservedUseCaseExecutions();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.Session#getTransactionType <em>Transaction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Type</em>'.
	 * @see net.sf.markov4jmeter.behavior.Session#getTransactionType()
	 * @see #getSession()
	 * @generated
	 */
	EAttribute getSession_TransactionType();

				/**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.ObservedUseCaseExecution <em>Observed Use Case Execution</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Observed Use Case Execution</em>'.
	 * @see net.sf.markov4jmeter.behavior.ObservedUseCaseExecution
	 * @generated
	 */
    EClass getObservedUseCaseExecution();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.ObservedUseCaseExecution#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see net.sf.markov4jmeter.behavior.ObservedUseCaseExecution#getStartTime()
	 * @see #getObservedUseCaseExecution()
	 * @generated
	 */
    EAttribute getObservedUseCaseExecution_StartTime();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.ObservedUseCaseExecution#getEndTime <em>End Time</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Time</em>'.
	 * @see net.sf.markov4jmeter.behavior.ObservedUseCaseExecution#getEndTime()
	 * @see #getObservedUseCaseExecution()
	 * @generated
	 */
    EAttribute getObservedUseCaseExecution_EndTime();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.AbstractUseCaseExecution <em>Abstract Use Case Execution</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Use Case Execution</em>'.
	 * @see net.sf.markov4jmeter.behavior.AbstractUseCaseExecution
	 * @generated
	 */
    EClass getAbstractUseCaseExecution();

    /**
	 * Returns the meta object for the reference '{@link net.sf.markov4jmeter.behavior.AbstractUseCaseExecution#getUseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Use Case</em>'.
	 * @see net.sf.markov4jmeter.behavior.AbstractUseCaseExecution#getUseCase()
	 * @see #getAbstractUseCaseExecution()
	 * @generated
	 */
    EReference getAbstractUseCaseExecution_UseCase();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph <em>Abstract Behavior Model Graph</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Behavior Model Graph</em>'.
	 * @see net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph
	 * @generated
	 */
    EClass getAbstractBehaviorModelGraph();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph#getVertices <em>Vertices</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vertices</em>'.
	 * @see net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph#getVertices()
	 * @see #getAbstractBehaviorModelGraph()
	 * @generated
	 */
    EReference getAbstractBehaviorModelGraph_Vertices();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph#getTransactionType <em>Transaction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Type</em>'.
	 * @see net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph#getTransactionType()
	 * @see #getAbstractBehaviorModelGraph()
	 * @generated
	 */
	EAttribute getAbstractBehaviorModelGraph_TransactionType();

				/**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex</em>'.
	 * @see net.sf.markov4jmeter.behavior.Vertex
	 * @generated
	 */
    EClass getVertex();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.Vertex#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Transitions</em>'.
	 * @see net.sf.markov4jmeter.behavior.Vertex#getOutgoingTransitions()
	 * @see #getVertex()
	 * @generated
	 */
    EReference getVertex_OutgoingTransitions();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition
	 * @generated
	 */
    EClass getTransition();

    /**
	 * Returns the meta object for the reference '{@link net.sf.markov4jmeter.behavior.Transition#getTargetVertex <em>Target Vertex</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Vertex</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition#getTargetVertex()
	 * @see #getTransition()
	 * @generated
	 */
    EReference getTransition_TargetVertex();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.Transition#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition#getValue()
	 * @see #getTransition()
	 * @generated
	 */
    EAttribute getTransition_Value();

    /**
	 * Returns the meta object for the attribute list '{@link net.sf.markov4jmeter.behavior.Transition#getTimeDiffs <em>Time Diffs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Time Diffs</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition#getTimeDiffs()
	 * @see #getTransition()
	 * @generated
	 */
    EAttribute getTransition_TimeDiffs();

    /**
	 * Returns the meta object for the attribute list '{@link net.sf.markov4jmeter.behavior.Transition#getThinkTimeParams <em>Think Time Params</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Think Time Params</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition#getThinkTimeParams()
	 * @see #getTransition()
	 * @generated
	 */
    EAttribute getTransition_ThinkTimeParams();

    /**
	 * Returns the meta object for the reference '{@link net.sf.markov4jmeter.behavior.Transition#getSourceVertex <em>Source Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Vertex</em>'.
	 * @see net.sf.markov4jmeter.behavior.Transition#getSourceVertex()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_SourceVertex();

				/**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.BehaviorModelAbsolute <em>Model Absolute</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Absolute</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorModelAbsolute
	 * @generated
	 */
    EClass getBehaviorModelAbsolute();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.BehaviorModelRelative <em>Model Relative</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Relative</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorModelRelative
	 * @generated
	 */
    EClass getBehaviorModelRelative();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.BehaviorMix <em>Mix</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mix</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMix
	 * @generated
	 */
    EClass getBehaviorMix();

    /**
	 * Returns the meta object for the containment reference list '{@link net.sf.markov4jmeter.behavior.BehaviorMix#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMix#getEntries()
	 * @see #getBehaviorMix()
	 * @generated
	 */
    EReference getBehaviorMix_Entries();

    /**
	 * Returns the meta object for class '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry <em>Mix Entry</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mix Entry</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMixEntry
	 * @generated
	 */
    EClass getBehaviorMixEntry();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModelName <em>Behavior Model Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Behavior Model Name</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModelName()
	 * @see #getBehaviorMixEntry()
	 * @generated
	 */
    EAttribute getBehaviorMixEntry_BehaviorModelName();

    /**
	 * Returns the meta object for the attribute '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getRelativeFrequency <em>Relative Frequency</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative Frequency</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMixEntry#getRelativeFrequency()
	 * @see #getBehaviorMixEntry()
	 * @generated
	 */
    EAttribute getBehaviorMixEntry_RelativeFrequency();

    /**
	 * Returns the meta object for the reference '{@link net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModel <em>Behavior Model</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Behavior Model</em>'.
	 * @see net.sf.markov4jmeter.behavior.BehaviorMixEntry#getBehaviorModel()
	 * @see #getBehaviorMixEntry()
	 * @generated
	 */
    EReference getBehaviorMixEntry_BehaviorModel();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    BehaviorFactory getBehaviorFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
    interface Literals {
        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.UseCaseImpl <em>Use Case</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.UseCaseImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getUseCase()
		 * @generated
		 */
        EClass USE_CASE = eINSTANCE.getUseCase();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute USE_CASE__ID = eINSTANCE.getUseCase_Id();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute USE_CASE__NAME = eINSTANCE.getUseCase_Name();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.UseCaseRepositoryImpl <em>Use Case Repository</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.UseCaseRepositoryImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getUseCaseRepository()
		 * @generated
		 */
        EClass USE_CASE_REPOSITORY = eINSTANCE.getUseCaseRepository();

        /**
		 * The meta object literal for the '<em><b>Use Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference USE_CASE_REPOSITORY__USE_CASES = eINSTANCE.getUseCaseRepository_UseCases();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.SessionRepositoryImpl <em>Session Repository</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.SessionRepositoryImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getSessionRepository()
		 * @generated
		 */
        EClass SESSION_REPOSITORY = eINSTANCE.getSessionRepository();

        /**
		 * The meta object literal for the '<em><b>Sessions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SESSION_REPOSITORY__SESSIONS = eINSTANCE.getSessionRepository_Sessions();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.SessionImpl <em>Session</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.SessionImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getSession()
		 * @generated
		 */
        EClass SESSION = eINSTANCE.getSession();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SESSION__ID = eINSTANCE.getSession_Id();

        /**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SESSION__START_TIME = eINSTANCE.getSession_StartTime();

        /**
		 * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SESSION__END_TIME = eINSTANCE.getSession_EndTime();

        /**
		 * The meta object literal for the '<em><b>Observed Use Case Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SESSION__OBSERVED_USE_CASE_EXECUTIONS = eINSTANCE.getSession_ObservedUseCaseExecutions();

        /**
		 * The meta object literal for the '<em><b>Transaction Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SESSION__TRANSACTION_TYPE = eINSTANCE.getSession_TransactionType();

								/**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.ObservedUseCaseExecutionImpl <em>Observed Use Case Execution</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.ObservedUseCaseExecutionImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getObservedUseCaseExecution()
		 * @generated
		 */
        EClass OBSERVED_USE_CASE_EXECUTION = eINSTANCE.getObservedUseCaseExecution();

        /**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute OBSERVED_USE_CASE_EXECUTION__START_TIME = eINSTANCE.getObservedUseCaseExecution_StartTime();

        /**
		 * The meta object literal for the '<em><b>End Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute OBSERVED_USE_CASE_EXECUTION__END_TIME = eINSTANCE.getObservedUseCaseExecution_EndTime();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.AbstractUseCaseExecutionImpl <em>Abstract Use Case Execution</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.AbstractUseCaseExecutionImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getAbstractUseCaseExecution()
		 * @generated
		 */
        EClass ABSTRACT_USE_CASE_EXECUTION = eINSTANCE.getAbstractUseCaseExecution();

        /**
		 * The meta object literal for the '<em><b>Use Case</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ABSTRACT_USE_CASE_EXECUTION__USE_CASE = eINSTANCE.getAbstractUseCaseExecution_UseCase();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl <em>Abstract Behavior Model Graph</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getAbstractBehaviorModelGraph()
		 * @generated
		 */
        EClass ABSTRACT_BEHAVIOR_MODEL_GRAPH = eINSTANCE.getAbstractBehaviorModelGraph();

        /**
		 * The meta object literal for the '<em><b>Vertices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES = eINSTANCE.getAbstractBehaviorModelGraph_Vertices();

        /**
		 * The meta object literal for the '<em><b>Transaction Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE = eINSTANCE.getAbstractBehaviorModelGraph_TransactionType();

								/**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.VertexImpl <em>Vertex</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.VertexImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getVertex()
		 * @generated
		 */
        EClass VERTEX = eINSTANCE.getVertex();

        /**
		 * The meta object literal for the '<em><b>Outgoing Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference VERTEX__OUTGOING_TRANSITIONS = eINSTANCE.getVertex_OutgoingTransitions();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.TransitionImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getTransition()
		 * @generated
		 */
        EClass TRANSITION = eINSTANCE.getTransition();

        /**
		 * The meta object literal for the '<em><b>Target Vertex</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference TRANSITION__TARGET_VERTEX = eINSTANCE.getTransition_TargetVertex();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute TRANSITION__VALUE = eINSTANCE.getTransition_Value();

        /**
		 * The meta object literal for the '<em><b>Time Diffs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute TRANSITION__TIME_DIFFS = eINSTANCE.getTransition_TimeDiffs();

        /**
		 * The meta object literal for the '<em><b>Think Time Params</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute TRANSITION__THINK_TIME_PARAMS = eINSTANCE.getTransition_ThinkTimeParams();

        /**
		 * The meta object literal for the '<em><b>Source Vertex</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__SOURCE_VERTEX = eINSTANCE.getTransition_SourceVertex();

								/**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorModelAbsoluteImpl <em>Model Absolute</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorModelAbsoluteImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorModelAbsolute()
		 * @generated
		 */
        EClass BEHAVIOR_MODEL_ABSOLUTE = eINSTANCE.getBehaviorModelAbsolute();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorModelRelativeImpl <em>Model Relative</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorModelRelativeImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorModelRelative()
		 * @generated
		 */
        EClass BEHAVIOR_MODEL_RELATIVE = eINSTANCE.getBehaviorModelRelative();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixImpl <em>Mix</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorMixImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorMix()
		 * @generated
		 */
        EClass BEHAVIOR_MIX = eINSTANCE.getBehaviorMix();

        /**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BEHAVIOR_MIX__ENTRIES = eINSTANCE.getBehaviorMix_Entries();

        /**
		 * The meta object literal for the '{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl <em>Mix Entry</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl
		 * @see net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl#getBehaviorMixEntry()
		 * @generated
		 */
        EClass BEHAVIOR_MIX_ENTRY = eINSTANCE.getBehaviorMixEntry();

        /**
		 * The meta object literal for the '<em><b>Behavior Model Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME = eINSTANCE.getBehaviorMixEntry_BehaviorModelName();

        /**
		 * The meta object literal for the '<em><b>Relative Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY = eINSTANCE.getBehaviorMixEntry_RelativeFrequency();

        /**
		 * The meta object literal for the '<em><b>Behavior Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL = eINSTANCE.getBehaviorMixEntry_BehaviorModel();

    }

} //BehaviorPackage
