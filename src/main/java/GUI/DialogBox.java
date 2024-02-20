package GUI;

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


import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


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
    private Color textboxColor;
    private int textboxWidth = 240;

    private DialogBox(String text, Image img, String type) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GUI.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        dialog.setText(text);
        displayPicture.setImage(img);

        if (type.equals("USER")) {
            textboxColor = Color.LIGHTBLUE;
        } else {
            textboxColor = Color.LIGHTPINK;
        }

        textStyle(dialog);
        displayPicStyle(displayPicture);

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
        return new DialogBox(text, img, "USER");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "");
        db.flip();
        return db;
    }


    /**
     * Styles the text of the dialog.
     *
     * @param text The text.
     */
    private void textStyle(Label text) {
        text.setWrapText(true);
        text.setPadding(new Insets(10));
    }

    /**
     * Styles the display picture.
     *
     * @param img The display picture.
     */
    private void displayPicStyle(ImageView img) {
        img.setFitWidth(100.0);
        img.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        img.setClip(clip);
    }


}
