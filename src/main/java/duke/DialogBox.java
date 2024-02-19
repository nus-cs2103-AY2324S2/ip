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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;





/**
 * Represents a dialog box in the user interface.
 * A dialog box consists of a label for displaying text and an image view for displaying an image.
 * This class extends the JavaFX HBox layout container.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog; // Label for displaying text
    @FXML
    private ImageView displayPicture; // Image view for displaying an image

    /**
     * Constructs a dialog box with the given text and image.
     *
     * @param text The text to be displayed in the dialog box.
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

        dialog.setText(text); // Set the text in the dialog box
        displayPicture.setImage(img); // Set the image in the dialog box
    }

    /**
     * Flips the dialog box to align the text to the left.
     * This method reverses the order of children in the HBox layout container.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for displaying user input.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A dialog box containing the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for displaying Duke's response.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     * @return A dialog box containing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); // Flip the dialog box to align the text to the left
        return db;
    }
}
