package saopig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * MainWindow for GUI
 */
public class MainWindow extends AnchorPane {
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    private Saopig saopig;


    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/User.png"));
    private Image saopigImage = new Image(this.getClass()
            .getResourceAsStream("/images/Saopig.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setSaopig(Saopig saopig) {
        this.saopig = saopig;
    }
    public Button getSendButton() {
        return sendButton;
    }
    public TextField getUserInput() {
        return userInput;
    }
    private String getTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    @FXML
    void handleUserInput() {
        String input = userInput.getText();
        String response = saopig.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, getTime(), userImage),
                DialogBox.getSaopigDialog(response, getTime(), saopigImage)
        );
        userInput.clear();
    }
}
