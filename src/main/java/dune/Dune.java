package dune;

import dune.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


import java.util.Scanner;

/**
 * The main class for the program.
 */
public class Dune {


    private TaskList tasks;

    private Storage storage;

    private Parser parser;

    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dunePic = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for dune.Dune.
     */
    public Dune() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();
        this.storage.loadTasks(this.tasks);
        this.parser = new Parser();
    }

    /**
     * Runs the program.
     */
    public void run() {
        // originally in the run function
        this.ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isContinue = true;
        while (isContinue) {
            isContinue = this.parser.parse(scanner, tasks, ui, this.storage);
        }
    }


    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String response(String text) {
        return this.parser.response(text, this.tasks, this.storage);
    }





}
