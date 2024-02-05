package iggly.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
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

    private FXMLLoader fxmlLoader;

    /**
     * Private constructor for creating a custom dialog box. It initializes the
     * dialog box with the specified text, image, and user status.
     *
     * @param text     The text content to be displayed in the dialog box.
     * @param img      The image to be displayed in the dialog box.
     * @param isUser   A boolean flag indicating whether the dialog box belongs to the user.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            if (isUser) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox2.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle(50, 50, 40);
        dialog.setText(text);
        dialog.setWrappingWidth(330);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    /**
     * Creates and returns a dialog box representing a user's message with the
     * specified text content and image.
     *
     * @param text The text content to be displayed in the user's dialog box.
     * @param img  The image to be associated with the user in the dialog box.
     * @return A new instance of DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates and returns a dialog box representing Iggly's message with the
     * specified text content and image.
     *
     * @param text The text content to be displayed in Iggly's dialog box.
     * @param img  The image to be associated with Iggly in the dialog box.
     * @return A new instance of DialogBox representing Iggly's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}
