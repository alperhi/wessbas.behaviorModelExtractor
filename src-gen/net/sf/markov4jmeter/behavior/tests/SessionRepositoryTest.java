/**
 */
package net.sf.markov4jmeter.behavior.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.SessionRepository;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Session Repository</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SessionRepositoryTest extends TestCase {

	/**
	 * The fixture for this Session Repository test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionRepository fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SessionRepositoryTest.class);
	}

	/**
	 * Constructs a new Session Repository test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SessionRepositoryTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Session Repository test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SessionRepository fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Session Repository test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionRepository getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BehaviorFactory.eINSTANCE.createSessionRepository());
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

} //SessionRepositoryTest
