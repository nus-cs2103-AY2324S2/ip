package rick;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rick.logic.Executer;
import rick.logic.Parser;
import rick.logic.RickException;
import rick.logic.command.Command;
import rick.ui.DialogBox;
import rick.ui.Ui;
import rick.util.Storage;
import rick.util.TaskList;


/**
 * A Rick chatbot.
 */
public class Rick extends Application {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a new instance of the Rick chatbot with specified filePath to store data on hard drive.
     */

    public Rick() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (RickException e) {
            //to delete
            this.ui.showLoadingError();
            Ui.reply(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs an instance of Rick.
     */
    public void run() {
        Ui.hello();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    Ui.exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    Ui.reply(tasks.list());
                } else if (input.startsWith("mark")) {
                    Ui.reply(tasks.mark(input, storage));
                } else if (input.startsWith("unmark")) {
                    Ui.reply(tasks.unmark(input, storage));
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    Ui.reply(tasks.addToList(input, storage));
                } else if (input.startsWith("delete")) {
                    Ui.reply(tasks.delete(input, storage));
                } else {
                    Ui.reply("I don't understand what you are saying... ㅜㅜ");
                }
            } catch (RickException e) {
                Ui.reply(e.getMessage());
            } catch (Exception e1) {
                Ui.reply("ERROR: Congratulations! "
                        + "You have input a message that the developer did not expect. "
                        + "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.");
                return;
            }
        }

    }

    /**
     * Create a dialog label with text wrapped
     * @param text a string to be contained in the label
     * @return a label which contains the specified string
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Handles user input.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(getResponse(userInput.getText())), duke)
        );
        userInput.clear();
    }

    /**
     * Gets response for specified input
     * @param input user input
     * @return a string representing the response of the chatbot
     */
    @FXML
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            Command parsedCommand = parser.parse();
            Executer executer = new Executer(this.tasks, this.storage);
            return executer.execute(parsedCommand);
        } catch (RickException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "ERROR: Congratulations! "
                    + "You have input a message that the developer did not expect. "
                    + "Report this issue here: https://forms.gle/hnnDTA7qYMnhJvQ46.";
        }
    }

    /**
     * Creates a new Rick instance and run it. Executes the programme.
     */
    public static void main(String[] args) {
        Rick rick = new Rick();
        Ui.hello();
    }
}
