package aurora.gui;

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
 * The DialogBox class represents a dialog box which contains either
 * a user input or a response from the bot, along with
 * their corresponding profile pictures.
 * Code adapted and reused from: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class DialogBox extends HBox {

    /**
     * Text to be encapsulated within the dialog box.
     */
    @FXML
    private Text dialog;

    /**
     * Image to be encapsulated within the dialog box.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Fxmlloader to render the styling.
     */
    private FXMLLoader fxmlLoader;

    /**
     * Constructor for a dialogbox. Can construct either a dialogbox for a user or for Aurora based on who is speaking
     * at the moment.
     *
     * @param text Text to be encapsulated within the dialogbox.
     * @param img Image to be encapsulated within the dialogbox.
     * @param isBot True if the dialogbox represents Aurora giving a response, false if the user is the one
     *              giving commands.
     */
    public DialogBox(String text, Image img, boolean isBot) {
        try {
            if (isBot) {
                this.fxmlLoader = new FXMLLoader(Window.class.getResource("/view/DialogBoxAurora.fxml"));
            } else {
                this.fxmlLoader = new FXMLLoader(Window.class.getResource("/view/DialogBoxUser.fxml"));
            }
            this.fxmlLoader.setController(this);
            this.fxmlLoader.setRoot(this);
            this.fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Method to flip the orientation of the text box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Factory method to create a dialogbox based on the user's command given to the application.
     *
     * @param text Command given by the user.
     * @param img User's profile image.
     * @return A DialogBox containing the command given by the user and the user's corresponding profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Factory method to create a dialogbox based on the user's command given to the application.
     *
     * @param text Aurora's response to a command.
     * @param img Aurora's profile image.
     * @return A DialogBox containing the application's response to a command and Aurora's corresponding profile
     * image.
     */
    public static DialogBox getAuroraResponse(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
