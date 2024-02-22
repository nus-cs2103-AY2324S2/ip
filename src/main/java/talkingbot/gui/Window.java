package talkingbot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import talkingbot.logic.TalkingBot;

/**
 * Class for the main window of the application.
 */
public class Window extends AnchorPane {

    private static final String BOT_NAME = "TalkingBot";
    private static final String USER_NAME = "You";
    private static final String BOT_IMAGE_PATH = "/gui/TalkingBot.jpg";
    private static final String USER_IMAGE_PATH = "/gui/You.jpg";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private TextField textInput;
    @FXML
    private Button sendButton;
    private TalkingBot talkingBot;
    private final Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private final Image botImage = new Image(this.getClass().getResourceAsStream(BOT_IMAGE_PATH));

    /**
     * Initializes the scrollPane and binds it to the vBox.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.vBox.heightProperty());
    }

    /**
     * Sets the logic handler within the window.
     * @param talkingBot The logic handler of the application.
     */
    public void setBot(TalkingBot talkingBot) {
        this.talkingBot = talkingBot;
        this.vBox.getChildren().addAll(
                Message.getBotMessage(this.botImage, this.talkingBot.getWelcomeMsg(), BOT_NAME)
        );
    }

    @FXML
    private void handleUserInput() {
        String text = this.textInput.getText();
        String botResponse = this.talkingBot.process(this.textInput.getText());
        this.vBox.getChildren().addAll(
                Message.getUserMessage(this.userImage, text, USER_NAME),
                Message.getBotMessage(this.botImage, botResponse, BOT_NAME)
        );
        this.textInput.clear();
    }
}
