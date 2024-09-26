package echo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified label, image view, and user status.
     *
     * @param l      The label to be displayed in the dialog box.
     * @param iv     The image view representing the display picture in the dialog box.
     * @param isUser {@code true} if the dialog box is for the user; {@code false} for the bot.
     */
    public DialogBox(Label l, ImageView iv, boolean isUser) {
        assert l != null : "Label should not be null";
        assert iv != null : "ImageView should not be null";
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setMaxHeight(Double.MAX_VALUE);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(isUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);
        this.getChildren().add(isUser ? userDialogBox() : botDialogBox());
    }

    /**
     * Creates an HBox for user dialog box representation.
     *
     * @return The HBox representing the user dialog box.
     */
    private HBox userDialogBox() {
        HBox box = new HBox(text, displayPicture);
        box.setAlignment(Pos.TOP_RIGHT);
        box.setStyle("-fx-background-color: #caebca; -fx-padding: 10; " +
                "-fx-background-radius: 50; -fx-border-radius: 50; " +
                "-fx-border-color: #ffffff; -fx-border-width: 1;");
        return box;
    }

    /**
     * Creates an HBox for bot dialog box representation.
     *
     * @return The HBox representing the bot dialog box.
     */
    private HBox botDialogBox() {
        HBox box = new HBox(displayPicture, text);
        box.setAlignment(Pos.TOP_LEFT);
        box.setStyle("-fx-background-color: #d4f4fc; -fx-padding: 10; " +
                "-fx-background-radius: 50; -fx-border-radius: 50; " +
                "-fx-border-color: #ffffff; -fx-border-width: 1;");
        return box;
    }

    public void flip() {
        this.setAlignment(this.getAlignment() == Pos.TOP_LEFT ? Pos.TOP_RIGHT : Pos.TOP_LEFT);

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);

        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, true);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        return db;
    }
}
