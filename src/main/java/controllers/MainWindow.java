package controllers;

import java.util.Objects;

import application.Chitty;
import commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.Response;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private VBox container;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private Chitty chitty;

    private final Image userImage =
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/sana.png")));
    private final Image chittyImage =
            new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/chitty.png")));

    /**
     * Initializes the MainWindow controller.
     * Adds initial dialog box with Chitty's greeting response and binds scroll properties.
     */
    @FXML
    public void initialize() {
        assert userImage != null;
        assert chittyImage != null;
        // Display greeting message to user.
        dialogContainer.getChildren().add(DialogBox.getChittyDialog(Response.getGreetingResponse(), chittyImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setChitty(Chitty chitty) {
        this.chitty = chitty;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = chitty.getCommand(input);
        dialogContainer.getChildren().addAll(
                getSeparator(),
                DialogBox.getUserDialog(input, userImage),
                getSeparator(),
                DialogBox.getChittyDialog(command.getResponse(), chittyImage)
        );
        userInput.clear();
    }

    private static Separator getSeparator() {
        return new Separator();
    }
}
