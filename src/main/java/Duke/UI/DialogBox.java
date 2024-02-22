package Duke.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code DialogBox} class represents a graphical user interface element for displaying dialog messages.
 * It extends the {@link HBox} class and includes a label for text and an image view for displaying a display picture.
 */
public class DialogBox extends HBox {

    /**
     * The label for displaying text content in the dialog box.
     */
    @FXML
    private Label text;

    /**
     * The image view for displaying a display picture in the dialog box.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new {@code DialogBox} with the specified label and image view.
     *
     * @param l The label for displaying text content.
     * @param iv The image view for displaying a display picture.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the alignment of the dialog box, changing it to be aligned to the top left.
     * This is used to represent messages from the user.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a new {@code DialogBox} representing a user dialog.
     *
     * @param l The label for displaying text content.
     * @param iv The image view for displaying a display picture.
     * @return A new {@code DialogBox} representing a user dialog.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates a new {@code DialogBox} representing a Duke dialog.
     * Flips the alignment to indicate messages from Duke.
     *
     * @param l The label for displaying text content.
     * @param iv The image view for displaying a display picture.
     * @return A new {@code DialogBox} representing a Duke dialog.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
