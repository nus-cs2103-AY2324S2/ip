package jiayou;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import jiayou.task.TaskList;

/**
 * Represents a chatbot named Jiayou.
 * @author Liu Jiayao
 */
public class Jiayou {
    private static final String FILE_PATH = "./data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Jiayou instance.
     *
     */
    public Jiayou() {
        this.tasks = new TaskList();
        this.ui = new Ui(this.tasks);
        this.storage = new Storage(FILE_PATH, this.tasks);
        this.tasks.linkStorage(this.storage);
        this.storage.loadFromFile();
    }

    /**
     * Runs the chatbot Jiayou to send the greeting message.
     */
    public void run() {
        this.ui.greet();
    }

    /**
     * Iteration 1:
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Initializes the chatbot and runs it.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Jiayou().run();
    }
}
