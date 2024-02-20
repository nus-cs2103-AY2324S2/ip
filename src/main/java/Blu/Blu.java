package blu;

import java.io.File;
import java.io.IOException;

import blu.command.ByeCommand;
import blu.command.Command;
import blu.exception.BluException;
import blu.exception.StorageException;
import blu.parser.InputParser;
import blu.storage.Storage;
import blu.task.TaskList;
import blu.ui.MainWindow;
import blu.ui.UI;
import blu.utils.BluLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * The main class for the Blu application, which extends the JavaFX framework.
 * It provides methods to start and initialize the GUI,
 * process user input commands, and manage the application's lifecycle.
 */
public class Blu extends Application {
    private static final String DEFAULT_STORAGE_PATH = "./data/data.csv";
    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private boolean isExit;
    private String welcomeMessage;

    /**
     * This method is called when the application should initialize itself,
     * prior to being made visible to the user. It initializes the UI, storage, and task list.
    */
    @Override
    public void init() {
        ui = new UI();
        isExit = false;
    }

    private void initStorage(String storagePath) {
        try {
            storage = new Storage(storagePath);
            taskList = storage.loadTasks();
            welcomeMessage = ui.getWelcomeMessage(storage.getAbsoluteFilePath());
        } catch (BluException e) {
            BluLogger.severe(e.getMessage());
            System.exit(1);
        }
    }

    private File getStorageFilePath(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Storage File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        File storageFilePath = getStorageFilePath(stage);
        if (storageFilePath == null) {
            initStorage(DEFAULT_STORAGE_PATH);
        } else {
            initStorage(storageFilePath.getAbsolutePath());
        }
        stage.setTitle("Blu");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Blu.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setBlu(this);
            stage.show();
            mainWindow.showGreeting(welcomeMessage);
        } catch (IOException e) {
            BluLogger.severe(e.getMessage());
        }
    }

    /**
     * Checks if the application is set to exit.
     *
     * @return true if the application is exiting, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Processes the user input string and executes the corresponding command.
     *
     * @param userInput The string input by the user.
     * @return The response from executing the user command.
     */
    public String getResponse(String userInput) throws BluException {
        Command command = new InputParser().parseInput(userInput);
        assert command != null : "Parsed command should not be null";
        if (command instanceof ByeCommand) {
            isExit = true;
        }
        return command.execute(taskList, storage, ui);
    }

    /**
     * This method is called when the application should stop,
     * and provides a convenient place to prepare for application exit and save state.
     */
    @Override
    public void stop() {
        try {
            storage.close();
        } catch (StorageException e) {
            BluLogger.severe(e.getMessage());
        }
    }
}
