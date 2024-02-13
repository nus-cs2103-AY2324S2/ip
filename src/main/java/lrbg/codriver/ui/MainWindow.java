package lrbg.codriver.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import lrbg.codriver.CoDriver;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private CoDriver codriver;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image codriverImg = new Image(this.getClass().getResourceAsStream("/images/CoDriver.png"));

    /**
     * Initializes the main window and shows the greeting message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getCoDriverDialog(CoDriver.GREETING_MESSAGE, codriverImg)
        );
    }

    /**
     * Sets the CoDriver object for the GUI.
     *
     * @param codriver The CoDriver object to be set.
     */
    public void setCoDriver(CoDriver codriver) {
        this.codriver = codriver;
    }

    /**
     * Handles the user input and displays the response from CoDriver.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = codriver.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg),
                DialogBox.getCoDriverDialog(response, codriverImg)
        );
        if (codriver.isExit) {
            System.exit(0);
        }
        userInput.clear();
    }
}
