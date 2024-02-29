package gmo.ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import gmo.Gmo;

import java.io.IOException;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Gmo gmo;
    private Ui ui = new Ui();
    private final Image userImg = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image gmoImg = new Image(getClass().getResourceAsStream("/images/DaGmo.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // add the greeting
        dialogContainer.getChildren().add(DialogBox.getGmoDialog(ui.greet(), gmoImg));
    }

    public void setGmo(Gmo g) {
        this.gmo = g;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing PHI's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                // Close the JavaFX application after the delay
                Platform.exit();
            });
            pause.play();
        }
        String response = gmo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg),
                DialogBox.getGmoDialog(response, gmoImg)
        );
        userInput.clear();
    }
}
