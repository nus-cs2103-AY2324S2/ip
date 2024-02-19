package cappy;

import static cappy.constant.FilePath.MAIN_WINDOW_FXML;
import static cappy.constant.FilePath.STORAGE_PATH;

import cappy.command.CommandType;
import cappy.controller.MainWindow;
import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.util.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the Cappy application.
 *
 * <p>The {@code Cappy} class is the entry point of the Cappy application. It initializes the
 * application components and starts the main input loop to interact with the user.
 */
public class Cappy extends Application {
    private static TaskList taskList;
    private static Storage storage;
    private MainWindow mainWindow;

    public Cappy() {
        try {
            storage = new Storage(STORAGE_PATH);
            taskList = TaskList.load(storage);
        } catch (IOException | CappyException e) {
            Logger.error(e);
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Cappy");
            FXMLLoader fxmlLoader = new FXMLLoader(Cappy.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setCappy(this);
            stage.show();
            mainWindow.showGreetings();
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    public void handleUserInput(String input) {
        try {
            ParsedInput parsedInput = Parser.parse(input);
            parsedInput.executeCommand(taskList, mainWindow, storage);
            if (parsedInput.getCommandType() == CommandType.BYE) {
                exit();
                System.exit(0);
            }
        } catch (IOException e) {
            Logger.error(e);
        } catch (CappyException e) {
            mainWindow.showError(e);
        }
    }

    public void exit() {
        try {
            storage.close();
        } catch (IOException e) {
            mainWindow.showError(e);
            Logger.error(e);
        }
    }
}
