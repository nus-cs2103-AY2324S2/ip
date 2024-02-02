import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A custom JavaFX HBox for representing dialog boxes in a chat application.
 * Each dialog box contains a Label for text and an ImageView for display pictures.
 * The dialog box can be customized with a background color.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with a given text, display picture, and background color.
     *
     * @param l      The Label containing the text to be displayed.
     * @param iv     The ImageView containing the display picture to be shown.
     * @param color  The background color of the dialog box.
     */
    public DialogBox(Label l, ImageView iv, Color color) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Add padding between each DialogBox
        this.setPadding(new Insets(10));

        // Add padding between each ImageView and its Label
        HBox.setMargin(text, new Insets(0, 10, 0, 10)); // Adjust the insets as needed

        // Clip the ImageView into a circle
        displayPicture.setClip(new javafx.scene.shape.Circle(50.0, 50.0, 50.0));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        // Add background color to each dialog box
        setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Flips the alignment of the dialog box to make it suitable for the opposite speaker.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a DialogBox for the user with a default background color.
     *
     * @param l  The Label containing the user's text.
     * @param iv The ImageView containing the user's display picture.
     * @return A DialogBox for the user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, Color.BEIGE);
    }

    /**
     * Creates a DialogBox for Duke (bot) with a default background color and flipped alignment.
     *
     * @param l  The Label containing Duke's text.
     * @param iv The ImageView containing Duke's display picture.
     * @return A DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, Color.LIGHTGRAY);
        db.flip();
        return db;
    }
}
