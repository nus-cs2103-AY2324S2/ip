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
 * This control represents a dialog box consisting of an ImageView to represent the user
 * and a label containing text from user.
 */
public class UserDialogBox extends HBox {
    private static final String FXML_FILE_PATH = "/view/UserDialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_FILE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        setImgStyle();
    }
    /**
     * Styles the user image and jade image which will appear in dialog boxes.
     */
    private void setImgStyle() {
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(60);
        clip.setArcHeight(60);
        displayPicture.setClip(clip);
    }

    public static UserDialogBox getDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
