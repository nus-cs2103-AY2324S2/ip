//package duke.ui;
//
//import java.io.IOException;
//import java.util.Collections;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
//public class DialogBox extends StackPane {
//    @FXML
//    private Label dialogBoxTextLabel;
//    @FXML
//    private Label dialogBoxUserLabel;
//    @FXML
//    private ImageView dialogBoxAvatar;
//    private DialogBox(String text, Image img, String userLabel) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
//            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        dialogBoxUserLabel.setText(userLabel);
//        dialogBoxTextLabel.setText(text);
//    }
//
//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_LEFT);
//    }
//
//    public static DialogBox getUserDialog(String text, Image img, String userName) {
//        return new DialogBox(text, img, userName);
//    }
//
//    public static DialogBox getDukeDialog(String text, Image img) {
//        var db = new DialogBox(text, img, "duke");
//        db.flip();
//        return db;
//    }
//
//}
package duke.ui;

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
import javafx.scene.layout.StackPane;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends StackPane {
    @FXML
    private Label dukeDialogText;
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dukeDialogText.setText(text);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_LEFT);
//    }
//
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
//
//    public static DialogBox getDukeDialog(String text, Image img) {
//        var db = new DialogBox(text, img);
//        db.flip();
//        return db;
//    }
}