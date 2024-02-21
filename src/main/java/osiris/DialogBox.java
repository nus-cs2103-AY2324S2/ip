package osiris;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Custom control representing a dialog box in the chat interface.
 * Each dialog box consists of an ImageView to represent the speaker's face and a Label containing text.
 */
public class DialogBox extends HBox {

    /**
     * Represents a single dialog entry in the conversation.
     * It includes text and an image to display either user or chatBot messages.
     */
    @FXML
    private Text dialog;

    /**
     * The ImageView component used to display the profile picture associated with the dialog entry.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and image.
     *
     * @param text The text content to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrappingWidth(330);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box to represent a message from the user.
     * The ImageView is placed on the left, and the text is placed on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a DialogBox representing a message from the user.
     *
     * @param text The text content of the user's message.
     * @param img  The image to represent the user.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a DialogBox representing a message from Osiris.
     * The dialog box is flipped to display Osiris's message on the left.
     *
     * @param text The text content of Osiris's message.
     * @param img  The image to represent Osiris.
     * @return A DialogBox representing Osiris's message.
     */
    public static DialogBox getOsirisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
