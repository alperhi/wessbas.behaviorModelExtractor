/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dynamod.behavior.impl;

import dynamod.behavior.BehaviorPackage;
import dynamod.behavior.UseCase;
import dynamod.behavior.UseCaseRepository;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Use Case Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dynamod.behavior.impl.UseCaseRepositoryImpl#getUseCases <em>Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UseCaseRepositoryImpl extends EObjectImpl implements UseCaseRepository {
    /**
     * The cached value of the '{@link #getUseCases() <em>Use Cases</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUseCases()
     * @generated
     * @ordered
     */
    protected EList<UseCase> useCases;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected UseCaseRepositoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BehaviorPackage.Literals.USE_CASE_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<UseCase> getUseCases() {
        if (useCases == null) {
            useCases = new EObjectContainmentEList<UseCase>(UseCase.class, this, BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES);
        }
        return useCases;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES:
                return ((InternalEList<?>)getUseCases()).basicRemove(otherEnd, msgs);
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
            case BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES:
                return getUseCases();
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
            case BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES:
                getUseCases().clear();
                getUseCases().addAll((Collection<? extends UseCase>)newValue);
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
            case BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES:
                getUseCases().clear();
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
            case BehaviorPackage.USE_CASE_REPOSITORY__USE_CASES:
                return useCases != null && !useCases.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //UseCaseRepositoryImpl
