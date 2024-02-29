package teletubbi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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

    private Teletubbi teletubbi;

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/Screenshot 2024-02-17 at 5.12.09 PM.png"));
    private Image teletubbiImage = new Image(this.getClass().getResourceAsStream("/images/teletubbi.png"));

    /**
     * Initializes the JavaFX app.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Sets teletubbi as teletubbi given.
     *
     * @param t Teletubbi to be set.
     */
    public void setTeletubbi(Teletubbi t) {
        teletubbi = t;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = teletubbi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTeletubbiDialog(response, teletubbiImage)
        );
        userInput.clear();
    }
}
