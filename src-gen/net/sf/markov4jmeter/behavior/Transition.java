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


package net.sf.markov4jmeter.behavior;

import java.math.BigDecimal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link net.sf.markov4jmeter.behavior.Transition#getTargetVertex <em>Target Vertex</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Transition#getValue <em>Value</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Transition#getTimeDiffs <em>Time Diffs</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Transition#getThinkTimeParams <em>Think Time Params</em>}</li>
 *   <li>{@link net.sf.markov4jmeter.behavior.Transition#getSourceVertex <em>Source Vertex</em>}</li>
 * </ul>
 *
 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends EObject {
    /**
	 * Returns the value of the '<em><b>Target Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Vertex</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Vertex</em>' reference.
	 * @see #setTargetVertex(Vertex)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition_TargetVertex()
	 * @model required="true"
	 * @generated
	 */
    Vertex getTargetVertex();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Transition#getTargetVertex <em>Target Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Vertex</em>' reference.
	 * @see #getTargetVertex()
	 * @generated
	 */
    void setTargetVertex(Vertex value);

    /**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition_Value()
	 * @model
	 * @generated
	 */
    double getValue();

    /**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Transition#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
    void setValue(double value);

    /**
	 * Returns the value of the '<em><b>Time Diffs</b></em>' attribute list.
	 * The list contents are of type {@link java.math.BigDecimal}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Time Diffs</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Diffs</em>' attribute list.
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition_TimeDiffs()
	 * @model
	 * @generated
	 */
    EList<BigDecimal> getTimeDiffs();

    /**
	 * Returns the value of the '<em><b>Think Time Params</b></em>' attribute list.
	 * The list contents are of type {@link java.math.BigDecimal}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Think Time Params</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Think Time Params</em>' attribute list.
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition_ThinkTimeParams()
	 * @model
	 * @generated
	 */
    EList<BigDecimal> getThinkTimeParams();

				/**
	 * Returns the value of the '<em><b>Source Vertex</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Vertex</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Vertex</em>' reference.
	 * @see #setSourceVertex(Vertex)
	 * @see net.sf.markov4jmeter.behavior.BehaviorPackage#getTransition_SourceVertex()
	 * @model required="true"
	 * @generated
	 */
	Vertex getSourceVertex();

				/**
	 * Sets the value of the '{@link net.sf.markov4jmeter.behavior.Transition#getSourceVertex <em>Source Vertex</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Vertex</em>' reference.
	 * @see #getSourceVertex()
	 * @generated
	 */
	void setSourceVertex(Vertex value);

} // Transition
