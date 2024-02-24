package arc.ui.gui;

import java.io.IOException;
import java.util.Collections;

import arc.exceptions.ArcException;
import arc.exceptions.resource.LoadResourceFailedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image.
     * The DialogBox represents a message dialog containing text from the speaker
     * and an image representing the speaker's face.
     *
     * @param text The text content of the dialog box.
     * @param image The image representing the speaker's face.
     */
    private DialogBox(String text, Image image) throws ArcException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ioException) {
            throw new LoadResourceFailedException(ioException);
        }

        Circle circle = new Circle(image.getWidth() / 2, image.getHeight() / 2, 70.0);

        dialog.setText(text);
        displayPicture.setImage(image);
        displayPicture.setClip(circle);
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
     * Creates a dialog box representing a user's message.
     * This method sets the background color to light blue and returns the dialog box.
     *
     * @param text The text content of the user's message.
     * @param img The image representing the user's face.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) throws ArcException {
        DialogBox dialogBox = new DialogBox(text, img);

        dialogBox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        return dialogBox;
    }

    /**
     * Creates a dialog box representing an Arc's message.
     * This method sets the background color to light pink and returns the dialog box.
     * Additionally, it flips the dialog box such that the image is on the left and text on the right.
     *
     * @param text The text content of Arc's message.
     * @param img The image representing Arc's face.
     * @return A DialogBox representing Arc's message.
     */
    public static DialogBox getArcDialog(String text, Image img) throws ArcException {
        DialogBox dialogBox = new DialogBox(text, img);

        dialogBox.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
        dialogBox.flip();

        return dialogBox;
    }
}
