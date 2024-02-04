package saopig;

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
 * Represents the dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private Label timeLabel; // Added timeLabel

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, String time, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        dialog.setText(text);
        timeLabel.setText("Time: " + time); // Added timeLabel
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    public static DialogBox getUserDialog(String text, String time, Image img) {
        return new DialogBox(text, time, img);
    }
    public static DialogBox getSaopigDialog(String text, String time, Image img) {
        var db = new DialogBox(text, time, img);
        db.flip();
        return db;
    }
}
