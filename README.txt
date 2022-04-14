

CECS-326 Project 1 - Interprocess Communication


-----------------------------------------------
Authors
-----------------------------------------------


* Carlos Verduzco  (018718282)

* Steven Dao  (017503055)


-----------------------------------------------
Usage notes
-----------------------------------------------


* Purpose: To use socket connections between Server and Client classes for communication by reading and writing data using input/output streams and outputting the results to the console of the client.

* Supported by Java-based applications.

* JDK version 9.0+ recommended.

* No installations required.


-----------------------------------------------
Compile / Run Instructions
-----------------------------------------------


------------------------
For Java-supported IDEs:
------------------------


-----------
Testing Q1:
-----------


* Load the source folder containing the `Q1` package into the IDE of your choice.

* Compile and then run `QuoteServer.java` file.

* Compile and then run the `QuoteClient.java` file. You should see the resulting quote of the day from the console.


-----------
Testing Q2:
-----------


* Load the source folder containing the `Q2` package into the IDE of your choice.

* Compile and then run the `EchoServer.java` file.

* Compile and then run the `EchoClient.java` file. You should see two symbols `>>` which is a prompt for user input.

* Enter any String or data you'd like. The program should respond by printing an echoed String of the user input to the client's console. To exit, simply enter an empty String or end-of-input character.

    * For MacOS users, this would be simultaneously pressing (COMMAND + D).
    * For window users, this would be simultaneously pressing (CONTROL + D).


--------------------------------
For Command Prompts / Terminals:
--------------------------------


-----------
Testing Q1:
-----------


* On the command prompt / terminal, change the current directory to the path of the package containing the contents of `Q1`. You can do this with the command `cd <insert path here>`. The path should end with a pattern similar to the following : `...\src\Q1`.

* Compile the `QuoteServer.java` file with the command: `javac QuoteServer.java`.

* Run the `QuoteServer.java` file with the command: `java QuoteServer.java`. The program should appear idly running.

* Open a new command prompt / terminal.

* Change the current directory of the new command prompt / terminal to the path of the package containing the contents of `Q1`. You can do this with the command `cd <insert path here>`. The path should end with a pattern similar to the following : `...\src\Q1`.

* Compile the `QuoteClient.java` file with the command: `javac QuoteClient.java`.

* Run the `QuoteClient.java` file with the command: `java QuoteClient.java`. The program should output a quote of the day.


-----------
Testing Q2:
-----------


* On the command prompt / terminal, change the current directory to the path of the package containing the contents of `Q2`. You can do this with the command `cd <insert path here>`. The path should end with a pattern similar to the following : `...\src\Q2`.

* Compile the `EchoServer.java` file with the command: `javac EchoServer.java`.

* Run the `EchoServer.java` file with the command: `java EchoServer.java`. The program should appear idly running.

* Open a new command prompt / terminal.

* Change the current directory of the new command prompt / terminal to the path of the package containing the contents of `Q2`. You can do this with the command `cd <insert path here>`. The path should end with a pattern similar to the following : `...\src\Q2`.

* Compile the `EchoClient.java` file with the command: `javac EchoClient.java`.

* Run the `EchoClient.java` file with the command: `java EchoClient.java`. You should see two symbols `>>` which is a prompt for user input.

* Enter any String or data you'd like. The program should respond by printing an echoed String of the user input to the client's console. To exit, simply enter an empty String or end-of-input character.

    * For MacOS users, this would be simultaneously pressing (COMMAND + D).
    * For window users, this would be simultaneously pressing (CONTROL + D).

