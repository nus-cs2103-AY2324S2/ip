import exceptions.TaylorException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
public class Taylor extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/TS.png"));
    private Image taytay = new Image(this.getClass().getResourceAsStream("/images/Taylor.png"));
    private static List<List<Task>> tasksList;
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
        tasksList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tasksList.add(new ArrayList<>());
        }
    }

    // Adapted from Teammate: Chen Run Jia
    // Source: https://github.com/RunjiaChen/ip/blob/master/src/main/java/Snom.java
    /**
     * Accepts the command entered by the user and converts it
     *         into a instance of a Command.
     * @param input is the input of the user represented as a string,
     * @return a String representing whether the command is valid or not.
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

    public static List<List<Task>> getTasksList() {
        return tasksList;
    }

    public String start() {
        return Parser.executeCommand("list", tasksList);
    }

    @Override
    public void start(Stage stage) {
        Label startMessage = new Label(this.start());
        Scene scene = new Scene(startMessage);
        stage.setScene(scene);
        stage.show();
    }
}
