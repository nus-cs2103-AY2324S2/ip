package ui;


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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        Circle clip = new Circle(49.5, 49.5, 49.5);
        displayPicture.setClip(clip);
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
     * Getter for the DialogBox object for the user's input.
     *
     * @param text the user's input.
     * @param img the user's profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert img != null : "User image not set!";
        return new DialogBox(text, img);
    }

    /**
     * Getter for the DialogBox object for the Chatbot's response.
     *
     * @param text the Chatbot's input.
     * @param img the Chatbot's profile image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert img != null : "DukeBot image not set!";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
