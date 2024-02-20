package shirmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Custom dialog box for displaying messages with images in a JavaFX application.
 * The dialog box can be oriented for user or Shirmin, with text and image alignment.
 */
public class DialogBox extends HBox {
    final int imageWidth = 100;
    final int imageHeight = 100;

    /**
     * Creates a DialogBox with the specified label and image.
     *
     * @param l The label to display in the dialog box.
     * @param iv The image to display in the dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        iv.setFitWidth(imageWidth);
        iv.setFitHeight(imageHeight);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    /**
     * Flips the dialog box to switch the alignment of text and image.
     * The ImageView will be on the left, and the text will be on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets a DialogBox configured for user input.
     *
     * @param l The label to display in the dialog box.
     * @param iv The image to display in the dialog box.
     * @return DialogBox configured for user input.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets a DialogBox configured for Shirmin's messages.
     *
     * @param l The label to display in the dialog box.
     * @param iv The image to display in the dialog box.
     * @return DialogBox configured for Shirmin's messages.
     */
    public static DialogBox getShirminDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}