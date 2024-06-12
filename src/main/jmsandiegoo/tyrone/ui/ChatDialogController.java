package jmsandiegoo.tyrone.ui;

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

import java.io.IOException;
import java.util.Collections;

/**
 * Controller class for the ChatDialog fxml file. It represents the
 * chat dialog messages in the application's gui.
 */
public class ChatDialogController extends HBox {
    @FXML
    private Label message;
    @FXML
    private ImageView displayPicture;

    private ChatDialogController(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatDialogController.class.getResource("/views/ChatDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().remove(1);
        this.message.setText(text);
    }

    private ChatDialogController(String text, Image img) {
        this(text);

        this.displayPicture.setImage(img);
        this.getChildren().add(this.displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a new chat dialog instance containing the current user input.
     * @param text - the message.
     * @param img - profile picture of the user.
     * @return ChatDialogController.
     */
    public static ChatDialogController makeUserChatDialog(String text) {
        ChatDialogController chatDialogController = new ChatDialogController(text);
        chatDialogController.setAlignment(Pos.TOP_RIGHT);
        return chatDialogController;
    }

    /**
     * Returns a new chat dialog instance containing the bot response.
     * The control is flipped so as the display picture is on the left.
     * @param text - the message.
     * @param img - profile picture of the user.
     * @return ChatDialogController.
     */
    public static ChatDialogController makeBotChatDialog(String text, Image img) {
        ChatDialogController chatDialogController = new ChatDialogController(text, img);
        chatDialogController.flip();
        return chatDialogController;
    }
}
