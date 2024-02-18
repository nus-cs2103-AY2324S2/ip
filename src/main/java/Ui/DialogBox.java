package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Custom dialog box to handle conversations with user
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Init, set alignment and add children to dialog box
     * @param l Label, or text
     * @param iv ImageView, or image
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
     * Simple method to flip dialog box to emulate asymmetric conversation
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Setting user's dialog box
     * @param l user text
     * @param iv user image
     * @return dialog box with formatted components
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(0, 10, 0, 10));
        l.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        return new DialogBox(l, iv);
    }

    /**
     * Setting kewgy's dialog box
     * @param l kewgy text
     * @param iv kewgy image
     * @return dialog box with formatted components
     */
    public static DialogBox getKewgyDialog(Label l, ImageView iv) {
        l.setPadding(new Insets(0, 10, 0, 10));
        l.setFont(Font.font("Georgia", FontWeight.BOLD, 12));

        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
