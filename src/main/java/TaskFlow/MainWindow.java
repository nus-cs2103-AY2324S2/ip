package TaskFlow;

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

    private TaskFlow taskFlow;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTaskie(TaskFlow d) {
        taskFlow = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = taskFlow.run(input);
        assert response != "";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTaskieDialog(response, chatBotImage)
        );
        userInput.clear();
    }

    /**
     * Create a dialog box to display the welcome message.
     */
    public void greet() {
        String response = taskFlow.showGreetings();
        dialogContainer.getChildren().addAll(
                DialogBox.getTaskieDialog(response, chatBotImage)
        );
        userInput.clear();
    }
}
