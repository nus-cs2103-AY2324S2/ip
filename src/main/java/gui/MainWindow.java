package gui;

import felix.Felix;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Felix felix;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gig.jpg"));
    private Image felixImage = new Image(this.getClass().getResourceAsStream("/images/felix.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.hvalueProperty().bind(dialogContainer.widthProperty());
    }

    public void setFelix(Felix d) {
        felix = d;
    }

    public void helloFelix() {
        Label greeting = new Label("Hello there! I'm Felix. Ready to help!");
        dialogContainer.getChildren().add(
                DialogBox.getFelixDialog(greeting, new ImageView(felixImage))
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(felix.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getFelixDialog(dukeText, new ImageView(felixImage))
        );
        userInput.clear();
    }
}
