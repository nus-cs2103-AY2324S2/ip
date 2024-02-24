package eve.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import eve.Eve;
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

    private Eve eve;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image eveImage = new Image(this.getClass().getResourceAsStream("/images/eve.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEve(Eve d) {
        eve = d;
        displayGreeting();
    }

    public void displayGreeting() {
        String greeting = eve.sayHello();
        DialogBox greetingDialog = DialogBox.getEveDialog(greeting, eveImage);
        dialogContainer.getChildren().add(greetingDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Eve's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = eve.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEveDialog(response, eveImage)
        );
        userInput.clear();
    }
}
