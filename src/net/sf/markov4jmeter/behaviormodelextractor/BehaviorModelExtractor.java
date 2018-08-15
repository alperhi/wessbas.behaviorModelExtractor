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

package net.sf.markov4jmeter.behaviormodelextractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.markov4jmeter.behavior.BehaviorFactory;
import net.sf.markov4jmeter.behavior.BehaviorMix;
import net.sf.markov4jmeter.behavior.BehaviorMixEntry;
import net.sf.markov4jmeter.behavior.BehaviorModelAbsolute;
import net.sf.markov4jmeter.behavior.SessionRepository;
import net.sf.markov4jmeter.behavior.UseCaseRepository;
import net.sf.markov4jmeter.behavior.impl.BehaviorPackageImpl;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.BehaviorModelWriter;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.DotGraphGenerator;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.ExtractionException;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.MarkovMatrixHandler;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.SessionRepositoryHandler;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.UseCaseMapping;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.RBMToMarkovMatrixTransformer;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.RBMToRBMUnifier;
import net.sf.markov4jmeter.behaviormodelextractor.extraction.transformation.SessionToABMTransformer;
import net.sf.markov4jmeter.behaviormodelextractor.util.CSVHandler;
import net.sf.markov4jmeter.behaviormodelextractor.util.IdGenerator;
import wessbas.commons.parser.ParseException;
import wessbas.commons.parser.Parser;
import wessbas.commons.parser.SessionData;

/**
 * This is the main class of the Behavior Model Extractor.
 * 
 * @author Eike Schulz (esc@informatik.uni-kiel.de)
 * 
 * @version 1.1
 */
public class BehaviorModelExtractor {

	/* ***************************** constants **************************** */

	/** Name of the final state, "<code>$</code>" in general. */
	private final static String FINAL_VERTEX_NAME = "$";

	private final static String THINK_TIME_DEFAULT_VALUE = "n(0 0)";

	private final static String ERROR_NOT_INITIALIZED = "Behavior Model Extractor has not been initialized.";

	/* ************************* global variables ************************* */

	/** Instance for creating and modifying Markov matrices. */
	private MarkovMatrixHandler markovMatrixHandler;

	/** Instance for generating DOT graphs from Markov matrices. */
	private DotGraphGenerator dotGraphGenerator;

	/** Repository which stores all session information. */
	private SessionRepository sessionRepository;

	/** Repository which stores all use case information. */
	private UseCaseRepository useCaseRepository;

	/**
	 * Mapping of alternative use case names; this might be <code>null</code>, if
	 * use case names shall not be mapped.
	 */
	private UseCaseMapping useCaseMapping;

	/** Instance for generating use case IDs. */
	private IdGenerator useCaseIdGenerator;

	private boolean isInitialized;

	/* *************************** constructors *************************** */

	/**
	 * Constructor for a Behavior Model Extractor.
	 */
	public BehaviorModelExtractor() {

		this.isInitialized = false;
	}

	/* ************************** public methods ************************** */

	/**
	 * Initializes the Behavior Model Extractor.
	 * 
	 * @param useCaseMappingFile
	 *            mapping of alternative use case names; this might be
	 *            <code>null</code>, if use case names shall not be mapped.
	 * @param templateFile
	 *            template file to be filled with extracted information.
	 * @param lineBreakType
	 *            OS-specific line-break type for CSV output files (0 = Windows, 1 =
	 *            Unix, 2 = MacOS); the default value is 0 (Windows).
	 * 
	 * @throws FileNotFoundException
	 *             in case any specified file does not exist.
	 * @throws IOException
	 *             if any reading error occurs.
	 * @throws ExtractionException
	 * @throws NullPointerException
	 */
	public void init(final String useCaseMappingFile, final String templateFile, final int lineBreakType)
			throws FileNotFoundException, IOException, NullPointerException, ExtractionException {

		// ---- initialize Markov Matrix Handler and DOT Graph Generator ----;

		this.markovMatrixHandler = new MarkovMatrixHandler(BehaviorModelExtractor.FINAL_VERTEX_NAME,
				new CSVHandler(lineBreakType), BehaviorModelExtractor.THINK_TIME_DEFAULT_VALUE);

		// TODO: dedicated line-break type for DOT Graph Generator as parameter?
		this.dotGraphGenerator = new DotGraphGenerator(lineBreakType);

		// ---- initialize repositories for sessions and use cases ----;

		BehaviorPackageImpl.init(); // initialize the EMF package;

		final BehaviorFactory factory = BehaviorFactory.eINSTANCE;

		this.sessionRepository = factory.createSessionRepository();
		this.useCaseRepository = factory.createUseCaseRepository();

		// if no template file is specified, use cases (from session traces!)
		// will be added during the extraction process to the repository;
		// -> ID-generator required;
		this.useCaseIdGenerator = new IdGenerator("UC");

		if (templateFile != null) {

			// if a template file is specified, use cases (from template!) will
			// be added to the repository initially;
			//
			// collectStatesFromFromCSVFile() might throw a FileNotFound-,
			// NullPointer-, IO- or ExtractionException;
			SessionRepositoryHandler.getInstance().addSessionUseCasesToUseCaseRepository(this.useCaseRepository,
					this.markovMatrixHandler.collectStatesFromCSVFile(templateFile), this.useCaseIdGenerator);

			// no more use cases will be added -> ID-generator not required
			// anymore (this null-value serves as indicator for some methods!);
			this.useCaseIdGenerator = null;
		}

		// ---- initialize use case mapping ----;

		// use case mapping will be left empty, if no file is defined;
		this.useCaseMapping = new UseCaseMapping();

		if (useCaseMappingFile != null) {

			// might throw a FileNotFound-, IO- or NullPointerException;
			// (NullPointerException should never be thrown here);
			this.useCaseMapping.initMap(useCaseMappingFile);
		}

		this.isInitialized = true;
	}

	/**
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param outputDotFile
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws ExtractionException
	 */
	public void extract(final String inputFile, final String outputDirectory, final String clusteringMethod)
			throws IOException, ParseException, ExtractionException {

		if (!this.isInitialized) {

			throw new ExtractionException(BehaviorModelExtractor.ERROR_NOT_INITIALIZED);
		}

		final ArrayList<SessionData> sessions = Parser.parseSessionsIntoSessionsRepository(inputFile, outputDirectory);

		SessionRepositoryHandler.getInstance().addSessionsToSessionsRepository(sessions, this.sessionRepository,
				this.useCaseRepository, this.useCaseMapping, this.useCaseIdGenerator);

		// ---- initialize transformers ----;

		final SessionToABMTransformer sessionToAbmTransformer = new SessionToABMTransformer();

		final RBMToRBMUnifier rbmToRBMUnifier = new RBMToRBMUnifier();

		final RBMToMarkovMatrixTransformer rbmToMarkovMatrixTransformer = new RBMToMarkovMatrixTransformer(
				this.markovMatrixHandler);

		// ---- start transformation process ----;

		final BehaviorModelAbsolute[] behaviorModelsAbsolute = sessionToAbmTransformer
				.transformSessionsToBehaviorModels(this.sessionRepository.getSessions(),
						this.useCaseRepository.getUseCases(), this.useCaseIdGenerator,
						clusteringMethod.equals(RBMToRBMUnifier.CLUSTERING_TYPE_NONE));

		final BehaviorMix behaviorMix = rbmToRBMUnifier.transform(behaviorModelsAbsolute, clusteringMethod,
				this.useCaseRepository, outputDirectory);

		// check if sumProbability is one
		double sumProbability = 0;
		for (BehaviorMixEntry behaviorMixEntry : behaviorMix.getEntries()) {
			sumProbability += behaviorMixEntry.getRelativeFrequency();
		}

		if (sumProbability != 1) {
			double diff = 1 - sumProbability;
			for (BehaviorMixEntry behaviorMixEntry : behaviorMix.getEntries()) {
				if ((behaviorMixEntry.getRelativeFrequency() + diff) > 0) {
					behaviorMixEntry.setRelativeFrequency(behaviorMixEntry.getRelativeFrequency() + diff);
					break;
				}
			}
		}

		// (RBM-to-matrix transformation is nested into file writer below);

		// ---- write output files ----;

		final BehaviorModelWriter behaviorModelWriter = new BehaviorModelWriter(rbmToMarkovMatrixTransformer, // last
																												// transformation;
				this.dotGraphGenerator);

		// write the sessions-related CVS- and graph-files;
		behaviorModelWriter.writeOutputFiles(behaviorMix, outputDirectory);
	}

	/**
	 * Main method of the application.
	 * 
	 * @param argv
	 *            Command-line arguments vector.
	 */
	public static void main(final String[] argv) {

		// try {
		//
		// System.out.println("****************************");
		// System.out.println("Start BehaviorModelExtractor");
		// System.out.println("****************************");
		//
		// // ---- read command-line parameters ----;
		//
		// // initialize arguments handler for retrieving the command line
		// // options; might throw a NullPointer-, IllegalArgument- or
		// // ParseException;
		// CommandLineArgumentsHandler.init(argv);
		//
		// final String inputFile = CommandLineArgumentsHandler.getInputFile();
		//
		// final String outputDirectory =
		// CommandLineArgumentsHandler.getOutputDirectory();
		//
		// // template file is optional;
		// final String templateFile = CommandLineArgumentsHandler.getTemplateFile();
		//
		// // use case mapping file is optional;
		// final String useCaseMappingFile =
		// CommandLineArgumentsHandler.getUseCaseMappingFile();
		//
		// // line-break type of CSV-files is optional;
		// final int lineBreakType = CommandLineArgumentsHandler.getLineBreakType();
		//
		// // clustering method is optional;
		// final String clusteringMethod =
		// CommandLineArgumentsHandler.getClusteringMethod();
		//
		// // ---- initialize and start the Behavior Model Extractor ----
		//
		// final BehaviorModelExtractor behaviorModelExtractor = new
		// BehaviorModelExtractor();
		//
		// // might throw a FileNotFound- or IOException;
		// behaviorModelExtractor.init(useCaseMappingFile, templateFile, lineBreakType);
		//
		// // might throw an IO-, Parse- or ExtractionException;
		// behaviorModelExtractor.extract(inputFile, outputDirectory, clusteringMethod);
		//
		// System.out.println("****************************");
		// System.out.println("END BehaviorModelExtractor");
		// System.out.println("****************************");
		//
		// // } catch (final MissingOptionException ex) {
		// //
		// // // this exception type indicates that no argument has been passed;
		// // CommandLineArgumentsHandler.printUsage();
		//
		// } catch (final Exception ex) {
		//
		// System.err.println(ex.getMessage());
		// CommandLineArgumentsHandler.printUsage();
		// }
		// BehaviorModelExtractor extractor = new BehaviorModelExtractor();
		// extractor.createBehaviorModel();
	}

	public void createBehaviorModel(String inputFile, String outputDirectory) {
		try {

			System.out.println("****************************");
			System.out.println("Start BehaviorModelExtractor");
			System.out.println("****************************");

			// -min 3 -max 5 -seed 5

			// final String inputFile = "wessbas/sessions.dat";
			//
			// final String outputDirectory = "wessbas/behaviormodelextractor";

			// template file is optional;
			final String templateFile = null;

			// use case mapping file is optional;
			final String useCaseMappingFile = null;

			// line-break type of CSV-files is optional;
			final int lineBreakType = 0;

			// clustering method is optional;
			final String clusteringMethod = "xmeans";

			// ---- initialize and start the Behavior Model Extractor ----

			final BehaviorModelExtractor behaviorModelExtractor = new BehaviorModelExtractor();

			// might throw a FileNotFound- or IOException;
			behaviorModelExtractor.init(useCaseMappingFile, templateFile, lineBreakType);

			// might throw an IO-, Parse- or ExtractionException;
			behaviorModelExtractor.extract(inputFile, outputDirectory, clusteringMethod);

			System.out.println("****************************");
			System.out.println("END BehaviorModelExtractor");
			System.out.println("****************************");

		} catch (final Exception ex) {

		}
	}

	public void createBehaviorModel() {
		final String inputFile = "wessbas/sessions.dat";
		final String outputDirectory = "wessbas/behaviormodelextractor";
		createBehaviorModel(inputFile, outputDirectory);
	}
}
