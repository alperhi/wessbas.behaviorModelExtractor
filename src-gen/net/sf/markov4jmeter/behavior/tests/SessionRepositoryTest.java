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
