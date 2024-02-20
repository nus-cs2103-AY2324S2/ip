package duke.main;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Represents a dialog box in the GUI.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the given text and image.
     *
     * @param text Text content of the dialog box.
     * @param img Image to be displayed in the dialog box.
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
        setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);

        StackPane dialogTextPane = (StackPane) this.getChildren().get(0);
        dialogTextPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), null)));
        Insets padding = new Insets(0, 20, 0, 20); // Adjust the values as needed
        dialogTextPane.setPadding(padding);
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
     * Creates and returns a user dialog box with the given text and image.
     *
     * @param text Text content of the user dialog.
     * @param img Image to be displayed for the user dialog.
     * @return DialogBox representing the user dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a flipped dialog box with the given text and image.
     *
     * @param text Text content of dialog.
     * @param img Image to be displayed for dialog.
     * @return DialogBox representing dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}