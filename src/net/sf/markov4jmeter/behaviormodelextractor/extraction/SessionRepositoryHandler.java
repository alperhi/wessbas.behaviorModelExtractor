/***************************************************************************
 * Copyright 2012 by
 *  Christian-Albrechts-University of Kiel, 24098 Kiel, Germany
 *    + Department of Computer Science
 *     + Software Engineering Group
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

package net.sf.markov4jmeter.behaviormodelextractor.extraction;

import java.util.List;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.ObservedUseCaseExecution;
import net.sf.markov4jmeter.behavior.Session;
import net.sf.markov4jmeter.behavior.SessionRepository;
import net.sf.markov4jmeter.behavior.UseCase;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.parser.SessionData;
import net.sf.markov4jmeter.behaviormodelextractor.util.IdGenerator;

import org.eclipse.emf.common.util.EList;

/**
 * This class provides methods for adding sessions and use cases, which build
 * on parsed session data, to corresponding repositories.
 *
 * <p>This class is implemented as singleton pattern; consequently, there is
 * one unique instance which can be requested by invoking method
 * {@link SessionRepositoryHandler#getInstance()}.
 *
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 *
 * @version 1.1
 */
public class SessionRepositoryHandler {


    /* *****************************  constants  **************************** */


    /** Error message for the case that a use case is unknown. */
    private final static String ERROR_UNKNOWN_USE_CASE =
            "use case \"%s\" is not registered in the repository "
            + "(not included in template file?)";


    /* *************************  internal classes  ************************* */


    /**
     * SingletonHolder for singleton pattern; loaded on the first execution of
     * {@link SessionRepositoryHandler#getInstance()}.
     */
    private static class SingletonHolder {

        public static SessionRepositoryHandler instance =
                new SessionRepositoryHandler();
    }


    /* ***************************  constructors  *************************** */


    /**
     * Constructor, makes the standard constructor <code>private</code>.
     */
    private SessionRepositoryHandler () { }


    /* **************************  public methods  ************************** */


    /**
     * Returns a unique instance of this class.
     *
     * @return an instance of {@link SessionRepositoryHandler}.
     */
    public static SessionRepositoryHandler getInstance () {

        return SingletonHolder.instance;
    }

    /**
     * Registers a list of parsed sessions in a given session repository. Each
     * session of the repository consists of a use case sequence, with use cases
     * being registered in a use case repository. If an ID-generator is
     * provided, each associated use case instance will be registered in the
     * given use case repository; in case no ID-generator is provided, an
     * unregistered use case is classified as being unknown, and an exception
     * will be thrown.
     *
     * @param sessions
     *     sessions to be registered in the given repository.
     * @param sessionRepository
     *     repository in which the sessions will be registered.
     * @param useCaseRepository
     *     repository in which any detected use cases will be registered.
     * @param useCaseMapping
     *     mapping of alternative use case names; this might be
     *     <code>null</code>, if use case names shall not be mapped.
     * @param useCaseIdGenerator
     *     instance for generating use case IDs.
     *
     * @throws ExtractionException
     *     if no ID-generator is provided and any unknown use case has been
     *     detected.
     */
    public void addSessionsToSessionsRepository (
            final List<SessionData> sessions,
            final SessionRepository sessionRepository,
            final UseCaseRepository useCaseRepository,
            final UseCaseMapping useCaseMapping,
            final IdGenerator useCaseIdGenerator) throws ExtractionException {

        for (final SessionData session : sessions) {

            this.addSessionToSessionsRepository(
                    session,
                    sessionRepository,
                    useCaseRepository,
                    useCaseMapping,
                    useCaseIdGenerator);
        }
    }

    /**
     * Adds a set of uses cases, indicated by their names, to a given
     * repository; each use case will get an individual ID.
     *
     * @param useCaseRepository
     *     repository to which the use cases shall be added.
     * @param useCaseNames
     *     names of the use cases to be added.
     * @param useCaseIdGenerator
     *     instance for generating the use case IDs.
     */
    public void addSessionUseCasesToUseCaseRepository (
            final UseCaseRepository useCaseRepository,
            final String[] useCaseNames,
            final IdGenerator useCaseIdGenerator) {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;
        final List<UseCase> eUseCases = useCaseRepository.getUseCases();

        for (final String name : useCaseNames) {

            if ( !this.containsUseCaseOfName(eUseCases, name) ) {

                // initialize properties of the EMF use case instance;
                final String id = useCaseIdGenerator.newId();

                // EMF use case to be added to the use case repository;
                final UseCase eUseCase = factory.createUseCase();

                eUseCase.setId(id);
                eUseCase.setName(name);

                eUseCases.add(eUseCase);
            }
        }
    }


    /* **************************  private methods  ************************* */


    /**
     * Checks, whether a use case of certain name is contained in a given list.
     *
     * @param useCases
     *     list of use cases to be searched through.
     * @param name
     *     name of the use case to be searched.
     *
     * @return
     *     <code>true</code> if and only if a use case of the specified name is
     *     contained in the given list.
     */
    private boolean containsUseCaseOfName (
            final List<UseCase> useCases,
            final String name) {

        for (final UseCase useCase : useCases) {

            if ( useCase.getName().equals(name) ) {

                return true;
            }
        }

        return false;
    }

    /**
     * Registers a parsed session in a given session repository. Each session of
     * the repository consists of a use case sequence, with use cases being
     * registered in a use case repository. If an ID-generator is provided,
     * each associated use case instance will be registered in the given use
     * case repository; in case no ID-generator is provided, an unregistered
     * use case is classified as being unknown, and an exception will be thrown.
     *
     * @param session
     *     session to be registered in the given repository.
     * @param sessionRepository
     *     repository in which the session will be registered.
     * @param useCaseRepository
     *     repository in which any detected use cases will be registered.
     * @param useCaseMapping
     *     mapping of alternative use case names; this might be
     *     <code>null</code>, if use case names shall not be mapped.
     * @param useCaseIdGenerator
     *     instance for generating use case IDs.
     *
     * @throws ExtractionException
     *     if no ID-generator is provided and any unknown use case has been
     *     detected.
     */
    private void addSessionToSessionsRepository (
            final SessionData session,
            final SessionRepository sessionRepository,
            final UseCaseRepository useCaseRepository,
            final UseCaseMapping useCaseMapping,
            final IdGenerator useCaseIdGenerator) throws ExtractionException {

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        // session to be stored in the repository;
        final Session eSession = factory.createSession();

        final List<net.sf.markov4jmeter.behaviormodelextractor.extraction.parser.UseCase> useCases =
                session.getUseCases();

        final long[] timeBounds = this.addUseCasesToSession(
                useCases,
                eSession,
                useCaseRepository,
                useCaseMapping,
                useCaseIdGenerator);

        eSession.setId( session.getId() );
        eSession.setStartTime( timeBounds[0] );
        eSession.setEndTime( timeBounds[1] );

        sessionRepository.getSessions().add(eSession);
    }


    /**
     * Adds a list of parsed use cases to a given session. If an ID-generator is
     * provided, the associated use case instances will be registered in the
     * given use case repository; in case no ID-generator is provided, an
     * unregistered use case is classified as being unknown, and an exception
     * will be thrown.
     *
     * <P>Furthermore, this method calculates the start time and ending time of
     * the given session.
     *
     * @param useCases
     *     use cases to be added to the given session and to be registered in
     *     the given use case repository.
     * @param eSession
     *     session to which the use cases will be added.
     * @param useCaseRepository
     *     repository in which the use cases will be registered.
     * @param useCaseMapping
     *     mapping of alternative use case names; this might be
     *     <code>null</code>, if use case names shall not be mapped.
     * @param useCaseIdGenerator
     *     instance for generating use case IDs. If <code>null</code> is passed,
     *     the detected use cases will not be registered in the use case
     *     repository; instead, that repository will only include use cases
     *     which have been registered before, e.g., when being extracted from a
     *     template.
     *
     * @return
     *     the start time and ending time of the given session.
     *
     * @throws ExtractionException
     *     if no ID-generator is provided and any unknown use case has been
     *     detected.
     */
    private long[] addUseCasesToSession (
            final List<net.sf.markov4jmeter.behaviormodelextractor.extraction.parser.UseCase> useCases,
            final Session eSession,
            final UseCaseRepository useCaseRepository,
            final UseCaseMapping useCaseMapping,
            final IdGenerator useCaseIdGenerator) throws ExtractionException {

        long sessionStartTime = Long.MAX_VALUE;
        long sessionEndTime   = Long.MIN_VALUE;

        final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

        for (net.sf.markov4jmeter.behaviormodelextractor.extraction.parser.UseCase useCase : useCases) {

            // retrieve observation properties from use case data;
            String name          = useCase.getName();
            final long startTime = useCase.getStartTime();
            final long endTime   = useCase.getEndTime();

            // lookup for a possible use case name redefinition;
            if (useCaseMapping != null) {

                name = useCaseMapping.lookup(name);
            }

            UseCase eUseCase = this.findUseCaseByNameInRepository(
                    name,
                    useCaseRepository);

            if (eUseCase == null) {  // use case not registered yet?

                if (useCaseIdGenerator != null) {

                    // create a use case to be stored in the repository;
                    eUseCase = factory.createUseCase();
                    eUseCase.setId( useCaseIdGenerator.newId() );
                    eUseCase.setName(name);

                    useCaseRepository.getUseCases().add(eUseCase);

                } else {

                    final String message = String.format(
                            SessionRepositoryHandler.ERROR_UNKNOWN_USE_CASE,
                            name);

                    throw new ExtractionException(message);
                    // continue;
                }
            }

            // eUseCase has been newly created or found in repository;

            final ObservedUseCaseExecution observedUseCaseExecution =
                    factory.createObservedUseCaseExecution();

            observedUseCaseExecution.setStartTime(startTime);
            observedUseCaseExecution.setEndTime(endTime);
            observedUseCaseExecution.setUseCase(eUseCase);

            eSession.getObservedUseCaseExecutions().add(
                    observedUseCaseExecution);

            if (startTime < sessionStartTime) {

                sessionStartTime = startTime;
            }

            if (endTime > sessionEndTime) {

                sessionEndTime = endTime;
            }
        }

        return new long[] { sessionStartTime, sessionEndTime };
    }

    /**
     * Searches for a use case with a certain name in a given repository.
     *
     * @param useCaseName
     *     the name of the use case to be searched for.
     * @param useCaseRepository
     *     the use case repository to be searched through.
     *
     * @return
     *     a use case of the given name, or <code>null</code> if such use case
     *     does not exist in the given repository.
     */
    private UseCase findUseCaseByNameInRepository(
            final String useCaseName,
            final UseCaseRepository useCaseRepository) {

        final EList<UseCase> useCases = useCaseRepository.getUseCases();

        for (final UseCase useCase : useCases) {

            if ( useCaseName.equals(useCase.getName()) ) {

                return useCase;
            }
        }

        return null;  // no matching use case found;
    }
}
