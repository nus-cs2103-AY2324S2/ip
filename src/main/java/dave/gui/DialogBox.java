package dave.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
// import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

//@@author iynixil-reused
//    from https://se-education.org/guides/tutorials/javaFx.html
//    (all parts of the tutorial) with minor modifications
/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to
 * represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

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
        dialog.setFont(Font.font("Consolas", 12));
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDb = new DialogBox(text, img);
        userDb.setStyle("-fx-background-color: #f5f19f;"); // Solution adapted from
                                                           // https://github.com/alfaloo/ip/blob/master/src/main/java/javafxgui/DialogBox.java
        return userDb;

    }

    public static DialogBox getDaveDialog(String text, Image img) {
        var daveDb = new DialogBox(text, img);
        daveDb.flip();
        daveDb.setStyle("-fx-background-color: #dbae81;"); // Solution adapted from
                                                           // https://github.com/alfaloo/ip/blob/master/src/main/java/javafxgui/DialogBox.java
        return daveDb;
    }
}
