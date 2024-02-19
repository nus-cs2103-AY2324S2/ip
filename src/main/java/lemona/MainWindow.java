package lemona;

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

    private Lemona lemona;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/jelly.png"));
    private Image LemonaImage = new Image(this.getClass().getResourceAsStream("/images/lemona.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLemona(Lemona l) {
        lemona = l;
    }

    public void greet() {
        String str = "Hello! I'm Lemona" + "\nWould you like some vitamins?";
        dialogContainer.getChildren().add(DialogBox.getLemonaDialog(str, LemonaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String str = "Bye. Don't forget to take a LEMONA!";
            dialogContainer.getChildren().add(DialogBox.getLemonaDialog(str, LemonaImage));

            Platform.exit();
        } else {
            String response = lemona.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLemonaDialog(response, LemonaImage)
            );
        }
        userInput.clear();
    }
}
