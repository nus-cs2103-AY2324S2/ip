package controller;

import cal.Cal;
import exceptions.CalException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * represents the main view for the application consisting of chat window,
 * text field and send button.
 */
public class MainWindow {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;

    private Cal cal;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image calImage = new Image(this.getClass().getResourceAsStream("/images/cal.jpg"));
    private Image errorBotImage = new Image(this.getClass().getResourceAsStream("/images/error-bot.jpg"));
    private Image errorMsgSticker = new Image(this.getClass().getResourceAsStream("/images/error-msg-sticker.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            dialogContainer.setPrefHeight(newValue.getHeight());
            dialogContainer.setPrefWidth(newValue.getWidth());
        });
        dialogContainer.setPadding(new Insets(15, 10, 15, 10));
    }

    public void setCal(Cal cal) {
        this.cal = cal;
    }

    @FXML
    private void handleUserInput() throws CalException {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        try {
            String response = cal.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage, "#EDEDEF"),
                    DialogBox.getCalDialog(response, calImage, "#A366F9")
            );
        } catch (CalException e) { 
            ImageView imageView = new ImageView(errorMsgSticker);
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(0, 0, 5, 55));
            vbox.getChildren().add(imageView);
            dialogContainer.getChildren().addAll(
                    vbox,
                    DialogBox.getCalDialog(e.getMessage(), errorBotImage, "#FDBE44")
            );
        }

        userInput.clear();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) throws CalException {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }
}
