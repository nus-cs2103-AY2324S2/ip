package solaire;

import javafx.animation.PauseTransition;
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
    private Image solaireImage = new Image(this.getClass().getResourceAsStream("/images/Solaire.jpeg"));

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
     * Creates two dialog boxes, one echoing user input and the other containing Solaire's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert !input.isEmpty() : "User input should not be empty";
        if (input.equals("bye")) {
            pauseGui();
        }
        String response = solaire.processInput(input);
        assert !response.isEmpty() : "Response should not be empty";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSolaireDialog(response, solaireImage)
        );
        userInput.clear();
    }

    private void pauseGui() {
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
        pause.setOnFinished(event -> System.exit(0));
        pause.play();
    }


    /**
     * Displays the welcome message from Solaire.
     */
    public void displayWelcomeMessage() {
        String welcomeMessage = solaire.processInput("greet");
        dialogContainer.getChildren().add(DialogBox.getSolaireDialog(welcomeMessage, solaireImage));
    }
}

