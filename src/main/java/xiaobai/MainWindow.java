package xiaobai;

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

    private XiaoBai xiaoBai;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image xiaoBaiImage = new Image(this.getClass().getResourceAsStream("/images/XiaoBai.png"));

    /**
     * Initializes the main window layout.
     * Binds the scroll pane to the height of the dialog container.
     * Displays a welcome message from XiaoBai upon initialization.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeString = String.format(
                "Woof! I'm %s \nWhat can I do for you? \n", XiaoBai.getName());
        dialogContainer.getChildren().add(DialogBox.getDialog(welcomeString, xiaoBaiImage));
    }

    /**
     * Sets the instance of XiaoBai for the main window.
     * 
     * @param xiaoBai The instance of XiaoBai to be set.
     */
    public void setXiaoBai(XiaoBai d) {
        xiaoBai = d;
    }

    /**
     * Handles user input.
     * Creates dialog boxes for the user input and the corresponding response from
     * XiaoBai, then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = xiaoBai.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDialog(response, xiaoBaiImage));
        userInput.clear();
    }
}
