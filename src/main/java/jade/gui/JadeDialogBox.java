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
 * This class represents a custom control for a dialog box used in the Jade application.
 * It consists of an ImageView to represent the bot's display picture, and a Label
 * containing text from the bot.
 * Some comments are improved using ChatGPT 3.5 using the prompt:
 * "add comments for the following class: {code}"
 */
public class JadeDialogBox extends HBox {
    // Path to the FXML file defining the appearance of the dialog box
    private static final String FXML_FILE_PATH = "/view/JadeDialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor to create a JadeDialogBox instance.
     * @param text The text content of the dialog.
     * @param img The image representing the bot's display picture.
     */
    private JadeDialogBox(String text, Image img) {
        try {
            // Load the FXML file and set this instance as the controller
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_FILE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the text content and display picture
        dialog.setText(text);
        displayPicture.setImage(img);

        // Apply styling to the display picture
        setImgStyle();
    }

    /**
     * Applies styling to the display picture.
     * This method sets a rounded rectangle clip around the image to give it a circular appearance.
     */
    private void setImgStyle() {
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(60); // Adjust arc width to control the roundness of corners
        clip.setArcHeight(60); // Adjust arc height to control the roundness of corners
        displayPicture.setClip(clip);
    }

    /**
     * Static factory method to create a JadeDialogBox instance with specified text and image.
     * @param text The text content of the dialog.
     * @param img The image representing the bot's display picture.
     * @return A new instance of JadeDialogBox.
     */
    public static JadeDialogBox getDialog(String text, Image img) {
        return new JadeDialogBox(text, img);
    }
}
