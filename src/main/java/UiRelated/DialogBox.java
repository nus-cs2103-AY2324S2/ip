package UiRelated;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String xmlPath = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    // TODO: change back to private or package private after packing boxes into a packege
    private DialogBox(String text, String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(path));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */


    public static DialogBox getUserDialog(String text) {
        return new DialogBox("You\n\n" + text, xmlPath);
    }

}

