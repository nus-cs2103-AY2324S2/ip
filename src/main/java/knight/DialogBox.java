package knight;

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
 * Represents a dialog box to display the user's input and the knight's response.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a dialog box.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
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

    /**
     * Gets a dialog box for the user (on the right side of the screen).
     *
     * @param s The text to be displayed in the dialog box.
     * @param im The image to be displayed in the dialog box.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(String s, Image im) {
        return new DialogBox(s, im);
    }

    /**
     * Gets a dialog box for the knight (on the left side of the screen).
     *
     * @param s The text to be displayed in the dialog box.
     * @param im The image to be displayed in the dialog box.
     * @return The dialog box for the knight.
     */
    public static DialogBox getKnightDialog(String s, Image im) {
        var db = new DialogBox(s, im);
        db.flip();
        return db;
    }

}
