package ui;

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
import javafx.scene.shape.Circle;

/**
 * Controller for ui.DialogBox. Provides the layout for the dialog boxes.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
        displayPicture.setClip(new Circle(40, 40, 40));
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
     * Retrieves the dialog box for the user.
     *
     * @param text Text to be displayed.
     * @param img User profile image.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDb = new DialogBox(text, img);
        userDb.setStyle("-box-color: #258553");
        return userDb;
    }

    /**
     * Retrieves the dialog box for buddy.
     *
     * @param text Text to be displayed.
     * @param img Buddy profile image.
     * @return Buddy dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox buddyDb = new DialogBox(text, img);
        buddyDb.flip();
        buddyDb.setStyle("-box-color: #333abd");
        return buddyDb;
    }
}
