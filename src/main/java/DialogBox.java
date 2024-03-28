import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

    /**
     * Creates a dialog box with specified text and image.
     * The dialog box can be styled differently based on the speaker type.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed as the speaker's face in the dialog box.
     * @param isTaskYapper A boolean flag to indicate if the dialog box is for a TaskYapper (true) or a user (false).
     */
    private DialogBox(String text, Image img, boolean isTaskYapper) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setPadding(new Insets(0, 10, 0, 10));
        displayPicture.setImage(img);
        if (isTaskYapper) {
            this.setStyle("-fx-background-color: #cfcfcf; -fx-background-radius: 10; -fx-padding: 10;");
        }
    }

    /**
     * Flips the dialog box horizontally, placing the ImageView to the left and the text to the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text The text for the dialog box.
     * @param img The image to be used as the user's avatar.
     * @return A DialogBox instance configured for a user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Creates a dialog box for a TaskYapper with the specified text and image.
     * This dialog box is flipped to indicate the TaskYapper's speech.
     *
     * @param text The text for the dialog box.
     * @param img The image to be used as the TaskYapper's avatar.
     * @return A DialogBox instance configured for a TaskYapper, flipped to indicate its speech direction.
     */
    public static DialogBox getTaskYapperDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }

    /**
     * Sets the CSS style for the dialog text.
     *
     * @param style The CSS style string to apply to the dialog text.
     */
    public void setDialogStyle(String style) {
        dialog.setStyle(style);
    }
}
