package pyrite;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Shape profileCircle;
    /**
     * Constructs a DialogBox.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     */
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
        profileCircle.setFill(new ImagePattern(img));
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
     * Returns a DialogBox formatted for the user.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox formatted for Pyrite.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return
     */
    public static DialogBox getPyriteDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
    /**
     * Returns a DialogBox formatted for Pyrite when an error occurs.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return
     */
    public static DialogBox getPyriteErrorDialog(String text, Image img) {
        var db = getPyriteDialog(text, img);
        db.setStyle("-fx-background-color: #FF9999;");
        return db;
    }
}
