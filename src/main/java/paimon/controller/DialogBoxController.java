package paimon.controller;


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
import javafx.scene.layout.VBox;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBoxController extends VBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBoxController(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatController.class.getResource("/view/DialogBox.fxml"));
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
        // Find the HBox within the children of this VBox
        for (Node node : this.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;

                // Reverse the children of the HBox
                ObservableList<Node> tmp = FXCollections.observableArrayList(hbox.getChildren());
                Collections.reverse(tmp);
                hbox.getChildren().setAll(tmp);
                hbox.setAlignment(Pos.TOP_LEFT);

                break;
            }
        }
    }

    public static DialogBoxController getUserDialog(String text, Image img) {
        return new DialogBoxController(text, img);
    }

    public static DialogBoxController getPaimonDialog(String text, Image img) {
        var db = new DialogBoxController(text, img);
        db.flip();
        return db;
    }
}
