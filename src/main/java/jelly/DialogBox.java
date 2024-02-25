package jelly;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label time;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String fxml) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxml));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm a");
        time.setText(LocalDateTime.now().format(formatter));
        time.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);

        final Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(20); // Adjust the corner radius here
        clip.setArcHeight(20); // Adjust the corner radius here
        displayPicture.setClip(clip);

        displayPicture.fitWidthProperty().addListener((obs, oldVal, newVal) -> {
            clip.setWidth(newVal.doubleValue());
        });
        displayPicture.fitHeightProperty().addListener((obs, oldVal, newVal) -> {
            clip.setHeight(newVal.doubleValue());
        });
    }

    public static DialogBox getUserDialog(String text, Image img) {

        return new DialogBox(text, img, "/view/DialogBox.fxml");
    }

    public static DialogBox getJellyDialog(String text, Image img) {

        return new DialogBox(text, img, "/view/DialogBoxReverse.fxml");
    }
}

