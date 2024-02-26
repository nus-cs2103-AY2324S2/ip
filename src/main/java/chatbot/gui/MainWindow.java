package chatbot.gui;

import chatbot.Duke;
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
 * Encapsulates the controller for MainWindow.
 * Provides the layout for the other controls.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
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
    private static final Image IMAGE_USER = new Image(Duke.class.getResourceAsStream("/images/User.png"));
    private static final Image IMAGE_CHATBOT = new Image(Duke.class.getResourceAsStream("/images/Chatbot.png"));

    /**
     * Initialises the MainWindow controller.
     */
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
        displayBotMessage(Ui.printWelcomeMessage("Fatnom"));
    }

    /**
     * Sets the Duke and Parser objects associated with this MainWindow instance.
     *
     * @param d The Duke object.
     * @param p The Parser object.
     */
    public void setDuke(Duke d, Parser p) {
        duke = d;
        parser = p;
    }

    /**
     * Handles user input by processing it with the Parser object and displaying the corresponding responses.
     * Clears the user input after processing.
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

    /**
     * Retrieves the chatbot's response to the given user input.
     *
     * @param input The user input.
     * @return The chatbot's response.
     */
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

    /**
     * Displays a message from the user in the dialog container.
     *
     * @param message The message to be displayed.
     */
    private void displayUserMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(message, IMAGE_USER));
    }

    /**
     * Displays a message from the chatbot in the dialog container.
     *
     * @param message The message to be displayed.
     */
    private void displayBotMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, IMAGE_CHATBOT));
    }
}
