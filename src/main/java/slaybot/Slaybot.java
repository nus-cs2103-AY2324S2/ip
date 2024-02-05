package slaybot;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * The Slaybot class combines the various classes to provide an interactive task tracking bot
 */
public class Slaybot {

    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaBot.png"));

    /**
     * Constructor for Slaybot class
     */
    public Slaybot() {
        this.tasks = new TaskList();
        this.ui = new Ui(tasks);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


    public void run() {
        this.ui.start();
    }
    public static void main(String[] args) {
        new Slaybot().run();
    }
}
