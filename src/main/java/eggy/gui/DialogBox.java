package eggy.gui;

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
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param text The text to be displayed.
     * @param img The image of the speaker.
     * @param isException Whether the text is an exception message.
     */
    private DialogBox(String text, Image img, boolean isException) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSpacing(5);
        dialog.setText(text);
        dialog.setFont(Font.font("Arial", 14));
        if (isException) {
            dialog.setStyle("-fx-background-color: #ff3b2f");
        } else {
            dialog.setStyle("-fx-background-color: #007ade");
        }
        displayPicture.setImage(img);
        setHgrow(dialog, Priority.ALWAYS);
        setHgrow(displayPicture, Priority.ALWAYS);
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
     * Returns a user dialog box.
     *
     * @param text The text to be displayed.
     * @param img The image of the user.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert img != null : "User image should not be null";
        return new DialogBox(text, img, false);
    }

    /**
     * Returns an Eggy dialog box.
     *
     * @param text The text to be displayed.
     * @param img The image of Eggy.
     * @param isException Whether the text is an exception message.
     * @return The dialog box for Eggy.
     */
    public static DialogBox getEggyDialog(String text, Image img, boolean isException) {
        assert img != null : "Eggy image should not be null";
        var db = new DialogBox(text, img, isException);
        db.flip();
        return db;
    }
}
