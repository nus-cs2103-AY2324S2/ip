package chatbot.ui;
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
import javafx.scene.shape.Circle;

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
    private DialogBox(String text, Image img) {
        try {
            String fxmlPath = java.nio.file.Paths.get(".", "view", "DialogBox.fxml").toString();
            fxmlPath = fxmlPath.substring(1);
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        // Create a Circle shape for the clip
        Circle clip = new Circle();
        double diameter = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight());

        clip.setRadius(diameter / 2); // Set the radius to half of the ImageView width or height
        clip.setCenterX(diameter / 2);
        clip.setCenterY(diameter / 2);
        // Set the clip on the ImageView
        displayPicture.setClip(clip);
        displayPicture.setPreserveRatio(true);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        if (dialog.getAlignment().equals(Pos.CENTER_RIGHT)) {
            dialog.setAlignment(Pos.CENTER_LEFT);
        } else {
            dialog.setAlignment(Pos.CENTER_RIGHT);
        }
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getChatbotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
