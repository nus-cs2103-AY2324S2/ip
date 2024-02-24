package duke;

import java.io.IOException;
import java.util.Collections;

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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box with specified text and image.
     *
     * @param text The message text to be displayed in the dialog box.
     * @param img The image representing the speaker.
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
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a DialogBox for the user's dialog.
     * This method initializes a DialogBox with the specified text and image,
     * representing the user's part of the conversation.
     * The dialog box is not flipped, indicating it's the user speaking.
     *
     * @param text The text message to be displayed in the user's dialog box.
     * @param img The image representing the user.
     * @return A DialogBox instance configured for displaying the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a DialogBox for Duke's dialog.
     * This method initializes a DialogBox with the specified text and image,
     * representing Duke's response in the conversation.
     * The dialog box is flipped (image on the left, text on the right) to indicate
     * that the response is from Duke, differentiating it from the user's dialog.
     *
     * @param text The text message to be displayed in Duke's dialog box.
     * @param img The image representing Duke.
     * @return A DialogBox instance configured for displaying Duke's dialog, with a flipped layout
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
