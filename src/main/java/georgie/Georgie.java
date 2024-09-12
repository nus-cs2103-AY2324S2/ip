package georgie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the main class for the Georgie application.
 */
public class Georgie extends Application {

    boolean canExit = false;
    private TaskList taskList = new TaskList();
    private static final String FILE_PATH = "./data/georgie.txt";

    /**
     * Represents the constructor of Georgie class. Constructs a new instance of the Georgie class. Initializes a Storage object with the specified file path
     * and loads tasks from the file into the associated task list.
     */
    public Georgie () {
        Storage storage = new Storage(FILE_PATH);
        storage.loadTasksFromFile(taskList.getTasks());
    }

    /**
     * Gets the response for the user input based on the command type. Handles "bye" command separately
     * to exit the application.
     *
     * @param input The user input command.
     * @return The response message.
     */
    public String getResponse(String input) {
        try {
            String[] parsedCommand = Parser.parse(input);
            String commandType = parsedCommand[0].toLowerCase();

            if (commandType.equals("bye")) {
                saveTasksToFile();
                canExit = true;
                return "Bye. Hope to see you again soon!";
            }
            return CommandHandler.handleCommand(input, taskList);
        } catch (GeorgieException e) {
            return e.getMessage();
        } finally {
            if (canExit) {
                Main.exitApp();
            }
        }
    }

    /**
     * Initializes the JavaFX application, loads the FXML file, and sets up the scene.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If an error occurs during FXML file loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/your/fxml/MainWindow.fxml"));
        AnchorPane root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);

        // Set the controller for the FXML file
        MainWindow mainWindowController = loader.getController();
        mainWindowController.setGeorgie(this);

        stage.setScene(scene);
        stage.show();
    }

    private void saveTasksToFile() {
        Storage storage = new Storage(FILE_PATH);
        storage.saveTasksToFile(taskList.getTasks());
    }
}