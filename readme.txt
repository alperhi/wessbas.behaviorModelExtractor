
INTRODUCTION
------------

This package reads wessbas logs and generates behavior mix and behavior models based on clustering 
algorithms. 

Input: Logfiles in form of the wessbas logfile format. The wessbas.ui package can be used to 
transform logfiles (HTTP web logs) into the wessbas logfile format. 

Output: The output is the behavior mix and the behavior models.  

License:

  MIT-license?

Transformation:

  sessions.dat -->  Absolut Behavior Models  -->  Clustering --> Relative Behavior Model
  --> Markov matrix as cvs files.

SYSTEM REQUIREMENTS
-------------------

The project has been developed with the use of the following tools:

  - JDK 1.7
  - Xtext 2.5.4
  - Eclipse Modeling Tools
  
ACADEMIC LITERATURE
------------------  
  
Automatic extraction of probabilistic workload specifications for 
load testing session-based application systems  (Hoorn, Vögele, Schulz, Hasselbring, Krcmar)  

ENHANCEMENTS
------------

  - added command-line options (see class CommandLineArgumentsHandler.java for
    options)

  - optional template file
    (if not specified, extracted use cases will be used only);

  - optional (generalized) mapping for use cases
    (for each use case, a lookup will be made in the mapping during transformation);

  - completely restructured code
    (more extensible/flexible with respect to alternative think time types);

  - comprehensively commented source code;

  - line-break parameter for output files;


USAGE
-----

Example for command-line parameters (one row):

-i examples\specj\input\sessions.dat -o examples\specj\output  -c xmeans -min 3 -max 3 -seed 5


OPEN ISSUES / FUTURE WORK
-------------------------

  - caching solution for too many Markov states
    (thousands of states, e.g., 11200 states lead to a heap-out-of-memory error,
    since the 2-dimensional matrix-arrays of the MarkovMatrixHandler class
    exceed the amount of available memory);  
  
TODO:
------------
  
  - DOT-graph generation code needs to be tested/revised;
  