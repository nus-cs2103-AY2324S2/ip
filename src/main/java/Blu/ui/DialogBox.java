package blu.ui;

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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
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

    private void setTextToRed() {
        dialog.getStyleClass().add("error-text");
    }

    /**
     * Factory method to create a dialog box for the user's input.
     *
     * @param text The user's input to be displayed.
     * @param img  The user's image.
     * @return A DialogBox instance representing the user's input.
    */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }


    /**
     * Creates and returns a dialog box specifically designed for displaying error messages.
     *
     * @param text The error message to be displayed
     * @param img  The Blu's image.
     * @return A DialogBox instance configured to display the error message
    */
    public static DialogBox showErrorMessage(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.setTextToRed();
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Factory method to create a dialog box for Blu's reply.
     *
     * @param text The Blu's reply to be displayed.
     * @param img  The Blu's image.
     * @return A DialogBox instance representing Blu's reply.
    */
    public static DialogBox getBluDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
}
