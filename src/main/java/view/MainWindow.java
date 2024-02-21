package view;

import com.sun.tools.javac.Main;
import snoopy.Snoopy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * Controller for view.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 400;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Snoopy snoopy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPrefHeight(this.USE_COMPUTED_SIZE);
    }

    /**
     * Sets the snoopy bot to be used in the GUI.
     * @param d the snoopy bot to be used in the GUI
     */ public void setSnoopy(Snoopy d) {
        snoopy = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Snoopy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        Boolean isBye = false;
        if (input.toUpperCase().equals("BYE")) {
            isBye = true;
        }
        String response = snoopy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        exitProgramCheck(isBye);
    }

    /**
     * Checks if the user has inputted "bye" and exits the program if so.
     * @param isBye whether the user has inputted "bye"
     */
    private void exitProgramCheck(Boolean isBye) {
        if (isBye) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }

}
