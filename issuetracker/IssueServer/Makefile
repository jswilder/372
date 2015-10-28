all:
	javac -cp gson-2.3.1.jar MainDriver.java Server.java DataBase.java ProjectImpl.java Project.java NanoHTTPD.java Issue.java
run:
	java -cp .:gson-2.3.1.jar MainDriver
test:
	javac -cp \* *.java
runtest:
	java -cp .:\* org.junit.runner.JUnitCore DataBaseTest
clean:
	rm -f *.class
