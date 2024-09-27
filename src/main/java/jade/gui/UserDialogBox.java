package jade.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Represents a dialog box in the user interface,
 * containing an ImageView for the user's display picture
 * and a Label for displaying text entered by the user.
 * Some comments are improved using ChatGPT 3.5 using the prompt:
 * "add comments for the following class: {code}"
 */
public class UserDialogBox extends HBox {
    // Path to the FXML file defining the user dialog box
    private static final String FXML_FILE_PATH = "/view/UserDialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    /**
     * Private constructor to create a UserDialogBox instance.
     * @param text The text content of the dialog.
     * @param img The image representing the user's display picture.
     */
    private UserDialogBox(String text, Image img) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_FILE_PATH));
            // Set this instance as the controller for the FXML file
            fxmlLoader.setController(this);
            // Set this instance as the root for the FXML file
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set the text of the dialog Label
        dialog.setText(text);
        // Set the image of the displayPicture ImageView
        displayPicture.setImage(img);
        setImgStyle();
    }

    /**
     * Apply styling to the displayPicture ImageView, creating rounded corners.
     */
    private void setImgStyle() {
        // Create a Rectangle to clip the displayPicture
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(60);
        clip.setArcHeight(60);
        // Apply the clipping to the displayPicture
        displayPicture.setClip(clip);
    }

    /**
     * Static method to create a new UserDialogBox instance with the given text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
     * @return A new UserDialogBox instance.
     */
    public static UserDialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
