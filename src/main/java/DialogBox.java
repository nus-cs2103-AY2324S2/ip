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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Collections;

/**
 * Custom JavaFX control representing a dialog box in the chat UI.
 * Extends HBox for horizontal layout.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for creating a new DialogBox.
     *
     * @param text The text to be displayed in the dialog.
     * @param img The image to be displayed as the profile picture.
     */
    private DialogBox(String text, Image img, boolean isUserDialog, boolean isError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setFont(new Font("Cambria", 14));
        displayPicture.setImage(img);
        dialog.setPadding(new Insets(5, 10, 5, 10));
        if (isUserDialog) {
            // Set background color for user dialog
            this.setStyle("-fx-background-color: #ADD8E6;"); // Light Blue
        } else {
            // Set background color for Lery dialog
            this.setStyle("-fx-background-color: #F0F8FF;"); // Alice Blue
        }
        if (isError) {
            dialog.setTextFill(Color.color(1, 0, 0));
        }
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
     * Creates a new DialogBox for representing a user's message in the chat.
     *
     * @param text The text of the user's message.
     * @param img The profile picture of the user.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true, false);
    }

    /**
     * Creates a new DialogBox for representing Lery's message in the chat.
     * This method flips the dialog box to switch the positions of the text and image.
     *
     * @param text The text of Lery's message.
     * @param img The profile picture of Lery.
     * @param isError A boolean indicating whether the message is an error.
     * @return A DialogBox representing Lery's message.
     */
    public static DialogBox getLeryDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img, false, isError);
        db.flip();
        return db;
    }


}
