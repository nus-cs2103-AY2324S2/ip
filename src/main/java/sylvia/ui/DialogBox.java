package sylvia.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int IMAGE_RADIUS = 20;
    private static final String userDialogStyle = "-fx-background-color: #0084ff;"
            + " -fx-background-radius: 20 5 20 20;";
    private static final String sylviaDialogStyle = "-fx-background-color: #f0f0f0;"
            + " -fx-background-radius: 5 20 20 20;";

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox dialogContainer;

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
        Circle clip = new Circle(displayPicture.getLayoutX() + IMAGE_RADIUS, displayPicture.getLayoutY() + IMAGE_RADIUS,
                IMAGE_RADIUS);
        displayPicture.setClip(clip);
        HBox.setMargin(dialogContainer, new Insets(0, 10, 0, 10));

        // when text overflows, wrap it to the next line
        if (dialog.getLayoutBounds().getWidth() > 300) {
            dialog.setWrappingWidth(275);
        }
    }

    /**
     * Returns a dialog box representing the user's input.
     *
     * @param text The user's input.
     * @param img  The user's image.
     * @return The dialog box representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialogContainer.setStyle(userDialogStyle);
        db.dialog.setFill(Paint.valueOf("#FFFFFF"));
        return db;
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

    /**
     * Returns a dialog box representing the bot's response.
     *
     * @param text The bot's response.
     * @param img  The bot's image.
     * @return The dialog box representing the bot's response.
     */
    public static DialogBox getSylviaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialogContainer.setStyle(sylviaDialogStyle);
        db.dialog.setFill(Paint.valueOf("#000000"));
        return db;
    }
}
