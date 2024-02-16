package com.example.artemis;

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
 * Represents a custom control for a dialog box using FXML.
 * This control consists of an ImageView to display the speaker's face and a Label for the text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image representing the speaker's face.
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Text cannot be null";
        assert img != null : "Image cannot be null";

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
     * Creates a DialogBox for the user with the specified text and image.
     *
     * @param text The text to be displayed in the user's dialog box.
     * @param img  The image representing the user's face.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Artemis with the specified text and image, flipping the layout.
     *
     * @param text The text to be displayed in Artemis's dialog box.
     * @param img  The image representing Artemis's face.
     * @return A DialogBox representing Artemis's dialog.
     */
    public static DialogBox getArtemisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
