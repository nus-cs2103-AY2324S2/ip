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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Polygon arrow;
    @FXML
    private Circle displayPicture;

    /**
     * Creates a DialogBox for user and bot interactions.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
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
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        this.arrow.setRotate(180);
        setAlignment(Pos.CENTER_LEFT);
        dialog.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox that is right justified.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);

    }

    /**
     * Creates a DialogBox that is left justified.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
