package baron;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window FXML file
 */
public class MainWindow implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Baron baron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/da-user.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/da-duke.jpg"));

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = baron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaronDialog(response, botImage));
        userInput.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBotInstance(Baron baron) {
        this.baron = baron;
    }
}
