package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String[] beeStyleClasses = {"bee-dialog-background"};
    private static final String[] userStyleClasses = {"user-dialog-background"};
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;



    private DialogBox(String text, Image img, String... styleClass) {
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
        dialog.getStyleClass().addAll(styleClass);
        dialog.setPrefWidth(285);

        // format the image
        displayPicture.setFitHeight(75);
        displayPicture.setFitWidth(75);
        displayPicture.setClip(null);
        displayPicture.setEffect(new DropShadow(10, Color.BLACK));

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
     * User's dialog box, show the image and user input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, userStyleClasses);
    }

    /**
     * Bee's dialog box, show the image and user input
     */
    public static DialogBox getBeeDialog(String text, Image img) {
        var db = new DialogBox(text, img, beeStyleClasses);
        db.flip();
        return db;
    }
}
