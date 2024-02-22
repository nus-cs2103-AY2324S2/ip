package ciara.ui.gui;

import java.io.IOException;
import java.util.Collections;

import ciara.exceptions.StorageException;
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
import javafx.scene.shape.Circle;

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
    private HBox dialogContainer;
    @FXML
    private Circle displayCircle;

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
        displayCircle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());

        Collections.reverse(tmp);

        getChildren().setAll(tmp);
        dialogContainer.setAlignment(Pos.CENTER_LEFT);
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
        var dialogBox = new DialogBox(text, picture);

        return dialogBox;
    }

    /**
     * Creates a ciara dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     *
     * @return DialogBox for ciara response
     */
    public static DialogBox getCiaraDialog(String text, Image picture) throws StorageException {
        var dialogBox = new DialogBox(text, picture);
        dialogBox.flip();

        return dialogBox;
    }
}
