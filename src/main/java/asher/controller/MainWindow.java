package asher.controller;

import asher.Asher;
import asher.ui.Ui;

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

    private Asher asher;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image asherImage = new Image(this.getClass().getResourceAsStream("/images/DaAsher.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = ui.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getAsherDialog(welcomeMsg, asherImage));
        // dialogContainer.setStyle("-fx-background-color: #a6acb8; ");
        dialogContainer.setPrefHeight(558.0);
    }

    /**
     * Sets the Asher object.
     *
     * @param a The Asher object.
     */
    public void setAsher(Asher a) {
        asher = a;
    }

    /**
     * Handles user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = asher.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAsherDialog(response, asherImage)
        );
        userInput.clear();
        if (asher.isExit) {
            javafx.application.Platform.exit();
        }
    }
}




