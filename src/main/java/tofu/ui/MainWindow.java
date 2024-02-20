package tofu.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import tofu.Tofu;

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

    private Tofu tofu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tofuImage = new Image(this.getClass().getResourceAsStream("/images/tofu.png"));
    private Image background = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Add background to Vbox
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        dialogContainer.setBackground(background);
        dialogContainer.getChildren().add(DialogBox.getTofuDialog(Ui.welcomeMessage(), tofuImage));
    }

    public void setTofu(Tofu d) {
        tofu = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tofu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tofu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTofuDialog(response, tofuImage)
        );
        userInput.clear();
    }
}

