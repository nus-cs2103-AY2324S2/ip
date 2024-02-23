package ficin;

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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Color COLOR_USER = Color.BLUEVIOLET;
    private static final Color CHAT_COLOR = Color.VIOLET;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    DialogBox(String text, Image img, Color backgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the HBox to stretch and fill its content horizontally
        HBox.setHgrow(this, Priority.ALWAYS);

        // Create a VBox to contain the DialogBox
        VBox vbox = new VBox(this);
        vbox.setFillWidth(true); // Set the VBox to stretch and fill its content vertically

        dialog.setText(text);
        displayPicture.setImage(img);
        applyStyling(backgroundColor);
    }

    private void applyStyling(Color backgroundColor) {
        dialog.setStyle("-fx-background-color: " + toHex(backgroundColor) + "; "
                + "-fx-background-radius: 10; "
                + "-fx-padding: 10px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 1), 10, 0, 0, 2); "
                + "-fx-background-insets: 0;");
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
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
        return new DialogBox(text, img, COLOR_USER);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, CHAT_COLOR);
        db.flip();
        return db;
    }
}
