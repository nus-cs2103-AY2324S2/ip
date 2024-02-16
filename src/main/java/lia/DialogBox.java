package lia;

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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getLiaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackgroundColor("#e1e1e3");
        db.setFontColor("#000000");
        return db;
    }

    /**
     * Changes the background color of the DialogBox.
     *
     * @param colorHexCode The color code in hexadecimal.
     */
    private void setBackgroundColor(String colorHexCode) {
        this.dialog.setStyle(this.dialog.getStyle().replaceFirst("-fx-background-color: #(?:[A-Fa-f0-9]{3}){1,2}\\b;",
                "-fx-background-color: " + colorHexCode + ";"));
    }

    /**
     * Changes the font color of the text within the DialogBox.
     *
     * @param colorHexCode The color code in hexadecimal.
     */
    private void setFontColor(String colorHexCode) {
        String existingStyle = this.dialog.getStyle();

        existingStyle = existingStyle.replaceAll("-fx-text-fill:[^;]*;", "");
        this.dialog.setStyle(existingStyle + "-fx-text-fill: " + colorHexCode + ";");
    }
}
