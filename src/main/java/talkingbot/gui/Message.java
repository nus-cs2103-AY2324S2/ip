package talkingbot.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class to display message bubbles.
 */
public class Message extends HBox {
    private static final String MESSAGE_FXML_PATH = "/gui/Message.fxml";
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label displayText;
    @FXML
    private Label displayName;

    /**
     * Creates a new Message instance.
     * @param displayPicture Display picture of the sender.
     * @param displayText Text to be displayed in the message.
     * @param displayName Display name of the sender.
     */
    private Message(Image displayPicture, String displayText, String displayName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource(MESSAGE_FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.displayPicture.setImage(displayPicture);
        this.displayText.setText(displayText);
        this.displayName.setText(displayName);
    }

    /**
     * Flips the positioning of the children in the Message.
     * Used to create the bot's response.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets the user message to be displayed.
     *
     * @param displayPicture Display picture of the user.
     * @param text The text sent by the user.
     * @param displayName The user's display name.
     * @return
     */
    public static Message getUserMessage(Image displayPicture, String text, String displayName) {
        return new Message(displayPicture, text, displayName);
    }

    /**
     * Gets the bot's response message.
     *
     * @param displayPicture Display picture of the bot.
     * @param text Text response sent by the bot.
     * @param displayName Display name of the bot.
     * @return
     */
    public static Message getBotMessage(Image displayPicture, String text, String displayName) {
        Message cur = new Message(displayPicture, text, displayName);
        cur.flip();
        return cur;
    }
}
