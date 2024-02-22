package alpa.main;

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
import javafx.scene.shape.Circle;

/**
 * A custom DialogBox control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox with the specified text and image.
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
        makeImageRound(displayPicture);
    }

    /**
     * Makes the specified ImageView round.
     *
     * @param imageView The ImageView to be made round.
     */
    private void makeImageRound(ImageView imageView) {
        Circle clip = new Circle(imageView.getFitWidth() / 2,
                                imageView.getFitHeight() / 2,
                                imageView.getFitWidth() / 2);
        imageView.setClip(clip);
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
     * Represents a dialog box that displays a message from the user.
     *
     * @param text The message from the user.
     * @param img The user's profile picture.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("user-dialog");
        return db;
    }

    /**
     * Represents a dialog box that displays a message from Alpa.
     *
     * @param text The message from Alpa.
     * @param img Alpa's profile picture.
     * @return A DialogBox representing Alpa's message.
     */
    public static DialogBox getAlpaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.getStyleClass().add("alpa-dialog");
        return db;
    }

    /**
     * Represents a dialog box that displays an error message from Alpa.
     *
     * @param text The error message from Alpa.
     * @param img Alpa's profile picture.
     * @return A DialogBox representing Alpa's error message.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("error-dialog");
        return db;
    }
}
