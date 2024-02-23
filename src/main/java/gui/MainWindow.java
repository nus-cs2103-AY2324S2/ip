package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import wei.Wei;

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
    private Wei wei;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/userImage.png"));
    private Image weiImage = new Image(this.getClass().getResourceAsStream("/images/weiImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set object for the GUI.
     *
     * @param wei chatbot object.
     */
    public void setWei(Wei wei) {
        this.wei = wei;
        startingDialog();
    }

    /**
     * Starts conversation.
     */
    public void startingDialog() {
        String greeting = wei.greet();
        String reminder = wei.remind();
        dialogContainer.getChildren().add(DialogBox.getWeiDialog(greeting, weiImage));
        dialogContainer.getChildren().add(DialogBox.getWeiDialog(reminder, weiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Wei's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wei.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWeiDialog(response, weiImage)
        );
        userInput.clear();
    }
}
