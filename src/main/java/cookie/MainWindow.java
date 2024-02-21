package cookie;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Cookie cookie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sender.png"));
    private Image cookieImage = new Image(this.getClass().getResourceAsStream("/images/cookie.png"));

    /**
     * Initializes the controller after its root element has been completely processed.
     * Sets up scrolling functionality for the dialog container and displays the initial welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getCookieDialog("Heyy! I'm cookie! :) \n" +
                "What can I do for your today?", cookieImage));
        scrollPane.setStyle("-fx-background: #cdeaff;");

    }

    /**
     * Sets the Cookie instance associated with this MainWindow.
     *
     * @param c The Cookie instance to be set.
     */
    public void setCookie(Cookie c) {
        cookie = c;
    }

    /**
     * Handles user input by processing it and displaying appropriate responses from Cookie.
     * Clears the user input field after processing.
     *
     * @throws CookieException If an error occurs during the Cookie's response processing.
     */
    @FXML
    private void handleUserInput() throws CookieException {
        String input = userInput.getText();
        String response = cookie.getResponse(input);
        displayDialogBoxes(input, response);
        userInput.clear();

        if (cookie.isExit) {
            javafx.application.Platform.exit();
        }
    }

    /**
     * Displays the user input and Cookie's response as dialog boxes in the conversation window.
     *
     * @param input    The user input to be displayed.
     * @param response The response from Cookie to be displayed.
     */
    private void displayDialogBoxes(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCookieDialog(response, cookieImage)
        );
    }
}
