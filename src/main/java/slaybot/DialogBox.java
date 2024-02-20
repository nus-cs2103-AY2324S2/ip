package slaybot;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String USER_DIALOG_COLOR = " #218aff";
    private static final String BOT_DIALOG_COLOR = " #39ff5a";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox vbox = new VBox(this);
        vbox.setFillWidth(true);
        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setStyle("-fx-background-color: " + color + ";"
                + "; -fx-background-radius: 25 25 25 25; -fx-background-radius: 25 25 25 25;" + "-fx-padding: 10px; ");

        Circle clip = new Circle(displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2, displayPicture.getFitWidth() / 2);
        displayPicture.setClip(clip);

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
        return new DialogBox(text, img, USER_DIALOG_COLOR);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, BOT_DIALOG_COLOR);
        db.flip();
        return db;
    }

}

