package gui;

import chrisPBacon.ChrisPBacon;
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

    private ChrisPBacon chrisPBacon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chrisPImage = new Image(this.getClass().getResourceAsStream("/images/DaChrisPBacon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChrisPBacon(ChrisPBacon chris) {
        chrisPBacon = chris;

        dialogContainer.getChildren().addAll(
                DialogBox.getChrisPDialog(chrisPBacon.getGreeting(), chrisPImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing ChrisP's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chrisPBacon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChrisPDialog(response, chrisPImage)
        );
        userInput.clear();
    }
}
