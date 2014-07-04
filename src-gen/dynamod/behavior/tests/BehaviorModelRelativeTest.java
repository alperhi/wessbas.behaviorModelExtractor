/**
 */
package dynamod.behavior.tests;

import dynamod.behavior.BehaviorFactory;
import dynamod.behavior.BehaviorModelRelative;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Model Relative</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BehaviorModelRelativeTest extends AbstractBehaviorModelGraphTest {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args) {
        TestRunner.run(BehaviorModelRelativeTest.class);
    }

    /**
     * Constructs a new Model Relative test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BehaviorModelRelativeTest(String name) {
        super(name);
    }

    /**
     * Returns the fixture for this Model Relative test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected BehaviorModelRelative getFixture() {
        return (BehaviorModelRelative)fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp() throws Exception {
        setFixture(BehaviorFactory.eINSTANCE.createBehaviorModelRelative());
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

} //BehaviorModelRelativeTest
