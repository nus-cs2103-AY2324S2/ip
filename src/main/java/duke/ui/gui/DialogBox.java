package duke.ui.gui;

import java.io.IOException;
import java.util.Collections;

import duke.exceptions.StorageException;
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
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 *
 * @author Ryan NgWH
 */
public class DialogBox extends HBox {
    // Elements of the dialogbox
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) throws StorageException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);

            fxmlLoader.load();
        } catch (IOException e) {
            throw new StorageException("Could not load FXML file");
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());

        Collections.reverse(tmp);

        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     *
     * @return DialogBox for user input
     */
    public static DialogBox getUserDialog(String text, Image picture) throws StorageException {
        return new DialogBox(text, picture);
    }

    /**
     * Creates a duke dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     *
     * @return DialogBox for duke response
     */
    public static DialogBox getDukeDialog(String text, Image picture) throws StorageException {
        var dialogBox = new DialogBox(text, picture);
        dialogBox.flip();

        return dialogBox;
    }
}
