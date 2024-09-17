package zack;

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
import javafx.scene.shape.Rectangle;

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

    private DialogBox(String text, Image img, double cropLeft, double cropTop, double cropRight,
                      double cropBottom, double arcWidth, double arcHeight) {
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

        // Create a Rectangle for clipping with rounded edges
        Rectangle clipRectangle = getClipRectangle(cropLeft, cropTop, cropRight, cropBottom,
                arcWidth, arcHeight);


        // Apply the clip to the ImageView
        displayPicture.setClip(clipRectangle);

    }

    private Rectangle getClipRectangle(double cropLeft, double cropTop, double cropRight, double cropBottom,
                                       double arcWidth, double arcHeight) {
        Rectangle clipRectangle = new Rectangle();

        // Set the width of the Rectangle to be the ImageView's width minus cropWidth
        clipRectangle.setWidth(displayPicture.getFitWidth() - cropLeft - cropRight);
        // Set the height of the Rectangle to match the ImageView's height
        clipRectangle.setHeight(displayPicture.getFitHeight() - cropTop - cropBottom);

        clipRectangle.setX(cropLeft);
        clipRectangle.setY(cropTop);

        // Set the arcWidth and arcHeight to control the roundness of the corners
        clipRectangle.setArcWidth(arcWidth); // Adjust these values as needed
        clipRectangle.setArcHeight(arcHeight); // Adjust these values as needed
        return clipRectangle;
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
        // Customize appearance for the user dialog with additional cropping
        double cropLeft = 10;
        double cropTop = 0;
        double cropRight = 10;
        double cropBottom = 20;
        double arcWidth = 20;
        double arcHeight = 20;
        var db = new DialogBox(text, img, cropLeft, cropTop, cropRight, cropBottom, arcWidth, arcHeight);
        db.setStyle("-fx-background-color: #FFD3B4;");
        return db;
    }

    public static DialogBox getZackDialog(String text, Image img) {
        // Zack's dialog customization remains the same or can be adjusted similarly
        double cropLeft = 0;
        double zackCropTop = 18;
        double cropRight = 55;
        double cropBottom = 0;
        double zackArcWidth = 20;
        double zackArcHeight = 20;
        // Assuming symmetric cropping for Zack's dialog for simplicity
        var db = new DialogBox(text, img, cropLeft, zackCropTop, cropRight,
                cropBottom, zackArcWidth, zackArcHeight);
        db.flip();
        db.setStyle("-fx-background-color: #FFAAA7;");
        return db;
    }
}
