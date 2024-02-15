package duke;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main class of the Duke program.
 */
public class Duke extends Application {
    private Storage storage = null;
    private TaskList tasks;
    private Ui ui = null;
    private TextArea chatArea;
    private TextField inputField;

    public Duke() {
    }

    /**
     * Constructor for Duke.
     * @param filePath The file path to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     * @throws DukeException If there is an error running the program.
     */
    public void run() throws DukeException {
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            String result = Parser.parse(userInput, tasks, ui, storage);
            if (result.equals("1")) {
                isExit = true;
                ui.closeScanner();
            }
            chatArea.appendText("User: " + userInput + "\n");
            chatArea.appendText("Duke: " + result + "\n");
        }
    }

    /**
     * Main method to run the Duke program.
     * @param args The arguments to run the program.
     * @throws DukeException If there is an error running the program.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Starts the Duke program.
     * @param stage The stage to start the program.
     */
    @Override
    public void start(Stage stage) {
        // Initialize chat area
        chatArea = new TextArea();
        chatArea.setEditable(false);

        // Initialize input field
        inputField = new TextField();
        inputField.setPromptText("Type here...");

        // Initialize send button
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String userInput = inputField.getText();
            inputField.clear();
            // Append user input to chat area
            chatArea.appendText("User: " + userInput + "\n");
            // Process user input
            String result;
            try {
                result = Parser.parse(userInput, tasks, ui, storage);
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
            // Append result to chat area
            chatArea.appendText("Duke: " + result + "\n");
        });

        // Create layout
        VBox layout = new VBox();
        layout.getChildren().addAll(chatArea, inputField, sendButton);

        // Create scene
        Scene scene = new Scene(layout, 400, 300);

        // Set stage
        stage.setScene(scene);
        stage.setTitle("Duke Chatbot");
        stage.show();
    }
}
