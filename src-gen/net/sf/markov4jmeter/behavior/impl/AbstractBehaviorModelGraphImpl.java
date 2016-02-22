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

import java.util.Collection;

import net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph;
import net.sf.markov4jmeter.behavior.BehaviorPackage;
import net.sf.markov4jmeter.behavior.Vertex;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Behavior Model Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl#getVertices <em>Vertices</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl#getTransactionType <em>Transaction Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractBehaviorModelGraphImpl extends EObjectImpl implements AbstractBehaviorModelGraph {
    /**
	 * The cached value of the '{@link #getVertices() <em>Vertices</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVertices()
	 * @generated
	 * @ordered
	 */
    protected EList<Vertex> vertices;

    /**
	 * The default value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSACTION_TYPE_EDEFAULT = "noType";
				/**
	 * The cached value of the '{@link #getTransactionType() <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransactionType()
	 * @generated
	 * @ordered
	 */
	protected String transactionType = TRANSACTION_TYPE_EDEFAULT;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected AbstractBehaviorModelGraphImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return BehaviorPackage.Literals.ABSTRACT_BEHAVIOR_MODEL_GRAPH;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Vertex> getVertices() {
		if (vertices == null) {
			vertices = new EObjectContainmentEList<Vertex>(Vertex.class, this, BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES);
		}
		return vertices;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransactionType() {
		return transactionType;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransactionType(String newTransactionType) {
		String oldTransactionType = transactionType;
		transactionType = newTransactionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE, oldTransactionType, transactionType));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES:
				return ((InternalEList<?>)getVertices()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES:
				return getVertices();
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE:
				return getTransactionType();
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
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES:
				getVertices().clear();
				getVertices().addAll((Collection<? extends Vertex>)newValue);
				return;
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE:
				setTransactionType((String)newValue);
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
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES:
				getVertices().clear();
				return;
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE:
				setTransactionType(TRANSACTION_TYPE_EDEFAULT);
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
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__VERTICES:
				return vertices != null && !vertices.isEmpty();
			case BehaviorPackage.ABSTRACT_BEHAVIOR_MODEL_GRAPH__TRANSACTION_TYPE:
				return TRANSACTION_TYPE_EDEFAULT == null ? transactionType != null : !TRANSACTION_TYPE_EDEFAULT.equals(transactionType);
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
		result.append(" (transactionType: ");
		result.append(transactionType);
		result.append(')');
		return result.toString();
	}

} //AbstractBehaviorModelGraphImpl
