package jiayou.view;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    private DialogBox(String text, Image img, boolean isUser) {
        try {
            if (isUser) {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxForUser.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
                VBox.setMargin(this, new Insets(0, 0, 0, 100));
            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxForJiayou.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
            }
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
        return new DialogBox(text, img, true);
    }

    public static DialogBox getJiayouDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
