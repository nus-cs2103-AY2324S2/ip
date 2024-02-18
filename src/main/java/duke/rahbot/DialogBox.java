package duke.rahbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box in the chat interface of a chatbot application.
 * This class is responsible for creating a dialog box containing a message
 * and an associated image. Dialog boxes can be customized for different speakers
 * in the chat, such as the user or the chatbot itself.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with specified text and image.
     * The DialogBox aligns its content to the top right by default and applies
     * predefined padding and spacing for aesthetic spacing.
     * @param l The label containing the text for the dialog box.
     * @param iv The ImageView containing the image to be displayed alongside the text.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setPreserveRatio(true);
        displayPicture.setFitHeight(100.0); // Consider removing setFitWidth for aspect ratio preservation

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10)); // Add padding around the HBox contents
        this.setSpacing(10); // Add spacing between the elements
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box horizontally, placing the image on the left and text on the right.
     * This method is used to visually differentiate between dialog boxes of different speakers.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox for the user's dialog.
     * The dialog box is not flipped, keeping the default alignment with the image on the right.
     * @param l The label containing the user's text.
     * @param iv The ImageView containing the user's image.
     * @return A DialogBox instance configured for the user's dialog.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Factory method to create a DialogBox for the Duke's (chatbot's) dialog.
     * The dialog box is flipped, placing the image on the left and text on the right,
     * to differentiate the chatbot's messages from the user's messages.
     * @param l The label containing Duke's text.
     * @param iv The ImageView containing Duke's image.
     * @return A DialogBox instance configured for Duke's dialog, with flipped alignment.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

