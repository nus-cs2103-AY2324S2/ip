import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        Text t = (Text) getChildren().get(0);
        t.setTextAlignment(TextAlignment.LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getUncleBobDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackgroundColor("#9FE2BF");
        return db;
    }

    /**
     * Sets the background color of the DialogBox.
     *
     * @param hexCode The hexadecimal color code.
     */
    private void setBackgroundColor(String hexCode) {
        // Change background color to #79e8b2
        this.dialog.setStyle(this.dialog.getStyle().replaceFirst("-fx-background-color: #(?:[A-Fa-f0-9]{3}){1,2}\\b;",
                "-fx-background-color: " + hexCode + ";"));
    }
}
