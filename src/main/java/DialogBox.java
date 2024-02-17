import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Represents a custom dialog box used in a chat application.
 * It extends JavaFX's HBox class to create a horizontal layout.
 */
public class DialogBox extends HBox {

    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text, image, and user type.
     *
     * @param text    the text content of the dialog box.
     * @param img     the image to be displayed in the dialog box.
     * @param isUser  a boolean indicating whether the dialog box represents a user or not.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            String fxmlPath = isUser ? "/view/UserDialogBox.fxml" : "/view/DukeDialogBox.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle(50, 50, 40);
        dialog.setText(text);
        dialog.setWrappingWidth(330);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    /**
     * Creates and returns a new DialogBox representing a user's dialog.
     *
     * @param text   the text content of the dialog box.
     * @param image  the image to be displayed in the dialog box.
     * @return a DialogBox representing a user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image, true);
    }

    /**
     * Creates and returns a new DialogBox representing Duke's dialog.
     *
     * @param text   the text content of the dialog box.
     * @param image  the image to be displayed in the dialog box.
     * @return a DialogBox representing Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        return new DialogBox(text, image, false);
    }
}
