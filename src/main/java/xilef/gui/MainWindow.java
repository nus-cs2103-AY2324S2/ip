package xilef.gui;

import xilef.Xilef;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.util.Duration;

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
    private Xilef xilef;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat2.jpeg"));
    private final Image xilefImage = new Image(this.getClass().getResourceAsStream("/images/cat1.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Xilef d) {
        xilef = d;
    }

    @FXML
    public void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(xilef.welcome(), xilefImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Xilef's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = xilef.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, xilefImage)
        );
        userInput.clear();
        if (xilef.isExit()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> Platform.exit()));
            timeline.play();
        }
    }
}
