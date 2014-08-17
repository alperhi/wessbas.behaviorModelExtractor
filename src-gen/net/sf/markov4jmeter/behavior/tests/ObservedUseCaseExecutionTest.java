/**
 */
package net.sf.markov4jmeter.behavior.tests;

import junit.textui.TestRunner;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.ObservedUseCaseExecution;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Observed Use Case Execution</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ObservedUseCaseExecutionTest extends AbstractUseCaseExecutionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ObservedUseCaseExecutionTest.class);
	}

	/**
	 * Constructs a new Observed Use Case Execution test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObservedUseCaseExecutionTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Observed Use Case Execution test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ObservedUseCaseExecution getFixture() {
		return (ObservedUseCaseExecution)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BehaviorFactory.eINSTANCE.createObservedUseCaseExecution());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ObservedUseCaseExecutionTest
