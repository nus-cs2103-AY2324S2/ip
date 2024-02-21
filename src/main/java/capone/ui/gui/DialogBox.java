package capone.ui.gui;

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
import javafx.scene.text.Text;

/**
 * Represents a custom control using FXML.
 * This control is used to display a dialog box consisting of an ImageView to
 * represent the user/bot image and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** The path to the default user image used for the GUI. */
    private static final String USER_IMG_PATH = "/images/DaUser.png";

    /** The path to the default Capone image used for the GUI. */
    private static final String CAPONE_IMG_PATH = "/images/DaCapone.png";

    /** The label displaying the text in the dialog box. */
    @FXML
    private Text dialog;

    /** The ImageView displaying the user/bot image in the dialog box. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the given text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
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
     * Gets the path to the user image used for the GUI.
     *
     * @return The path to the user image.
     */
    protected static String getUserImgPath() {
        return DialogBox.USER_IMG_PATH;
    }

    /**
     * Gets the path to the Capone image used for the GUI.
     *
     * @return The path to the Capone image.
     */
    protected static String getCaponeImgPath() {
        return DialogBox.CAPONE_IMG_PATH;
    }

    /**
     * Flips the dialog box for the bot reply.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox representing user input.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return The DialogBox representing user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox representing Capone's response.
     *
     * @param text The text to display in the dialog box.
     * @param img The image to display in the dialog box.
     * @return The DialogBox representing Capone's response.
     */
    public static DialogBox getCaponeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
