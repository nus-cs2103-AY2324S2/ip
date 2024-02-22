package nollid.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box used in the user interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructs a DialogBox with the specified text and image.
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

        Image roundedImage = roundImage(img);

        dialog.setText(text);
        displayPicture.setImage(roundedImage);
    }

    /**
     * Creates the DialogBox for user input with the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates the DialogBox for Nollid's output with the specified text and image.
     * DialogBox will be flipped horizontally.
     */
    public static DialogBox getNollidDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackgroundColor("#79e8b2");
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Changes the background colour of the Label too.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Sets the background color of the DialogBox.
     *
     * @param colorHexCode The hexadecimal color code.
     */
    private void setBackgroundColor(String colorHexCode) {
        // Change background color to #79e8b2
        this.dialog.setStyle(this.dialog.getStyle().replaceFirst("-fx-background-color: #(?:[A-Fa-f0-9]{3}){1,2}\\b;",
                "-fx-background-color: " + colorHexCode + ";"));
    }

    /**
     * Rounds the corners of a square Image object to create a circular profile picture.
     *
     * @param image The original Image.
     * @return The rounded Image.
     */
    private Image roundImage(Image image) {
        // Solution below taken from https://stackoverflow.com/questions/68631386/javafx-crop-image-as-a-circle

        // Params for creating circle object to crop user profile picture
        double circleXCenter = image.getWidth() / 2;
        double circleYCenter = image.getHeight() / 2;
        double circleRadius = image.getWidth() / 2;

        Circle clip = new Circle(circleXCenter, circleYCenter, circleRadius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }
}
