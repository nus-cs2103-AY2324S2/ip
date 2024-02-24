package hal.graphics;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends VBox {
    @FXML
    private Label senderName;
    @FXML
    private Text messageContent;
    @FXML
    private Text timeStamp;
    @FXML
    private ImageView profileImage;

    private DialogBox(String text, Image img, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        senderName.setText(name);
        messageContent.setText(text);
        profileImage.setImage(img);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        timeStamp.setText(formatter.format(LocalDateTime.now()));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right,
     * to display HAL9000's dialogue.
     */
    private void flip() {
        HBox hBox = (HBox) this.getChildren().get(0);
        hBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        this.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "Dave");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, "HAL9000");
        db.flip();
        return db;
    }
}
