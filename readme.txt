(TODO:)
  
  - implementation of class RBMToRBMUnifier;

  - DOT-graph generation code needs to be tested/revised;
  



INTRODUCTION
------------

<TODO: description, license issues, transformation>

License:

  MIT-license (class "dynamod.behavior.extractor.util.Util").

Transformation:

  sessions.dat -->  ABMS  -->  RBMS  -->  RBM ("merged")  -->  Markov matrix


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

  - added internal IDs for use cases, removed AIDA-specific ID handling;


USAGE
-----

Example for command-line parameters (one row):

  -i "./examples/aida/sessions.dat" -t "./examples/aida/template.csv"
    -m "./examples/aida/mapping.csv"  -l 2 -o "./output/behaviormodel.csv"
      -g "./output/gen_graph"


OPEN ISSUES / FUTURE WORK
-------------------------

  - caching solution for too many Markov states
    (thousands of states, e.g., 11200 states lead to a heap-out-of-memory error,
    since the 2-dimensional matrix-arrays of the MarkovMatrixHandler class
    exceed the amount of available memory);
    
  - initial states have to be set manually in the resulting matrices;