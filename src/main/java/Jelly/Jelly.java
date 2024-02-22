package jelly;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Jelly class
 */
//enum is not required
public class Jelly {

    private static final String farewell = "(•︿•) Bye. Hope to see you again soon!";

    private static String path = "jelly.txt";
    private TaskList list;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Jelly_user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Jelly_icon.png"));

    /**
     * Default constructor for Jelly
     */
    public Jelly() {

        storage = new Storage(path);

        ui = new Ui();

        try {

            list = new TaskList(storage.load());

        } catch (JellyException e) {

            list = new TaskList();
            ui.printLoadingError(e);
        }

        parser = new Parser(list, ui);
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
        return parser.read(input);
    }

    /**
     * Saves storage of tasks into txt file
     */
    public void saveStorage() {

        storage.save(list);
    }

    /**
     * @return Farewell message
     */
    public String getFarewell() {

        return farewell;
    }

}
