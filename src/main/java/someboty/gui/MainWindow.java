package someboty.gui;

import someboty.SomeBoty;
import someboty.exceptions.TerminateException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.application.Platform;

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

    private SomeBoty bot;
    private boolean endApplication = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sensei.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/izuna.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
           DialogBox.getBotDialog(SomeBoty.Greeting(), botImage)
           );
    }

    public void setBot(SomeBoty bot) {
        this.bot = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;

        if (endApplication) {
            Platform.exit();
            return;
        }

        try {
            response = bot.getResponse(input);
            
        } catch (TerminateException e) {
            response = e.getMessage();
            endApplication = true;
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );

        userInput.clear();
    }
}
