package judy.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * A class representing a dialog box in the Judy task management application.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for creating a DialogBox instance with the specified text, image, and user indicator.
     *
     * @param text    The text message to be displayed in the dialog box.
     * @param img     The image to be displayed in the dialog box.
     * @param isUser  A boolean indicating whether the dialog box represents a user message.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            String fxmlPath = isUser ? "/view/UserDialogBox.fxml" : "/view/DialogBox.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Rectangle clip = new Rectangle(200, 200);
        dialog.setText(text);
        //dialog.setWrappingWidth(300);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    /**
     * Static method to create a DialogBox instance representing a user's message.
     *
     * @param text The text message input by user.
     * @param img  The image represents user.
     * @return A DialogBox instance representing a user message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Static method to create a DialogBox instance representing a message from Judy.
     *
     * @param text The text message to be displayed in Judy's dialog box.
     * @param img  The image to be displayed in Judy's dialog box.
     * @return A DialogBox instance representing a message from Judy.
     */
    public static DialogBox getJudyDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}

