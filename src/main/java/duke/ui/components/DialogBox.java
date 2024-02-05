package duke.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The DialogBox class creates a dialog box using JavaFX consisting of a display
 * picture and a text label
 *
 * @author Ryan NgWH
 */
public class DialogBox extends HBox {
    /**
     * Text to display in the dialog box
     */
    private Label text;

    /**
     * Picture to display in the dialog box
     */
    private ImageView displayPicture;

    /**
     * Creates a dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     */
    public DialogBox(Label text, ImageView picture) {
        this.text = text;
        this.displayPicture = picture;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a user dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     *
     * @return DialogBox for user input
     */
    public static DialogBox getUserDialog(Label text, ImageView picture) {
        return new DialogBox(text, picture);
    }

    /**
     * Creates a duke dialog box
     *
     * @param text    Text to display in the dialog box
     * @param picture Picture to display in the dialog box
     *
     * @return DialogBox for duke response
     */
    public static DialogBox getDukeDialog(Label text, ImageView picture) {
        var db = new DialogBox(text, picture);
        db.flip();
        return db;
    }

}
