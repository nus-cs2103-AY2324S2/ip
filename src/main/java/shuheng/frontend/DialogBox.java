package shuheng.frontend;

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
 * This class represents a dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for dialog box.
     * @param text Label to add to the box.
     * @param img The imageview to add to the box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                MainWindow.class.getResource("/view/DialogBox.fxml"));
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
     * Gets dialog box for the user's perspective.
     * @param l The string.
     * @param iv The image to add.
     * @return A dialog box for users.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets dialog box for shuheng's perspective.
     * @param l The string.
     * @param iv The image to add.
     * @return A dialog box for duke.
     */
    public static DialogBox getShuhengDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
