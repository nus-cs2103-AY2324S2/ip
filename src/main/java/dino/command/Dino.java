package dino.command;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** The main class for the Dino application. */
public class Dino extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Enumeration representing the types of tasks in the Dino application.
     * Tasks can be of type TODO, DEADLINE, or EVENT.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a new Dino instance with the specified file path.
     */
    public Dino() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = storage.loadTasksFromFile();

    }

    @Override
    public void start(Stage stage) {
        TextArea chatArea = new TextArea();
        TextField userInputField = new TextField();
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String userCommand = userInputField.getText();
            String response = processUserInput(userCommand);
            appendToChat(chatArea, "User: " + userCommand);
            appendToChat(chatArea, "Dino: " + response);
            userInputField.clear();
        });

        VBox root = new VBox(10); // Vertical box layout
        root.getChildren().addAll(chatArea, userInputField, submitButton);

        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Dino Chatbot");
        stage.setScene(scene);
        stage.show();
    }


    private String processUserInput(String command) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, sc);

        if (command.equals("bye")) {
            storage.saveTasksToFile(tasks.getTaskList());
            ui.goodbye();
            sc.close();
            System.exit(0);
        } else {
            return parser.parseCommand(command);
        }
        return null;
    }

    private void appendToChat(TextArea chatArea, String message) {
        chatArea.appendText(message + "\n");
    }

    /**
     * The main entry point of the Dino application.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
