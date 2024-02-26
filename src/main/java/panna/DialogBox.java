package panna;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * Class which encapsulates the behaviour of the dialogbox
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;


    /**
     * Constructor to set up the formatting of the elements of the dialogBox.
     * @param l
     * @param iv
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(80.0);
        displayPicture.setFitHeight(80.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }


    /**
     * Flips the screen!
     */

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the dialog box from the perspective of the user.
     * @param l
     * @param iv
     * @return
     */

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns the dialog box from the perspective of the bot.
     * @param l
     * @param iv
     * @return
     */

    public static DialogBox getPannaDialog(Label l, ImageView iv) {
        DialogBox dialog = new DialogBox(l, iv);
        dialog.flip();
        return dialog;
    }
}