package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is a custom class for a DialogBox object,
 * consisting of a label and an ImageView.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private Hyperlink hyperLink;

    /**
     * Constructor for a DialogBox object
     * @param l the label in the DialogBox
     * @param iv the ImageView in the DialogBox
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
     * Constructor for a DialogBox object containing an image, label and hyperlink
     * @param l the label in the DialogBox
     * @param hLink the hyperlink in the DialogBox
     * @param iv the image in the DialogBox
     */
    public DialogBox(Label l, Hyperlink hLink, ImageView iv) {
        text = l;
        hyperLink = hLink;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        VBox labelHyperlink = new VBox(text, hyperLink);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(labelHyperlink, displayPicture);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getTamDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public static DialogBox getTamDialog(Label l, Hyperlink hLink, ImageView iv) {
        var db = new DialogBox(l, hLink, iv);
        db.flip();
        return db;
    }
}
