package gui;


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


/**
 * A class to handle the Diglox Box GUI.
 */
public class DialogBox extends HBox {

    /** A label to show text. */
    @FXML
    private Label dialog;

    /** An imageView to show image. */
    @FXML
    private ImageView displayPicture;

    /**
     * A constructor to create a DialogBox for label and image.
     *
     * @param text The text to print.
     * @param image The image to show.
     */
    public DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a new dialog box to represent the user.
     *
     * @param text The text that the user sent.
     * @param image The image of the user.
     * @return a Dialog box that represent the user.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a new dialog box to represent Andelu bot.
     *
     * @param text The response by Andelu bot.
     * @param image The image of Andelu bot.
     * @return a Dialog box that represent Andelu bot.
     */
    public static DialogBox getAndeluDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
