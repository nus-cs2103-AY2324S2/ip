package zhen;

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
 * Part of the UI design is adopted from CS2103T JavaFX tutorial.
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

    private Zhen zhen;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image zhenImage = new Image(this.getClass().getResourceAsStream("/images/ZHEN.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), zhenImage)
        );
    }

    public void setZhen(Zhen d) {
        zhen = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zhen's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zhen.getResponse(input);
        assert userImage != null : "Can't find user image";
        assert zhenImage != null : "Can't find ZHEN image";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, zhenImage)
        );
        if (response.equals("Bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
