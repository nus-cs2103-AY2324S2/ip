package dude;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainView. Provides the layout for the other controls.
 */
public class MainView extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button sendButton;

    @FXML
    private TextField userInputField;

    @FXML
    private VBox dialogContainer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleUserInput() {
        String input = userInputField.getText();
        String response = "Duke heard: " + input;

        System.out.println("User input: " + input);
        userInputField.clear();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, userImage)
        );

        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });

    }

}
