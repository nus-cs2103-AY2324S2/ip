package UiRelated;

import Commands.Command;
import TaskLists.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controller class for the MainWindow.fxml file.
 * This class manages the UI interactions and controls for the main window of the application.
 * It includes functionality for handling user input, displaying dialogs,
 * and saving/loading task lists.
 */
public class MainWindow extends AnchorPane {
    private Storage storage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Ui ui;

    private TaskList tasks = new TaskList();
    private final String logPath = "./src/list_log";

    /**
     * Initializes the MainWindow controller.
     * It loads the task list from storage (if available)
     * and displays a message indicating the status.
     * If the previous task list is not found, it initializes an empty task list.
     * It also sets up a listener for the dialog container
     * to scroll to the bottom when new content is added.
     */
    public void initialize() {
        ui = new Ui();
        storage = new Storage(logPath);
        try {
            tasks = new TaskList(storage.load());
            dialogContainer.getChildren().add(
                    HAssntDialogBox.getBotDialog(ui.showMessages("Successfully loaded last session list")));
        } catch (FileNotFoundException e) {
            dialogContainer.getChildren().add(
                    HAssntDialogBox.getBotDialog(ui.showMessages("Last session list not found. "
                                                                     + "The list now is empty")));
            tasks = new TaskList();
        }
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input when the sendButton is clicked or when Enter is pressed.
     * It parses the user input, executes the corresponding command, and displays the response in the dialog container.
     * If an exception occurs during command execution, it displays the error message.
     */
    @FXML
    private void handleUserInput() {
        // Step 1: Retrieve user input
        String input = userInput.getText();
        String response;

        // Step 2: Parse user input
        if (Parser.isDisplayCommand(input)) {
            response = ui.displayCommand();
        } else {
            try {
                Command c = Parser.parseInput(input);
                // Step 3: Execute command
                response = c.execute(tasks, ui);
            } catch (IllegalArgumentException e) {
                // Step 4: Handle exceptions
                response = e.getMessage();
            }
        }
        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(input),
                HAssntDialogBox.getBotDialog(response));

        userInput.clear();
    }

    /**
     * Saves the current task list to storage.
     * If an IOException occurs during the saving process, it prints the stack trace.
     */
    public void saveTaskList() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
