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