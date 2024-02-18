package chatbot;

import chatbot.exception.DukeException;
import chatbot.gui.DialogBox;
import chatbot.parser.Parser;
import chatbot.ui.Ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.format.DateTimeParseException;

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

    private Duke duke;
    private Parser parser;
    private static final Image userImg = new Image(Duke.class.getResourceAsStream("/images/User.png"));
    private static final Image dukeImg = new Image(Duke.class.getResourceAsStream("/images/Chatbot.png"));

    @FXML
    public void initialize() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayMessage(Ui.printWelcomeMessage("Fatnom"));
        System.out.println("initialised");
    }

    public void setDuke(Duke d, Parser p) {
        duke = d;
        parser = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        displayUserMessage(input);
        if (input.toLowerCase().equals("bye")) {
            displayBotMessage(Ui.printByeMessage());
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.75), event -> Platform.exit()));
            timeline.play();
        } else {
            String response = getResponse(input);
            displayBotMessage(response);
        }
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            responseBuilder.append(parser.parseUserInput(input));
        } catch (DukeException e) {
            responseBuilder.append(Ui.printErrorMessage(e.getMessage()));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            String exceptionMessage = "invalid date time format! please use YYYY-MM-DD HH:MM format!";
            responseBuilder.append(Ui.printErrorMessage(exceptionMessage));
        }
        return responseBuilder.toString();
    }

    private void displayMessage(String message) {
        Label messageLabel = new Label(message);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(message, dukeImg));
    }

    private void displayUserMessage(String message) {
        Label userLabel = new Label(message);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(message, userImg));
    }

    private void displayBotMessage(String message) {
        Label botLabel = new Label(message);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, dukeImg));
    }
}
