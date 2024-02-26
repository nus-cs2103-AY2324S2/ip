package aegis;

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

import java.util.Collections;

/**
 * The code for this class was taken from the tutorial for implementing JavaFX @ https://se-education.org/guides/tutorials/javaFx.html
 *
 * DialogBox class represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker. This speaker can be either the user or the Aegis Assistant program.
 *
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.BOTTOM_LEFT);
    }

    /**
     * Returns a DialogBox object representing the user.
     *
     * @param text String to be displayed as user input.
     * @param img Image to be displayed representing the user.
     * @return DialogBox representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.BOTTOM_RIGHT);
        db.setSpacing(5);
        return db;
    }

    /**
     * Returns a DialogBox object representing the response by the Aegis assistant program.
     *
     * @param text String to be displayed as response by program.
     * @param img Image to be displayed representing the Aegis assistant program.
     * @return DialogBox representing the Aegis assistant program's response.
     */
    public static DialogBox getAegisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSpacing(5);
        return db;
    }
}
