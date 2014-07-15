/**
 */
package net.sf.markov4jmeter.behavior.impl;

import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelRelative;
import net.sf.markov4jmeter.behavior.BehaviorPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mix Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl#getBehaviorModelName <em>Behavior Model Name</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl#getRelativeFrequency <em>Relative Frequency</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.BehaviorMixEntryImpl#getBehaviorModel <em>Behavior Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BehaviorMixEntryImpl extends EObjectImpl implements BehaviorMixEntry {
    /**
     * The default value of the '{@link #getBehaviorModelName() <em>Behavior Model Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviorModelName()
     * @generated
     * @ordered
     */
    protected static final String BEHAVIOR_MODEL_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBehaviorModelName() <em>Behavior Model Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviorModelName()
     * @generated
     * @ordered
     */
    protected String behaviorModelName = BEHAVIOR_MODEL_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getRelativeFrequency() <em>Relative Frequency</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeFrequency()
     * @generated
     * @ordered
     */
    protected static final double RELATIVE_FREQUENCY_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getRelativeFrequency() <em>Relative Frequency</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRelativeFrequency()
     * @generated
     * @ordered
     */
    protected double relativeFrequency = RELATIVE_FREQUENCY_EDEFAULT;

    /**
     * The cached value of the '{@link #getBehaviorModel() <em>Behavior Model</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviorModel()
     * @generated
     * @ordered
     */
    protected BehaviorModelRelative behaviorModel;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BehaviorMixEntryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BehaviorPackage.Literals.BEHAVIOR_MIX_ENTRY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBehaviorModelName() {
        return behaviorModelName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBehaviorModelName(String newBehaviorModelName) {
        String oldBehaviorModelName = behaviorModelName;
        behaviorModelName = newBehaviorModelName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME, oldBehaviorModelName, behaviorModelName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getRelativeFrequency() {
        return relativeFrequency;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRelativeFrequency(double newRelativeFrequency) {
        double oldRelativeFrequency = relativeFrequency;
        relativeFrequency = newRelativeFrequency;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY, oldRelativeFrequency, relativeFrequency));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BehaviorModelRelative getBehaviorModel() {
        if (behaviorModel != null && behaviorModel.eIsProxy()) {
            InternalEObject oldBehaviorModel = (InternalEObject)behaviorModel;
            behaviorModel = (BehaviorModelRelative)eResolveProxy(oldBehaviorModel);
            if (behaviorModel != oldBehaviorModel) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL, oldBehaviorModel, behaviorModel));
            }
        }
        return behaviorModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BehaviorModelRelative basicGetBehaviorModel() {
        return behaviorModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBehaviorModel(BehaviorModelRelative newBehaviorModel) {
        BehaviorModelRelative oldBehaviorModel = behaviorModel;
        behaviorModel = newBehaviorModel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL, oldBehaviorModel, behaviorModel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME:
                return getBehaviorModelName();
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY:
                return getRelativeFrequency();
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL:
                if (resolve) return getBehaviorModel();
                return basicGetBehaviorModel();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME:
                setBehaviorModelName((String)newValue);
                return;
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY:
                setRelativeFrequency((Double)newValue);
                return;
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL:
                setBehaviorModel((BehaviorModelRelative)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME:
                setBehaviorModelName(BEHAVIOR_MODEL_NAME_EDEFAULT);
                return;
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY:
                setRelativeFrequency(RELATIVE_FREQUENCY_EDEFAULT);
                return;
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL:
                setBehaviorModel((BehaviorModelRelative)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL_NAME:
                return BEHAVIOR_MODEL_NAME_EDEFAULT == null ? behaviorModelName != null : !BEHAVIOR_MODEL_NAME_EDEFAULT.equals(behaviorModelName);
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__RELATIVE_FREQUENCY:
                return relativeFrequency != RELATIVE_FREQUENCY_EDEFAULT;
            case BehaviorPackage.BEHAVIOR_MIX_ENTRY__BEHAVIOR_MODEL:
                return behaviorModel != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (behaviorModelName: ");
        result.append(behaviorModelName);
        result.append(", relativeFrequency: ");
        result.append(relativeFrequency);
        result.append(')');
        return result.toString();
    }

} //BehaviorMixEntryImpl
