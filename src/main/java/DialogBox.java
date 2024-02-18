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
import javafx.scene.shape.Circle;

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

    private DialogBox(String text, Image img, String backgroundColor, String textColor,
                      String fontFamily, int fontSize) {
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
        setStyle("-fx-background-color: " + backgroundColor + ";");
        dialog.setStyle("-fx-text-fill: " + textColor + "; -fx-font-family: "
                + fontFamily + "; -fx-font-size: " + fontSize + "px;");

        setSpacing(10); // Adjust spacing as per requirement

        // Set padding around the dialog box
        setPadding(new Insets(10));

        Circle clip = new Circle();
        clip.setRadius(displayPicture.getFitWidth() / 2.3); // Radius should be half of the ImageView width
        clip.setCenterX(displayPicture.getFitWidth() / 2); // Center X coordinate of the circle
        clip.setCenterY(displayPicture.getFitHeight() / 2); // Center Y coordinate of the circle
        displayPicture.setClip(clip);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "lightgray", "black", "Calibri", 14);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "lightblue",
                "black", "Calibri", 14);
        db.flip();
        return db;
    }
}
