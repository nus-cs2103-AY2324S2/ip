package kervyn;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kervyn.Tasks.TaskList;

import java.io.IOException;
import java.util.Objects;

import kervyn.FXControls.DialogBox;

/**
 * Main class for the Kervyn application.
 * This class initializes the application and starts the interaction with the user.
 */
public class Kervyn extends Application {

    private TaskList taskList;
    private Ui ui;


    private VBox dialogContainer;
    private Image kervyn = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    public Kervyn () {}

    /**
     * The entry point for the Kervyn application.
     *
     * @param args Command-line arguments, not used in this application.
     * @throws IOException If an I/O error occurs when starting the application.
     */
//    public static void main(String[] args) throws IOException {
//        new Kervyn("data/tasks.txt").run();
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.txt", dialogContainer, kervyn);
        try {
            taskList = new TaskList(storage.readTasks());
        } catch (IOException e) {
            taskList = new TaskList();
            throw new RuntimeException(e);
        }
        ui.startChatBot(taskList, storage, stage);
    }

    public String getResponse(String input) {
        return "Kervyn heard: " + input;
    }
}
