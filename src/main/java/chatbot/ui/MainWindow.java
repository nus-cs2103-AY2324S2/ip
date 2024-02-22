package chatbot.ui;

import java.nio.file.Paths;

import chatbot.cortana.Cortana;
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

    private Cortana cortana;

    private final String userImagePath = Paths.get(".", "images", "chief.png").toString().substring(1);
    private final String chatbotImagePath = Paths.get(".", "images", "cortana.jpeg").toString().substring(1);

    private Image userImage = new Image(this.getClass().getResourceAsStream(userImagePath));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream(chatbotImagePath));

    /**
     * Initializes the MainWindow and greets the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chatbot for the MainWindow.
     * @param d The chatbot to be set.
     */
    public void setChatbot(Cortana d) {
        cortana = d;
    }

    /**
     * Greets the user.
     */
    public void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getChatbotDialog(cortana.getGreetString(), chatbotImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cortana.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getChatbotDialog(response, chatbotImage)
        );
        userInput.clear();
    }

}

