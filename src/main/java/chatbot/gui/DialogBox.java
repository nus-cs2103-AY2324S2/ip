package chatbot.gui;

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
 * Encapsulates a custom JavaFX component used to display dialogs in a chat interface.
 * It extends the HBox class and includes a Label to display text and an ImageView to display
 * the user or the chatbot's display picture.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and image.
     *
     * @param text The text to be displayed in the DialogBox.
     * @param img  The image to be displayed in the DialogBox.
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

        Circle clip = new Circle(50);
        clip.centerXProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(displayPicture.fitHeightProperty().divide(2));
        displayPicture.setClip(clip);

        dialog.setPadding(new Insets(10, 10, 10, 10));
        this.setStyle("-fx-background-color: #D9E8D8;");

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
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a new DialogBox representing the user's dialog with the specified text and image.
     *
     * @param text The text of the user's message.
     * @param img  The image representing the user.
     * @return A new DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a new DialogBox representing the chatbot's dialog with the specified text and image.
     * Flips the DialogBox.
     *
     * @param text The text of the chatbot's message.
     * @param img  The image representing the chatbot.
     * @return A new DialogBox representing the chatbot's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}