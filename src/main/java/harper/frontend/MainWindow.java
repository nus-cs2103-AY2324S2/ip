package harper.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import harper.Harper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Harper harper;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image harperImage = new Image(this.getClass().getResourceAsStream("/images/DaHarper.png"));

    /**
     * Initialises the chatbot.
     */
    @FXML
    public void initialize() {
        this.addDialogBoxIntoDialogContainer(
                DialogBox.getHarperDialog("\nHello! I am Harper.\n" + "What can I do for you?\n",
                        harperImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setHarper(Harper h) {
        harper = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Harper's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = harper.getResponse(input);
        this.addDialogBoxIntoDialogContainer(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHarperDialog(response, harperImage)
        );
        userInput.clear();
        checkIfBye(input);
    }

    public void addDialogBoxIntoDialogContainer(DialogBox... dialogBoxes) {
        this.dialogContainer.getChildren().addAll(dialogBoxes);
    }

    /**
     * Checks if the input is bye, if it is, exits the program,
     * else, continue.
     *
     * @param input Input of the user.
     */
    public void checkIfBye(String input) {
        String inputAfterTrim = input.trim();
        if (!inputAfterTrim.equals("bye")) {
            return;
        }
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
        });
        timer.start();
    }
}
