package linus;

import java.util.ArrayList;

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
    // Adapted from @Howlong11
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Linus linus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image linusImage = new Image(this.getClass().getResourceAsStream("/images/linus.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLinus(Linus linus) {
        this.linus = linus;
    }

    public void greet() {
        String str = "Hello! I'm Linus.\n" + "I'm the mascot of National University of Singapore!";
        dialogContainer.getChildren().add(DialogBox.getLinusDialog(str, linusImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("terminate")) {
            String str = "Bye. Hope to see you again, ROOAARRRRRR!";
            dialogContainer.getChildren().add(DialogBox.getLinusDialog(str, linusImage));

            Platform.exit();
        } else {
            String response = linus.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLinusDialog(response, linusImage)
            );
        }

        userInput.clear();
    }
}