# EverestEngineeringCodingChallenge

#Tame of Thrones

It's a brief specification about project dependencies, how to run and coding technologies used.

#Dependencies – 
1-	Spring Boot project – created from Spring Initializer (web link - https://start.spring.io )
No other dependencies (web, Security, JPA etc) required.

2-	Under the <build> and <plugins> tag of POM file, I have used “repackage” to build the executable jar file.

3-	To run the jar file – open command prompt/terminal then navigate to the directory where jar file is placed and run the jar file by using the below command (assuming java is installed, and class path is updated accordingly.)
 java -jar TameOfThrones-0.0.1-SNAPSHOT.jar

4-	For a sample try you can use the CodingChallenge.pdf commands, which came with the test.

#Coding Technologies – 

1-	Java is used as the programming language to write the complete logic.
2-	Clean Code practices are followed to keep it neat and readable.
(Still, refactoring is possible)
3-	Junit test cases are written for all basic code logic testing.
NOTE – Corner/complex logics are not updated in Junit Test classes (still hope for betterment).
4-	Extract the TameOfThrones-SourceCode.zip file & import as a maven project in eclipse IDE to verify the code.
