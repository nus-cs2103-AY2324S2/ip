package gui;

import destiny.Destiny;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static Background DIALOGUE_BG = new Background(new BackgroundFill(
            new LinearGradient(
                    0, 0.5, 1, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#330066")),
                    new Stop(1, Color.web("#000066"))), null, null));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Destiny destiny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Chad.png"));
    private Image destinyImage = new Image(this.getClass().getResourceAsStream("/images/Chadette.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setSpacing(10.0);
        dialogContainer.setBackground(DIALOGUE_BG);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getDestinyDialog(Destiny.greetingMessage, destinyImage)
        );
    }

    public void setDestiny(Destiny d) {
        destiny = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Destiny's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String destinyText = destiny.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDestinyDialog(destinyText, destinyImage)
        );
        userInput.clear();
    }
}
