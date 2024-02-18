package solaire;

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

    private Solaire solaire;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/AshenOne.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Solaire.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Solaire instance to be used by the GUI.
     *
     * @param s an instance of Solaire chatbot.
     */
    public void setSolaire(Solaire s) {
        solaire = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.isEmpty() : "User input should not be empty";
        String response = solaire.processInput(input);
        assert !response.isEmpty() : "Response should not be empty";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    public void displayWelcomeMessage() {
        String welcomeMessage = solaire.processInput("greet");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }
}

