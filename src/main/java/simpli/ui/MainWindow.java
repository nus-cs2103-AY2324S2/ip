package simpli.ui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import simpli.core.Simpli;

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

    private Simpli simpli;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private Image simpliImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Simpli.jpg")));

    /**
     * Driver code for MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setSimpli(Simpli simpli) {
        this.simpli = simpli;
        dialogContainer.getChildren().add(DialogBox.getSimpliDialog(simpli.greet(), simpliImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = simpli.processInput(input);

        if (response.equals("exit")) {
            dialogContainer.getChildren().add(
                    DialogBox.getSimpliDialog(simpli.bye(), simpliImage)
            );
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSimpliDialog(response, simpliImage)
        );
        userInput.clear();
    }
}
