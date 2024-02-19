package aegis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * DialogBox class represents a custom dialog box that is used in the JavaFX GUI for Aegis Assistant.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for the creating a DialogBox object.
     *
     * @param l Label to contain text to show in the dialog box.
     * @param iv Image to display in dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;


        text.setWrapText(true);
        displayPicture.setFitWidth(25.0);
        displayPicture.setFitHeight(25.0);

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(5.0);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.BOTTOM_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getAegisDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
