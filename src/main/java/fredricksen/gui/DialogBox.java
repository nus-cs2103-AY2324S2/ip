package fredricksen.gui;

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
import javafx.scene.text.Font;

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
    @FXML
    private Label nameLabel;

    /**
     * Constructs a DialogBox with specified text and image.
     *
     * @param text the text to be displayed in the dialog box.
     * @param img the image to be displayed in the dialog box.
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
        dialog.setPadding(new Insets(10, 0, 0, 10));
        dialog.setAlignment(Pos.CENTER_LEFT);

        dialog.setFont(Font.font("Lobster", 15));
        makeImageCircular();
    }


    /**
     * Applies a circular clip to the ImageView to display the image in a circular shape.
     */
    private void makeImageCircular() {
        double radius = displayPicture.getFitWidth() / 2;
        Circle clip = new Circle(radius, radius, radius);
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

    /**
     * Creates a User dialog box with specified text and image.
     *
     * @param text the text to be shown as user's dialog.
     * @param img the image to be shown as the user.
     * @return a new DialogBox object representing user's dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Fredricksen Chat Bot dialog box with specified text and image.
     * The dialogBox calls .flip() to invert the dialogBox
     * to distinguish it from the user's dialog.
     *
     * @param text the text to be displayed as Adam's dialog.
     * @param img the image to be displayed representing Adam.
     * @return a new DialogBox object representing Adam's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public void configBotBox() {
        this.nameLabel.setText("Fredricksen");
    }

    /**
     * Configures the User dialog box to set the dialog box label to "user"
     * and the position of the nameLabel label to top right of the container
     * it is in.
     */
    public void configUserBox() {
        this.nameLabel.setText("");
        this.nameLabel.setAlignment(Pos.TOP_RIGHT);
        this.dialog.setId("user");
    }
}
