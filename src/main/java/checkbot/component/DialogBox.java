package checkbot.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A container for dialog box. Contains text and an image representing the avatar.
 */
public class DialogBox extends HBox {
    private final Label text;
    private final ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param label     The text that the dialog box will contain.
     * @param imageView The ImageView object containing the avatar.
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setPadding(new Insets(10));
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Returns a DialogBox that has labels and profile pictures aligned such that we can
     * tell that the message came from the user.
     *
     * @param label     The Label containing the message from the user.
     * @param imageView The profile picture of the user.
     * @return The DialogBox that wraps the whole thing together.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }


    /**
     * Returns a DialogBox that has labels and profile pictures aligned such that we can
     * tell that the message came from Checkbot.
     *
     * @param label     The Label containing the message from Checkbot.
     * @param imageView Checkbot's profile picture.
     * @return The DialogBox that wraps the whole thing together.
     */
    public static DialogBox getCheckbotDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        db.setBackground(Background.fill(Color.color(0.85, 0.85, 0.85)));
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
