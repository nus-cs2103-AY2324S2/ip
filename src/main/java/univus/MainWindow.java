package univus;

import javafx.application.Platform;
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

    private Univus univus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));
    private Image univusImage = new Image(this.getClass().getResourceAsStream("/images/agumon.png"));

    /**
     * Initializes the scrollPane by binding its vertical value property to the height property of the dialogContainer.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Generates a greeting message from the Univus instance and displays it in the UI.
     * The generated greeting is added to the dialog container using a UnivusDialog component.
     */
    public void greet() {
        String greetingMessage = univus.greet();
        dialogContainer.getChildren().add(DialogBox.getUnivusDialog(greetingMessage, univusImage));
    }
    /**
     * Sets the Univus instance for this class.
     *
     * @param u The Univus instance to be set.
     */
    public void setUnivus(Univus u) {
        univus = u;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String response = univus.bye();
            dialogContainer.getChildren().add(DialogBox.getUnivusDialog(response, univusImage));

            // Save current storage to text file
            univus.save();

            // Close the GUI
            Platform.exit();
        } else {
            String response = univus.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getUnivusDialog(response, univusImage)
            );
        }
        userInput.clear();
    }
}
