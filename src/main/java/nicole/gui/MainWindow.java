package nicole.gui;

import java.io.IOException;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import nicole.userrequests.Ui;
import nicole.entry.Nicole;

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
    private Nicole nicole;

    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/user.JPG")));
    private final Image nicoleImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/raiden.jpeg")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getNicoleDialog(Ui.greet(), nicoleImage)
        );
    }
    public void setNicole(Nicole n) {
        nicole = n;
    }

    /**
     * Sets the exit for the MainWindow.
     */
    private void exit() {
        int delayInMillis = 1000;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, delayInMillis);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getNicoleDialog("Bye, for now ;)", nicoleImage)
            );
            this.exit();
        } else {
            try {
                Ui ui = new Ui();
                String response = ui.talkToUser(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getNicoleDialog(response, nicoleImage)
                );
                userInput.clear();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

