package talktomeorilldie;

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
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label containing text from
 * the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Text cannot be null";
        assert img != null : "Image cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setMinSize(0, 70);

        dialog.setText(text);

        // Create circular mask
        Circle clip = new Circle(50, 35, 35);
        displayPicture.setClip(clip);

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

    /**
     * Gets a dialog box for the user.
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        text += "    ";
        DialogBox dialogbox = new DialogBox(text, img);
        dialogbox.setBackgroundColor("LIGHTBLUE");
        return dialogbox;
    }

    /**
     * Sets the background color of the dialog box.
     * @param color The color to set the background to.
     */
    public void setBackgroundColor(String color) {
        assert color != null : "Background color cannot be null";
        String colorStyle = String.format("-fx-background-color: %s;", color);
        setStyle(colorStyle);
    }

    /**
     * Gets a dialog box for TalkToMeOrIllDie.
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return The dialog box for TalkToMeOrIllDie.
     */
    public static DialogBox gettalktomeorilldieDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackgroundColor("LIGHTPINK");
        return db;
    }
}
