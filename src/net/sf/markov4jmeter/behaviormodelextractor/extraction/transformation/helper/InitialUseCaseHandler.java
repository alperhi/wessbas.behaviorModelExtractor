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


package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.helper;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behaviormodelextractor.util.IdGenerator;

/**
 * Helper class for handling initial use cases; this handler provides a generic
 * use case for unifying multiple use cases.
 * 
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 * @version 1.0
 */
public class InitialUseCaseHandler {

	/* ***************************** constants **************************** */

	/** Name of the generic use case. */
	public final static String GENERIC_INITIAL_USE_CASE_NAME = "INITIAL";

	/* ************************* global variables ************************* */

	/** Instance for generating use case IDs. */
	private final IdGenerator idGenerator;

	/* *************************** constructors *************************** */

	/**
	 * Constructor for a Use Case Handler.
	 * 
	 * @param idGenerator
	 *            instance for generating use case IDs.
	 */
	public InitialUseCaseHandler(final IdGenerator idGenerator) {

		this.idGenerator = idGenerator;
	}

	/* ************************** public methods ************************** */

	/**
	 * Creates a generic use case.
	 * 
	 * @return the newly created use case.
	 */
	public UseCase getUniqueInitialUseCase() {
		final UseCase useCase; // to be returned;
		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		useCase = factory.createUseCase();
		useCase.setId(this.idGenerator.newId());
		useCase.setName(InitialUseCaseHandler.GENERIC_INITIAL_USE_CASE_NAME);

		return useCase;
	}
}
