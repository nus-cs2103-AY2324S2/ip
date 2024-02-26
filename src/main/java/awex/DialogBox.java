package awex;

import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
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

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader;
            if (isUser) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/AwexBox.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle(42, 42, 42);
        dialog.setText(text);
        dialog.setWrappingWidth(330);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    //@@author {alex-setyawan}-reused
    //adapted from https://github.com/nus-cs2103-AY2324S2/ip/pull/4/files
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getAwexDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
    //@@author
}