package talkingbot.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Message extends HBox {
    private ImageView displayPicture;
    private Label text;
    private Label displayName;

    /**
     * Constructor for the Message class.
     * @param displayPicture Display picture of the sender.
     * @param text Text to be displayed in the message.
     * @param displayName Display name of the sender.
     */
    public Message(ImageView displayPicture, Label text, Label displayName) {
        this.displayPicture = displayPicture;;
        this.text = text;
        this.displayName = displayName;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static Message getUserMessage(ImageView displayPicture, Label text, Label displayName) {
        return new Message(displayPicture, text, displayName);
    }

    public static Message getBotMessage(ImageView displayPicture, Label text, Label displayName) {
        Message cur = new Message(displayPicture, text, displayName);
        cur.flip();
        return cur;
    }
}
