/**
 */
package net.sf.markov4jmeter.behavior.tests;

import junit.textui.TestRunner;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Model Absolute</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehaviorModelAbsoluteTest extends AbstractBehaviorModelGraphTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BehaviorModelAbsoluteTest.class);
	}

	/**
	 * Constructs a new Model Absolute test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehaviorModelAbsoluteTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Model Absolute test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected BehaviorModelAbsolute getFixture() {
		return (BehaviorModelAbsolute)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BehaviorFactory.eINSTANCE.createBehaviorModelAbsolute());
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

} //BehaviorModelAbsoluteTest
