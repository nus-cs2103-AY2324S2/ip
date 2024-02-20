package chatbro;

import javafx.fxml.FXML;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private boolean isRunning = true;
    private ChatBro chatbro;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image chatBroImage = new Image(this.getClass().getResourceAsStream("/images/DaBro.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(
            DialogBox.getChatbroDialog(Ui.welcomeMessage(), chatBroImage)
        );
    }

    public void setChatBro(ChatBro cb) {
        chatbro = cb;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equalsIgnoreCase("bye")) {
            isRunning = false;
        }
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getChatbroDialog(response, chatBroImage)
        );
        //@@author jimseah-0116 -reused
        // Reused from https://github.com/nus-cs2103-AY2324S2/ip/pull/21/
        // code idea taken from Shu Heng's MainWindow.java
        if (!isRunning) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.2));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else {
            userInput.clear();
        }
    }

    /**
     * Gets response from ChatBro given user input.
     * @param input User input.
     */
    @FXML
    private String getResponse(String input) {
        return Parser.parseCommand(input);
    }
}
