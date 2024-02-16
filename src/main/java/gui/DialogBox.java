package gui;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * A custom JavaFX control representing a dialog box with an ImageView for the speaker's face and a label for text.
 */
public class DialogBox extends HBox {
    @FXML
    private TextFlow dialogTextFlow;
    @FXML
    private Text dialogText;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text content of the dialog box.
     * @param img  The image to be displayed in the dialog box.
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

        dialogText.setText(text);
        displayPicture.setImage(img);

        // Set a circular clip for the displayPicture
        Circle clipCircle = new Circle();
        // fitHeight and fitWidth are ImageView properties set in DialogBox.fxml
        clipCircle.setRadius(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);

        displayPicture.setClip(clipCircle);
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
     * Creates a DialogBox for user messages.
     *
     * @param text The text content of the user's message.
     * @param img  The image representing the user.
     * @return A DialogBox instance for user messages.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #E8DAEF;");
        return db;
    }

    /**
     * Creates a DialogBox for Ezra's messages, flipping it to display the image on the left.
     *
     * @param text The text content of Ezra's message.
     * @param img  The image representing Ezra.
     * @return A DialogBox instance for Ezra's messages.
     */
    public static DialogBox getEzraDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #D6EAF8;");
        return db;
    }
}
