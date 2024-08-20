### Overview
This project is a JavaFX application that functions as a dictionary, allowing users to add, view, update, and delete word definitions. 
The application provides a simple and intuitive graphical user interface (GUI) for managing a collection of words and their meanings.

### Project Structure
* Main.java: The entry point of the application. It initializes the JavaFX application and loads the main user interface for the dictionary.
* MainController.java: Manages the main dictionary interface, including the display of words and their definitions. It handles user interactions such as searching for a word, selecting a word to view its definition, and navigating to other parts of the application.
* AddController.java: Handles the logic for adding new words and definitions to the dictionary. It provides a user interface for entering a word and its corresponding definition.
* UpdateController.java: Manages the updating of existing word definitions. Users can select a word and modify its definition using the interface controlled by this class.
* OperationException.java: A custom exception class used to handle specific errors related to dictionary operations, such as attempting to add a duplicate word or delete a non-existent word.
* run.bat: A batch script to compile and run the JavaFX dictionary application with a single command.

### How to Run the Program
* Ensure that you have the Java Development Kit (JDK 8 or higher) and JavaFX SDK installed on your system.
* Open the command line and navigate to the directory containing the program files.
* Run the program using the provided run.bat script, or by typing the following command:`java Main`.
