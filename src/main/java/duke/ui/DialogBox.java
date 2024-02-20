package duke.ui;

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
 * Class to log user input or chatbot response.
 * Consists of a Label to represent text information and ImageView to represent the speaker.
 *
 * @@author KohGuanZeh-reused
 * Source: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox component with the respective text and image.
     *
     * @param text Text message to be displayed.
     * @param image Image of sender of message.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box for ImageView to be on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user DialogBox to display user's message.
     *
     * @param text Message of user to display.
     * @param displayImage Display image for user profile
     * @return DialogBox component of user for specified message.
     */
    public static DialogBox getUserDialog(String text, Image displayImage) {
        return new DialogBox(text, displayImage);
    }

    /**
     * Creates a DialogBox to display chatbot's response.
     *
     * @param text Response of chatbot to display.
     * @param displayImage Display image of chatbot.
     * @return DialogBox component of chatbot for specified response.
     */
    public static DialogBox getDukeDialog(String text, Image displayImage) {
        var db = new DialogBox(text, displayImage);
        db.flip();
        return db;
    }
}
