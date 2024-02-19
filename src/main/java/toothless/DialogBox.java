package toothless;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private Font userFont = Font.font("Lucida Calligraphy", 12);
    private Font toothlessFont = Font.font("Comic Sans MS", 12);
    private Font warningFont = Font.font("Comic Sans MS", FontWeight.BOLD, 12);
    private Insets textInsets  = new Insets(10);
    private static Insets insets = new Insets(3);
    private static CornerRadii cornerRadii = new CornerRadii(5);

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setPadding(textInsets);
        dialog.setMinSize(dialog.getMinWidth(), dialog.getMinHeight());
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setFont(db.userFont);
        BackgroundFill dialogBackgroundFill = new BackgroundFill(Color.ORANGE, cornerRadii, insets);
        db.dialog.setBackground(new Background(dialogBackgroundFill));
        return db;
    }

    public static DialogBox getToothlessDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setFont(db.toothlessFont);
        BackgroundFill dialogBackgroundFill = new BackgroundFill(Color.GREENYELLOW, cornerRadii, insets);
        db.dialog.setBackground(new Background(dialogBackgroundFill));
        db.flip();
        return db;
    }
}