import exceptions.TaylorException;
import filestorage.Storage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to execute Taylor ChatBot.
 */
// Adapted from Teammate: Chen Run Jia
// Source: https://github.com/RunjiaChen/ip/blob/master/src/main/java/Snom.java
public class Taylor extends Application {
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/TS.png"));
    private Image tayImage = new Image(this.getClass().getResourceAsStream("/images/Taylor.png"));
    public static List<List<Task>> tasksList;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Creates an instance of Taylor.
     */
    public Taylor() {
        try {
            tasksList = Storage.inputFromFile();
        } catch (Exception err) {
            tasksList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                tasksList.add(new ArrayList<>());
            }
        }
    }

    /**
     * Accepts the command entered by the user and do the necessary action
     * @param input is the input of the user represented as a string,
     * @return a String of the result
     */
    public String runCommand(String input) {
        String reply = "";
        try {
            reply = Parser.executeCommand(input, tasksList);
        } catch (TaylorException e) {
            return Ui.printError(e).toString();
        }
        return reply;
    }

    public String startup() {
        String setUpResponse = "";
        try {
            setUpResponse = Parser.executeCommand("list", tasksList);
        } catch (Exception err) {
            setUpResponse = Ui.printError(err).toString();
        }
        return setUpResponse;
    }

    @Override
    public void start(Stage stage) {
        String starting = startup();
        Label startMessage = new Label(starting);
        Scene scene = new Scene(startMessage);
        stage.setScene(scene);
        stage.show();
    }
}
