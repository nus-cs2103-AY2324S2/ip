package gui;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Color USER_TEXT_COLOR = Color.web("#EEFCD7");
    private static final Color CHRONOS_TEXT_COLOR = Color.web("#FFFFFF");

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructs a dialog box to display on GUI.
     *
     * @param text Text input provided by the user or text output processed from the user's input.
     * @param img Image of speaker (Chronos or user).
     */
    private DialogBox(String text, Image img, Color backgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true);
        dialog.setStyle("-fx-background-radius: 5px; "
                + "-fx-background-color: " + toRgbString(backgroundColor) + "; "
                + "-fx-padding: 10px;"
                + "-fx-border-color: #F2F2F2;"
                + "-fx-border-width: 1px;"
                + "-fx-font-family: 'Product Sans'; -fx-font-size: 13px;");

        displayPicture.setImage(img);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(displayPicture.getFitWidth() / 2, displayPicture.getFitHeight() / 2,
                displayPicture.getFitWidth() / 2);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(15);
    }

    private String toRgbString(Color color) {
        return String.format("rgba(%d, %d, %d, %f)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255),
                color.getOpacity());
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
     * Creates an instance of a user dialog box with the given text and image.
     *
     * @param text Text input provided by the user.
     * @param img Image of user.
     * @return A constructed dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, USER_TEXT_COLOR);
    }

    /**
     * Creates an instance of a Chronos dialog box with the given text and image.
     *
     * @param text Text output processed from the user's input.
     * @param img Image of Chronos.
     * @return A constructed dialog box.
     */
    public static DialogBox getChronosDialog(String text, Image img) {
        var db = new DialogBox(text, img, CHRONOS_TEXT_COLOR);
        db.flip();
        return db;
    }
}
