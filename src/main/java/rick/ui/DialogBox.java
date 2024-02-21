package rick.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
    private Label dialog = new Label();
    @FXML
    private ImageView displayPicture = new ImageView();


    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            Parent root = fxmlLoader.load();
            dialog.setText(text);
            dialog.setWrapText(true);
            displayPicture.setImage(img);
            getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Creates a user dialog
     * @param text the text in rick dialog
     * @param img text image
     * @return A dialog to be displayed
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox user = new DialogBox(text, img);
        user.flip();
        return user;
    }

    /**
     * Creates a rick dialog
     * @param text the text in rick dialog
     * @param img text image
     * @return A dialog to be displayed
     */
    public static DialogBox getRickDialog(String text, Image img) {
        DialogBox rick = new DialogBox(text, img);
        return rick;
    }
}
