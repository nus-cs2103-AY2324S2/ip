package GUI;
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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * This constructor initializes a DialogBox using a specified text and image. The constructor loads the
     * corresponding FXML layout from the "DialogBox.fxml" resource file and sets the controller to the current
     * instance. It then sets the text of the dialog box and the image for display.
     *
     * @param text The text content to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @throws IOException If an error occurs while loading the FXML layout.
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
     * Creates and returns a DialogBox representing user input.
     *
     * This method creates a new DialogBox instance with the specified text content and image to represent
     * user input in the chat interface. The method returns the constructed DialogBox, ready to be displayed.
     *
     * @param text The text content of the user's input.
     * @param img The image to be displayed alongside the user's input.
     * @return A DialogBox representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    /**
     * Creates and returns a DialogBox representing Duke's response.
     *
     * This method creates a new DialogBox instance with the specified text content and image to represent
     * Duke's response in the chat interface. Additionally, it flips the orientation of the DialogBox to visually
     * distinguish between user input and Duke's responses. The method returns the constructed DialogBox,
     * ready to be displayed.
     *
     * @param text The text content of Duke's response.
     * @param img The image to be displayed alongside Duke's response.
     * @return A DialogBox representing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
