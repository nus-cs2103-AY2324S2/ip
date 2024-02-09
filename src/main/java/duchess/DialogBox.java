package duchess;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * The package contains classes related to the Duchess application.
 * This package provides the class for displaying dialog boxes in the UI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified Label and ImageView.
     *
     * @param l The Label containing the text to display in the dialog box.
     * @param iv The ImageView containing the image to display in the dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        // Set text wrapping and image dimensions
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Align dialog box contents to the top right
        this.setAlignment(Pos.TOP_RIGHT);

        // Add text and image to the dialog box
        this.getChildren().addAll(text, displayPicture);
    }
}