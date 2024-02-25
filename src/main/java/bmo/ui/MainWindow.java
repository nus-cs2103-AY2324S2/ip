package bmo.ui;

import bmo.command.Command;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import bmo.BMO;

import java.io.IOException;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private BMO bmo;
    private Ui ui = new Ui();
    private final Image userImg = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image bmoImg = new Image(getClass().getResourceAsStream("/images/DaBmo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // add the greeting
        dialogContainer.getChildren().add(DialogBox.getBmoDialog(ui.greet(), bmoImg));
    }

    public void setBmo(BMO b) {
        this.bmo = b;
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
        String response = bmo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg),
                DialogBox.getBmoDialog(response, bmoImg)
        );
        userInput.clear();

    }
}
