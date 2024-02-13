package jayne;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in a GUI application. This class is designed to display a message
 * alongside an image, such as a user or character avatar. The dialog box can be customized
 * to show the image on either the left or right side of the text message.
 *
 * This class extends {@link HBox}, using FXML to define its structure, which includes a label
 * for text and an image view for displaying an avatar. It also features the ability to flip
 * the layout of its content, allowing flexibility in how dialog is presented.
 */
public class DialogBox extends HBox {

    private static final String VIEW_DIALOG_BOX_FXML = "/view/DialogBox.fxml";
    private Label text;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle circle;

    private DialogBox(String text, Image img) {
        assert text != null : "Text parameter cannot be null";
        assert img != null : "Image parameter cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(VIEW_DIALOG_BOX_FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Failed to load FXML for DialogBox";
        }

        dialog.setText(text);
        circle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    @FXML
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getJayneDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
