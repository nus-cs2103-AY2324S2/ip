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

    /** Text to be encapsulated within the dialog box. */
    @FXML
    private Text dialog;

    /** Image to be encapsulated within the dialog box. */
    @FXML
    private ImageView displayPicture;

    /** Fxmlloader to render the styling. */
    private FXMLLoader fxmlLoader;

    /**
     * Constructs either a dialogbox for a user or for Aurora.
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
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialogbox for Aurora and the user alternately.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialogbox containing the user's command given to the application.
     *
     * @param text Command given by the user.
     * @param img User's profile image.
     * @return DialogBox containing the command given by the user and the user's profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Returns a dialogbox containing Aurora's response based on the user's command given to the application.
     *
     * @param text Aurora's response to the user's command.
     * @param img Aurora's profile image.
     * @return Dialogbox containing Aurora's response based on the user's command given to the application and
     *         Aurora's profile image.
     */
    public static DialogBox getAuroraResponse(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
