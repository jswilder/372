all:
	javac -cp gson-2.3.1.jar StarTrekVideoGame/client/UserInterface/*.java StarTrekVideoGame/client/Services/*.java StarTrekVideoGame/shared/*.java StarTrekVideoGame/client/Model/*.java StarTrekVideoGame/server/AdminInterface/*.java StarTrekVideoGame/server/Model/*.java StarTrekVideoGame/server/Response/*.java StarTrekVideoGame/server/Services/*.java StarTrekVideoGame/server/Testing/*.java
runclient:
	java -cp .:gson-2.3.1.jar StarTrekVideoGame.client.UserInterface.ConnectFrame
runserver:
	java -cp .:gson-2.3.1.jar StarTrekVideoGame.server.AdminInterface.MainDriver
runtest:
	java StarTrekVideoGame.server.Testing.Tester
clean:
	rm -f StarTrekVideoGame/client/UserInterface/*.class
	rm -f StarTrekVideoGame/client/Model/*.class
	rm -f StarTrekVideoGame/client/Services/*.class
	rm -f StarTrekVideoGame/shared/*.class
	rm -f StarTrekVideoGame/server/AdminInterface/*.class
	rm -f StarTrekVideoGame/server/Model/*.class
	rm -f StarTrekVideoGame/server/Services/*.class
	rm -f StarTrekVideoGame/server/Request/*.class
	rm -f StarTrekVideoGame/server/Response/*.class

debug:
	jdb StarTrekVideoGame.client.UserInterface.ConnectFrame
