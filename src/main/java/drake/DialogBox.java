package drake;

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
 * DialogBox is a custom control used to display a message in a chat bubble format,
 * typically in chat applications. It combines an {@link ImageView} to represent the speaker's avatar
 * and a {@link Label} to contain the message text from the speaker. This class supports the visual
 * representation of dialogues with customization for different speakers by flipping the dialog box orientation.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox instance with specified text and image.
     *
     * @param text the message text to be displayed in the dialog box.
     * @param img the image to be displayed as the speaker's avatar.
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
        dialog.setWrapText(true);
        dialog.setMaxWidth(250);
    }

    /**
     * Flips the dialog box horizontally to change the orientation of the ImageView and text.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox instance for the user's dialog. The avatar is placed on the right side by default.
     *
     * @param text the user's message text.
     * @param img the user's avatar image.
     * @return a DialogBox instance representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox instance for Drake's dialog. This method also flips the dialog box to place the avatar
     * on the left side, visually distinguishing Drake's dialog from the user's.
     *
     * @param text Duke's message text.
     * @param img Duke's avatar image.
     * @return a flipped DialogBox instance representing Duke's dialog.
     */
    public static DialogBox getDrakeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
