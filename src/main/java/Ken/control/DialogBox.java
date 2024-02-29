package ken.control;

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
import javafx.scene.text.TextFlow;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private TextFlow dialogTextFlow;

    /**
     * Creates DialogBox Object.
     */
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
        displayPicture.setImage(img);

        Circle clipCircle = new Circle();

        clipCircle.setRadius(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);

        displayPicture.setClip(clipCircle);
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


    /**
     * Gets User DialogBox with user image and input.
     *
     * @param img image of user.
     * @param text text provided by user.
     * @return DialogBox for user dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #F18DBC;");
        return db;
    }

    /**
     * Gets User DialogBox with ken image and response.
     *
     * @param img image of ken.
     * @param text response provided by ken.
     * @return DialogBox for ken dialog
     */
    public static DialogBox getKenDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #FACDE5;");
        return db;
    }
}
