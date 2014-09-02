This project contains the source code for the Behavior Model Extractor, which
extracts user behavior information (Behavior Mix + Behavior Models) from a given
set of user session traces.

The implementation builds on the Behavior Model Extraction tool which has been
developed in context of the DynaMod project (http://kosse-sh.de/dynamod/).

Transformation from session data to Behavior Models is done is follows:

  sessions.dat -->  ABMGs  -->  RBMGs  -->  RBMG ("merged")  -->  Markov matrix

whereas ABMGs and RBMGs denote Absolute and Relative Behavior Model Graphs
respectively, and Markov matrices represent Behavior Models as used in JMeter
Test Plans. A "merged" RBMG denotes a Relative Behavior Model Graph, that is
a Behavior Model, which results from a simple merge operation for multiple
RBMGs.


ENHANCEMENTS
------------

The following fixes/features have been made or added to the DynaMod version of
this tool:

  - added command-line options instead of using constant values (see class
    CommandLineArgumentsHandler.java for available options)

  - added option for providing a template file as input (if not specified,
    extracted use cases will be used only);

  - optional (generalized) mapping for use cases (for each use case, a lookup
    will be made in the mapping during transformation);

  - completely restructured code (more extensibility/flexibility with respect
    to alternative think time types);

  - comprehensively commented source code;

  - line-break parameter for output files;

  - added internal IDs for use cases, removed AIDA-specific ID handling;

  - added output: list of use cases, Behavior Mix information.


LICENSE NOTES
-------------

It is important to note that the tool uses an external class with MIT-license
(class "dynamod.behavior.extractor.util.Util").


TOOLING
-------

The project has been developed with the use of the following tools:

  - Eclipse Kepler
  - JDK 1.7

An Eclipse environment for this project should be configured accordingly.


USAGE
-----

The Behavior Model Extractor is a stand-alone tool which is executable from
command-line. The file "runconfiguration_arguments.txt" contains some examples
of parameter sequences that might be passed to the tool.


OPEN ISSUES / FUTURE WORK
-------------------------

The following open issues denote future work:

  - caching solution for too many Markov states (thousands of states, such as
    about 11200 in a specific test run, lead to a heap-out-of-memory error,
    since the 2-dimensional matrix-arrays of the MarkovMatrixHandler class
    exceed the amount of available memory);

  - initial states have to be set manually in the resulting matrices;
  
  - initial states appear in certain cases twice (with and without asterisk);

  - DOT-graph generation code needs to be tested/revised;

  - implementation of class RBMToRBMUnifier with clustering methods.