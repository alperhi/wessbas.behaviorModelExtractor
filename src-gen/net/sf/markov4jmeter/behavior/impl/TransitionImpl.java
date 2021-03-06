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


package net.sf.markov4jmeter.behavior.impl;

import java.math.BigDecimal;

import java.util.Collection;

import net.sf.markov4jmeter.behavior.BehaviorPackage;
import net.sf.markov4jmeter.behavior.Transition;
import net.sf.markov4jmeter.behavior.Vertex;

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
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl#getTargetVertex <em>Target Vertex</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl#getTimeDiffs <em>Time Diffs</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl#getThinkTimeParams <em>Think Time Params</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.TransitionImpl#getSourceVertex <em>Source Vertex</em>}</li>
 * </ul>
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
	 * The cached value of the '{@link #getTimeDiffs() <em>Time Diffs</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTimeDiffs()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> timeDiffs;

    /**
	 * The cached value of the '{@link #getThinkTimeParams() <em>Think Time Params</em>}' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getThinkTimeParams()
	 * @generated
	 * @ordered
	 */
    protected EList<BigDecimal> thinkTimeParams;

    /**
	 * The cached value of the '{@link #getSourceVertex() <em>Source Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceVertex()
	 * @generated
	 * @ordered
	 */
	protected Vertex sourceVertex;

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
    public EList<BigDecimal> getTimeDiffs() {
		if (timeDiffs == null) {
			timeDiffs = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, this, BehaviorPackage.TRANSITION__TIME_DIFFS);
		}
		return timeDiffs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<BigDecimal> getThinkTimeParams() {
		if (thinkTimeParams == null) {
			thinkTimeParams = new EDataTypeUniqueEList<BigDecimal>(BigDecimal.class, this, BehaviorPackage.TRANSITION__THINK_TIME_PARAMS);
		}
		return thinkTimeParams;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex getSourceVertex() {
		if (sourceVertex != null && sourceVertex.eIsProxy()) {
			InternalEObject oldSourceVertex = (InternalEObject)sourceVertex;
			sourceVertex = (Vertex)eResolveProxy(oldSourceVertex);
			if (sourceVertex != oldSourceVertex) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehaviorPackage.TRANSITION__SOURCE_VERTEX, oldSourceVertex, sourceVertex));
			}
		}
		return sourceVertex;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex basicGetSourceVertex() {
		return sourceVertex;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceVertex(Vertex newSourceVertex) {
		Vertex oldSourceVertex = sourceVertex;
		sourceVertex = newSourceVertex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.TRANSITION__SOURCE_VERTEX, oldSourceVertex, sourceVertex));
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
			case BehaviorPackage.TRANSITION__TIME_DIFFS:
				return getTimeDiffs();
			case BehaviorPackage.TRANSITION__THINK_TIME_PARAMS:
				return getThinkTimeParams();
			case BehaviorPackage.TRANSITION__SOURCE_VERTEX:
				if (resolve) return getSourceVertex();
				return basicGetSourceVertex();
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
			case BehaviorPackage.TRANSITION__TIME_DIFFS:
				getTimeDiffs().clear();
				getTimeDiffs().addAll((Collection<? extends BigDecimal>)newValue);
				return;
			case BehaviorPackage.TRANSITION__THINK_TIME_PARAMS:
				getThinkTimeParams().clear();
				getThinkTimeParams().addAll((Collection<? extends BigDecimal>)newValue);
				return;
			case BehaviorPackage.TRANSITION__SOURCE_VERTEX:
				setSourceVertex((Vertex)newValue);
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
			case BehaviorPackage.TRANSITION__TIME_DIFFS:
				getTimeDiffs().clear();
				return;
			case BehaviorPackage.TRANSITION__THINK_TIME_PARAMS:
				getThinkTimeParams().clear();
				return;
			case BehaviorPackage.TRANSITION__SOURCE_VERTEX:
				setSourceVertex((Vertex)null);
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
			case BehaviorPackage.TRANSITION__TIME_DIFFS:
				return timeDiffs != null && !timeDiffs.isEmpty();
			case BehaviorPackage.TRANSITION__THINK_TIME_PARAMS:
				return thinkTimeParams != null && !thinkTimeParams.isEmpty();
			case BehaviorPackage.TRANSITION__SOURCE_VERTEX:
				return sourceVertex != null;
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
		result.append(", timeDiffs: ");
		result.append(timeDiffs);
		result.append(", thinkTimeParams: ");
		result.append(thinkTimeParams);
		result.append(')');
		return result.toString();
	}

} //TransitionImpl
