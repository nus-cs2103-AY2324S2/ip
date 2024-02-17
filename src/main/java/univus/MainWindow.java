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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void greet() {
        String greetingMessage = univus.greet();
        dialogContainer.getChildren().add(DialogBox.getUnivusDialog(greetingMessage, univusImage));
    }
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
