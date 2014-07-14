package net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.helper;

import java.util.HashMap;
import java.util.Set;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behaviormodelextractor.util.IdGenerator;

public class InitialUseCaseHandler {


    public final static String GENERIC_INITIAL_USE_CASE_NAME = "_init";


    /* *************************  global variables  ************************* */


    /** Mapping between initial use cases and their numbers of observations. */
    private final HashMap<UseCase, Integer> initialUseCaseObservations =
            new HashMap<UseCase, Integer>();

    private final IdGenerator idGenerator;


    /* ***************************  constructors  *************************** */


    public InitialUseCaseHandler (final IdGenerator idGenerator) {

        this.idGenerator = idGenerator;
    }


    /* **************************  public methods  ************************** */


    /**
     * Registers a use case as initial state; if the use case has been already
     * registered as initial state, its assigned occurrence number will be
     * increased by 1.
     *
     * @param useCase  use case to be registered.
     */
    public void registerInitialState (final UseCase useCase) {

        if (useCase != null) {

            if ( this.initialUseCaseObservations.containsKey(useCase) ) {

                // key exists  ->  increase its assigned counter value;
                final int count = this.initialUseCaseObservations.get(useCase);

                this.initialUseCaseObservations.put(useCase, count + 1);

            } else {

                // key does not exist  ->  assign initial counter value;
                this.initialUseCaseObservations.put(useCase, 1);
            }
        }
    }

    /**
     *
     * @return
     */
    public UseCase getUniqueInitialUseCase () {

        final UseCase uniqueInitialUseCase;  // to be returned;

        switch ( this.initialUseCaseObservations.size() ) {

            case 0:

                uniqueInitialUseCase = this.createGenericInitialUseCase();
                break;

            case 1:

                uniqueInitialUseCase =
                        this.getRegisteredInitialUseCases().iterator().next();
                break;

            default:  // number of use cases > 1;

                uniqueInitialUseCase = this.createGenericInitialUseCase();
        }

        return uniqueInitialUseCase;
    }


    /**
     * Returns the set of initial use cases.
     *
     * @return  the use cases which have been registered as initial use cases.
     */
    public Set<UseCase> getRegisteredInitialUseCases () {

        return this.initialUseCaseObservations.keySet();
    }


    public int getInitialUseCaseOccurrences (final UseCase useCase) {

        return this.initialUseCaseObservations.get(useCase);
    }


    /* **************************  private methods  ************************* */


    /**
     *
     * @return
     */
    private UseCase createGenericInitialUseCase () {

        final UseCase useCase;  // to be returned;
        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        useCase = factory.createUseCase();
        useCase.setId( this.idGenerator.newId() );
        useCase.setName(InitialUseCaseHandler.GENERIC_INITIAL_USE_CASE_NAME);

        return useCase;
    }
}