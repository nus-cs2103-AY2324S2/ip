package Kokbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.application.Platform;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    /**
     * ScrollPane to contain the dialogContainer
     */
    @FXML
    private ScrollPane scrollPane;

    /**
     * Container for the dialog to display
     */
    @FXML
    private VBox dialogContainer;

    /**
     * Input field for user to enter commands
     */
    @FXML
    private TextField userInput;

    /**
     * Button to send user input
     */
    @FXML
    private Button sendButton;

    /**
     * Kokbot to be used in the MainWindow
     */
    private Kokbot kokbot;

    /**
     * Images for user and Kokbot
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dauser.jpg"));
    private Image kokbotImage = new Image(this.getClass().getResourceAsStream("/images/Daduke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(false);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    /**
     * Sets the Kokbot to be used in the MainWindow
     *
     * @param k Kokbot to be used
     */
    public void setKokbot(Kokbot k) {
        kokbot = k;
    }

    /**
     * Produces and inserts user and Kokbot DialogBoxes to screen
     * based on user input
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kokbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKokbotDialog(response, kokbotImage)
        );
        userInput.clear();
        if (input.equalsIgnoreCase("bye")) {
            closeMainWindow();
        }
    }

    private void closeMainWindow() {
        Platform.exit();
    }
}