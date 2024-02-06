package capone.ui.gui;

import capone.Capone;
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

    private Capone capone;

    private Image userImage = new Image(this.getClass().getResourceAsStream(DialogBox.getUserImg()));
    private Image caponeImage = new Image(this.getClass().getResourceAsStream(DialogBox.getCaponeImg()));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setCapone(Capone capone) {
        this.capone = capone;
        this.dialogContainer.getChildren().addAll(
                DialogBox.getCaponeDialog(this.capone.getUi().printWelcomeMsg(),
                this.caponeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = this.capone.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getCaponeDialog(response, this.caponeImage)
        );
        this.userInput.clear();
    }
}
