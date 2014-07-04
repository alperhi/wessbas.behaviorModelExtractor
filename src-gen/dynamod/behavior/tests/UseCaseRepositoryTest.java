/**
 */
package dynamod.behavior.tests;

import dynamod.behavior.BehaviorFactory;
import dynamod.behavior.UseCaseRepository;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Use Case Repository</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class UseCaseRepositoryTest extends TestCase {

    /**
     * The fixture for this Use Case Repository test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UseCaseRepository fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(UseCaseRepositoryTest.class);
    }

    /**
     * Constructs a new Use Case Repository test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UseCaseRepositoryTest(String name) {
        super(name);
    }

    /**
     * Sets the fixture for this Use Case Repository test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(UseCaseRepository fixture) {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Use Case Repository test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UseCaseRepository getFixture() {
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
        setFixture(BehaviorFactory.eINSTANCE.createUseCaseRepository());
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

} //UseCaseRepositoryTest
