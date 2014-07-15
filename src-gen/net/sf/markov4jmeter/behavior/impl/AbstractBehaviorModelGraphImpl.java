/**
 */
package net.sf.markov4jmeter.behavior.impl;

import java.util.Collection;

import net.sf.markov4jmeter.behavior.AbstractBehaviorModelGraph;
import net.sf.markov4jmeter.behavior.BehaviorPackage;
import net.sf.markov4jmeter.behavior.Vertex;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Behavior Model Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.impl.AbstractBehaviorModelGraphImpl#getVertices <em>Vertices</em>}</li>
 * </ul>
 * </p>
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
        }
        return super.eIsSet(featureID);
    }

} //AbstractBehaviorModelGraphImpl
