package com.example.artemis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller class for the main window of the Artemis application.
 * Manages the interaction between the GUI elements and the underlying Artemis logic.
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
    @FXML
    private Artemis artemis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image artemisImage = new Image(this.getClass().getResourceAsStream("/images/Artemis.png"));

    /**
     * Initializes the main window.
     * Binds the scrollPane's vvalueProperty to the dialogContainer's heightProperty.
     * Displays a welcome message in the dialog container on startup.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane not initialized";
        assert dialogContainer != null : "DialogContainer not initialized";
        assert userInput != null : "UserInput not initialized";
        assert sendButton != null : "SendButton not initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show welcome message on startup
        Ui ui = new Ui();
        String welcomeMessage = ui.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getArtemisDialog(welcomeMessage, artemisImage));
    }

    /**
     * Sets the Artemis instance for interaction.
     *
     * @param a The Artemis instance to be set.
     */
    public void setArtemis(Artemis a) {
        assert a != null : "Artemis instance cannot be null";
        artemis = a;
    }

    /**
     * Handles the user input event.
     * Creates dialog boxes for the user input and Artemis's response.
     * Clears the user input after processing.
     * Closes the main window if the "bye" command is entered.
     */
    @FXML
    private void handleUserInput() {
        assert artemis != null : "Artemis instance cannot be null";

        String input = userInput.getText();
        String response = artemis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getArtemisDialog(response, artemisImage)
        );
        userInput.clear();

        // Check if the "bye" command was entered
        if (input.trim().equalsIgnoreCase("bye")) {
            // Close the main window or stage
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
