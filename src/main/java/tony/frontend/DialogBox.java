package tony.frontend;

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
 * DialogBox Utility for Gui
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    /**
     * Constructor for DialogBox
     * @param l String of the dialog.
     * @param iv Image for the DialogBox.
     */
    public DialogBox(String l, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l);
        displayPicture.setImage(iv);
    }

    /**
     * Creates Dialog Box for User
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates Dialog Box for Tony
     */
    public static DialogBox getTonyDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
