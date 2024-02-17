package javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

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

    private DialogBox(String text, Image img, boolean isUser, boolean isError) {
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
        this.setSpacing(10);
        this.dialog.setFont(Font.font("Comic Sans MS", 15));

        Background bg = isUser
                ? new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))
                : new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY));
        this.setBackground(bg);

        // Change text color based on user or duke

        // Add drop shadow effect
        if (!isUser) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.GOLD);
            dropShadow.setRadius(3.0);
            dropShadow.setOffsetX(2.0);
            dropShadow.setOffsetY(2.0);
            dialog.setEffect(dropShadow);
        }

        if (isError) {
            bg = new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY));
            dialog.setTextFill(Color.RED); // Set text color to white for better visibility
        }

        if (isUser) {
            applyCircularShape();
        } else {
            applyTriangularShape();
        }
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
        return new DialogBox(text, img, true, false);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isMessageError) {
        var db = new DialogBox(text, img, false, isMessageError);
        db.flip();
        return db;
    }

    //Function reused from appleraincoat's ip
    private void applyCircularShape() {
        Circle clip = new Circle();
        clip.setRadius(displayPicture.getFitWidth() / 2.3); // Radius should be half of the ImageView width
        clip.setCenterX(displayPicture.getFitWidth() / 2); // Center X coordinate of the circle
        clip.setCenterY(displayPicture.getFitHeight() / 2); // Center Y coordinate of the circle
        displayPicture.setClip(clip);
    }

    private void applyTriangularShape() {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(0.0, 0.0,
                                    100.0, 0.0,
                                    50.0, 86.6);
        displayPicture.setClip(triangle);
    }
}
