package yarr.javafx;

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
import javafx.scene.shape.Circle;

/**
 * A custom control using FXML. This control represents a dialog box consisting of an ImageView to represent the
 * speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    /**
     * The label to display the text in the dialog box.
     */
    @FXML
    private Label dialog;
    /**
     * The image view to display the image in the dialog box.
     */
    @FXML
    private ImageView displayPicture;
    /**
     * The circle to clip the image view to a circle.
     */
    @FXML
    private Circle clip;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text a String representing the text to be displayed in the dialog box
     * @param img an Image object representing the image to be displayed in the dialog box
     */
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
        displayPicture.setClip(clip);
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
     * Returns a DialogBox object representing the user's input with the specified text and image.
     *
     * @param text a String representing the text to be displayed in the dialog box
     * @param img an Image object representing the image to be displayed in the dialog box
     * @return a DialogBox object representing a user dialog with the specified text and image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #38536b; -fx-background-radius: 8; -fx-text-fill: #ffffff;");
        return db;
    }

    /**
     * Returns a DialogBox object representing the Bot's dialog with the specified text and image.
     *
     * @param text a String representing the text to be displayed in the dialog box
     * @param img an Image object representing the image to be displayed in the dialog box
     * @return a DialogBox object representing a bot dialog with the specified text and image
     */
    public static DialogBox getYarrDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle("-fx-background-color: #a99c6a; -fx-background-radius: 8; -fx-text-fill: #000000;");
        return db;
    }
}
