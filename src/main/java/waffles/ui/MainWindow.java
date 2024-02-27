package waffles.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import waffles.Waffles;

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
    private Waffles waffles;
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image wafflesImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/surprised_pikachu.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the instance of Waffles to be used by the GUI.
     *
     * @param w Waffles instance to be used by GUI.
     */
    public void setWaffles(Waffles w) {
        this.waffles = w;
    }

    /**
     * Greets the user upon loading up the GUI.
     */
    public void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getWafflesDialog(waffles.greet(), wafflesImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = waffles.getResponse(input);
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getWafflesDialog(response, wafflesImage);
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dialogContainer.getChildren().addAll(
                userDialog,
                dukeDialog
        );
        userInput.clear();
    }
}
