package tes.gui;

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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
    private ImageView pictureDisplayer;
    @FXML
    private StackPane imageContainer;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setWrapText(true);
        dialog.setText(text);
        pictureDisplayer.setImage(img);
        pictureDisplayer.setClip(clipToCircle(pictureDisplayer));

        imageContainer.getChildren().add(0, addCircularBorder(pictureDisplayer));

        if (isUser) {
            dialog.setStyle("-fx-background-color: #a1f1ff; "
                    + "-fx-background-radius: 15; "
                    + "-fx-padding: 10;");
        } else {
            dialog.setStyle("-fx-background-color: #E7E7E7; "
                    + "-fx-background-radius: 15; "
                    + "-fx-padding: 10;");
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
        return new DialogBox(text, img, true);
    }

    public static DialogBox getTesDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

    public Circle clipToCircle(ImageView imageView) {
        Circle clip = new Circle();
        clip.setCenterX(imageView.getFitWidth() / 2);
        clip.setCenterY(imageView.getFitHeight() / 2);
        clip.setRadius(imageView.getFitWidth() / 2);
        return clip;
    }

    public static Circle addCircularBorder(ImageView imageView) {
        double radius = imageView.getFitHeight() / 2;
        Circle border = new Circle(radius, radius, radius);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(2);
        border.setFill(Color.TRANSPARENT);
        border.setCenterX(imageView.getLayoutX() + radius);
        border.setCenterY(imageView.getLayoutY() + radius);
        return border;
    }
}
