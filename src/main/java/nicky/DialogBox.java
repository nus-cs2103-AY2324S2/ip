package nicky;

import java.io.IOException;

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
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * DialogBox class represents a custom control used to display messages in the Nicky application.
 * It includes an image and a text area to show messages from both the user and Nicky.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox. Loads the FXML and sets up the layout.
     */
    private DialogBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Configures the dialog box with appropriate settings based on whether the dialog is for Nicky or the user.
     * @param isNicky A boolean flag indicating whether the dialog box is for Nicky (true) or the user (false).
     *               This affects the background color and alignment of the dialog box.
     */
    private void setupDialogBox(boolean isNicky) {
        dialog.setWrapText(true);
        dialog.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(dialog, Priority.ALWAYS);
        dialog.setAlignment(Pos.CENTER_LEFT);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        Color bgColor = isNicky ? Color.LIGHTBLUE : Color.LIGHTGRAY;
        BackgroundFill fill = new BackgroundFill(bgColor, new CornerRadii(5.0), Insets.EMPTY);
        this.setBackground(new Background(fill));

        if (isNicky) {
            flip();
        }
    }

    /**
     * Creates a dialog box for user input with specified text and image.
     * @param text The text to be displayed in the dialog box.
     * @param img The image associated with the dialog box (typically the user's avatar).
     * @return A DialogBox instance configured for displaying user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox();
        dialogBox.dialog.setText(text);
        dialogBox.displayPicture.setImage(img);
        dialogBox.setupDisplayPicture();
        dialogBox.setupDialogBox(false); // false for user
        return dialogBox;
    }

    /**
     * Creates a dialog box for Nicky's response with specified text and image.
     * @param text The text to be displayed in the dialog box.
     * @param img The image associated with the dialog box (typically Nicky's avatar).
     * @return A DialogBox instance configured for displaying Nicky's response.
     */
    public static DialogBox getNickyDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox();
        dialogBox.dialog.setText(text);
        dialogBox.displayPicture.setImage(img);
        dialogBox.setupDisplayPicture();
        dialogBox.setupDialogBox(true); // true for chatbot
        return dialogBox;
    }

    /**
     * Sets up the display picture by applying a circular clip to the image view.
     */
    private void setupDisplayPicture() {
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box to change the orientation of the text and image.
     * This method is typically used to differentiate between user messages and Nicky's responses.
     */
    private void flip() {
        ObservableList<Node> tmp = this.getChildren();
        FXCollections.reverse(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }
}
