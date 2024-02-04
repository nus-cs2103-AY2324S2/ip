package Duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    private TextField inputField;
    private TextArea outputArea;

    public Duke() {

    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            storage.ensureDataFileExists();
            tasks = storage.loadTasksFromFile();
        } catch (DukeDataCorruptedException e) {
            ui.showDukeDataCorruptionMessage(e);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
            ui.showFileNotFoundExceptionMessage();
        } catch (IOException e) {
            ui.showIoExceptionMessage();
        } catch (NumberFormatException e) {
            ui.showNumberFormatExceptionMessage();
        }

        // Set up UI components
        inputField = new TextField();
        outputArea = new TextArea();
        outputArea.setEditable(false);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> handleUserInput());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(outputArea, inputField, sendButton);

        // Set up the scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duck Chatbot");
        primaryStage.show();

        // Show welcome message
        ui.showWelcomeMessage();
        appendToOutputArea("Welcome to Duck Chatbot!\n");
    }

    private void handleUserInput() {
        String userInput = inputField.getText();
        appendToOutputArea("> " + userInput + "\n");

        if (Parser.isExitCommand(userInput)) {
            ui.showGoodbyeMessage();
            appendToOutputArea("Goodbye! Hope to see you again :)");
            // Save tasks and exit
            try {
                storage.saveTasksToFile(tasks);
            } catch (IOException e) {
                ui.showIoExceptionMessage();
            }
            System.exit(0);
        }

        if (userInput.trim().isEmpty()) {
            appendToOutputArea("Please enter an action and a task\n");
            return;
        }

        try {
            Command command = Parser.parseCommand(userInput);
            command.execute(tasks, new Ui(), storage);
            storage.saveTasksToFile(tasks);
        } catch (DukeException | IOException e) {
            appendToOutputArea(e.getMessage() + "\n");
        }

        // Clear input field after processing
        inputField.clear();
    }

    private void appendToOutputArea(String text) {
        outputArea.appendText(text);
    }
}


/**
 * Exception class representing a corrupted data file in Duke.
 */
class DukeDataCorruptedException extends Exception {

    /**
     * Constructs a DukeDataCorruptedException with the specified error message.
     *
     * @param message The error message describing the data corruption issue.
     */
    public DukeDataCorruptedException(String message) {
        super(message);
    }
}

/**
 * Exception class representing a generic exception in Duke.
 */
class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}


