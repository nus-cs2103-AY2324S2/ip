package cappy;

import cappy.command.CommandType;
import cappy.controller.MainWindow;
import cappy.error.CappyException;
import cappy.parser.ParsedInput;
import cappy.parser.Parser;
import cappy.storage.Storage;
import cappy.task.TaskList;
import cappy.ui.Ui;

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
    private static final String STORAGE_PATH = "./cappy.csv";
    private static final Ui UI = new Ui();
    private static TaskList taskList;
    private static Storage storage;

    public Cappy() {}

    public static void main(String[] args) {
        UI.showBanner();
        UI.showGreetings();
        try {
            storage = new Storage(STORAGE_PATH);
            taskList = TaskList.load(storage);
            inputLoop();
            storage.close();
        } catch (IOException | CappyException e) {
            UI.showError(e);
        } finally {
            Cappy.UI.close();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(Cappy.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCappy(new Cappy());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void inputLoop() {
        String input = "";
        while (true) {
            input = UI.getInput();
            try {
                ParsedInput parsedInput = Parser.parse(input);
                parsedInput.executeCommand(taskList, UI, storage);
                if (parsedInput.getCommandType() == CommandType.BYE) {
                    break;
                }
            } catch (IOException | CappyException e) {
                UI.showError(e);
            }
        }
    }
}
