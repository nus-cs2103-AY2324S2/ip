package main.GUi;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String filePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(filePath));
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
     * Creates a DialogBox that reflects the user command
     *
     * @param text the text input of user
     * @param img image of user
     * @return A DialogBox instance with the specific user dialog box fxml
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/UserDialogBox.fxml");
    }

    /**
     * Creates a DialogBox that reflects the response command by Poe
     *
     * @param text the text output response according the input
     * @param img image of Poe
     * @return A DialogBox instance with Poe dialog box fxml
     */
    public static DialogBox getPoeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/PoeDialogBox.fxml");
        db.flip();
        return db;
    }
}
