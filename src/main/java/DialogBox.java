import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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

    public DialogBox(String text, Image img, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10; -fx-border-radius: 10;");

        double textWidth = dialog.getBoundsInParent().getWidth();

        if (textWidth > 325.0) {
            dialog.setWrappingWidth(325.0);
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        dialog.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        dialog.setWrappingWidth(330.0);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "#E4FBFF");
    }

    public static DialogBox getTobiasDialog(String text, Image img) {
        var db = new DialogBox(text, img, "#FFA329");
        db.flip();
        return db;
    }
}