/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior.impl;

import dynamod.behavior.BehaviorPackage;
import dynamod.behavior.Transition;
import dynamod.behavior.Vertex;

import java.math.BigDecimal;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dynamod.behavior.impl.TransitionImpl#getTargetVertex <em>Target Vertex</em>}</li>
 *   <li>{@link dynamod.behavior.impl.TransitionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link dynamod.behavior.impl.TransitionImpl#getTimes <em>Times</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionImpl extends EObjectImpl implements Transition {
    /**
     * The cached value of the '{@link #getTargetVertex() <em>Target Vertex</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetVertex()
     * @generated
     * @ordered
     */
    protected Vertex targetVertex;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final double VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected double value = VALUE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTimes() <em>Times</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimes()
     * @generated
     * @ordered
     */
    protected EList<BigDecimal> times;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TransitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BehaviorPackage.Literals.TRANSITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Vertex getTargetVertex() {
        if (targetVertex != null && targetVertex.eIsProxy()) {
            InternalEObject oldTargetVertex = (InternalEObject)targetVertex;
            targetVertex = (Vertex)eResolveProxy(oldTargetVertex);
            if (targetVertex != oldTargetVertex) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.TRANSITION__TARGET_VERTEX, oldTargetVertex, targetVertex));
            }
        }
        return targetVertex;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Vertex basicGetTargetVertex() {
        return targetVertex;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetVertex(Vertex newTargetVertex) {
        Vertex oldTargetVertex = targetVertex;
        targetVertex = newTargetVertex;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.TRANSITION__TARGET_VERTEX, oldTargetVertex, targetVertex));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(double newValue) {
        double oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.TRANSITION__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<BigDecimal> getTimes() {
        if (times == null) {
            times = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, this, BehaviorPackage.TRANSITION__TIMES);
        }
        return times;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case BehaviorPackage.TRANSITION__TARGET_VERTEX:
                if (resolve) return getTargetVertex();
                return basicGetTargetVertex();
            case BehaviorPackage.TRANSITION__VALUE:
                return getValue();
            case BehaviorPackage.TRANSITION__TIMES:
                return getTimes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case BehaviorPackage.TRANSITION__TARGET_VERTEX:
                setTargetVertex((Vertex)newValue);
                return;
            case BehaviorPackage.TRANSITION__VALUE:
                setValue((Double)newValue);
                return;
            case BehaviorPackage.TRANSITION__TIMES:
                getTimes().clear();
                getTimes().addAll((Collection<? extends BigDecimal>)newValue);
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
            case BehaviorPackage.TRANSITION__TARGET_VERTEX:
                setTargetVertex((Vertex)null);
                return;
            case BehaviorPackage.TRANSITION__VALUE:
                setValue(VALUE_EDEFAULT);
                return;
            case BehaviorPackage.TRANSITION__TIMES:
                getTimes().clear();
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
            case BehaviorPackage.TRANSITION__TARGET_VERTEX:
                return targetVertex != null;
            case BehaviorPackage.TRANSITION__VALUE:
                return value != VALUE_EDEFAULT;
            case BehaviorPackage.TRANSITION__TIMES:
                return times != null && !times.isEmpty();
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
        result.append(" (value: ");
        result.append(value);
        result.append(", times: ");
        result.append(times);
        result.append(')');
        return result.toString();
    }

} //TransitionImpl
