package teletubbi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * Represents a chatbot that assists the user with managing a task list.
 * This chatbot has a task-list to keep track of tasks and a storage to store task content.
 */
public class Teletubbi {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Creates a new Teletubbi object with teletubbi.txt as the storage file.
     */
    public Teletubbi() {
        ui = new Ui();
        storage = new Storage("./data/teletubbi.txt");
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (TeletubbiException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Creates a new Teletubbi object with the given filePath to store data.
     *
     * @param filePath Filepath to storage file.
     */
    public Teletubbi(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path must not be null or empty!";
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (TeletubbiException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs and starts the chatbot.
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        System.out.println("isRunning");
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.breakLine();
                Command command = parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                if (command.isExit()) {
                    System.out.println("saving tasklist");
                    storage.save(taskList);
                    ui.exit();
                    isRunning = false;
                }
            } catch (TeletubbiException e) {
                ui.showMessage(e.getMessage());
            } finally {
                if (isRunning) {
                    ui.breakLine();
                }
            }
        }
    }

    /**
     * Gets the response given a text user input.
     *
     * @param input User input.
     * @return Teletubbi response.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (TeletubbiException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        Teletubbi teletubbi = new Teletubbi("./data/teletubbi.txt");
        teletubbi.run();
    }
}
