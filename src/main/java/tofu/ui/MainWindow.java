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
    @FXML
    private HBox inputContainer;

    private Tofu tofu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tofuImage = new Image(this.getClass().getResourceAsStream("/images/tofu.png"));
    private Image background = new Image(this.getClass().getResourceAsStream("/images/background.png"));

    @FXML
    public void initialize() {
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 40.0);

        HBox.setHgrow(userInput, Priority.ALWAYS);
        AnchorPane.setBottomAnchor(inputContainer, 0.0);
        AnchorPane.setLeftAnchor(inputContainer, 0.0);
        AnchorPane.setRightAnchor(inputContainer, 0.0);

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Add background to Vbox
        BackgroundSize backgroundSize = new BackgroundSize(700, 400, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        dialogContainer.setBackground(background);
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
        dialogContainer.getChildren().add(DialogBox.getTofuDialog(Ui.welcomeMessage(), tofuImage));
    }

    public void setTofu(Tofu d) {
        tofu = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tofu's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        String response = tofu.getResponse(input);

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox tofuDialog = DialogBox.getTofuDialog(response, tofuImage);
        userDialog.prefWidthProperty().bind(dialogContainer.widthProperty());
        tofuDialog.prefWidthProperty().bind(dialogContainer.widthProperty());

        dialogContainer.getChildren().addAll(userDialog, tofuDialog);
        userInput.clear();
    }
}

