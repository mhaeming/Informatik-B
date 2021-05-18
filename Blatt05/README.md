# How to create a `jar` file


First compile the source files with `javac`

`jar cfe Bruchrechner.jar bruchrechner.Calculator .\bruchrechner\*.class`

cf for creating a jar archive.

e defines an entry point. This is the class to be run when the .jar is executed.

To run the jar file use `java -jar Bruchrechner.jar` and add the arguments you want to pass to it.

