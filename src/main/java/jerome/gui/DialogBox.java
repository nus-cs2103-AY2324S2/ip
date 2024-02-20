package jerome.gui;

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
 * DialogBox controlled using FXML.
 * Represents dialog box consisting of ImageView to represent the
 * speaker's face and a label containing text from the speaker.
 * @@author se-edu
 * Reuse from https://se-education.org/guides/tutorials/javaFx.html
 * with minor modifications to cater for differences in
 * program design.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);

        // Leave a nicer space between the Image and the text that user input.
        // Inspired by ChatGPT.
        this.setSpacing(10);
        this.displayPicture.setImage(getRoundedImage(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        this.dialog.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-padding: 5; "
                + "-fx-background-color: rgba(48,48,90,0.13); -fx-border-radius: 2;");
    }

    /**
     * Retrieves the user dialog box.
     *
     * @param text that the user enter.
     * @param img image of the user.
     * @return the user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Retrieves the dialog box for the Chatbot.
     *
     * @param text from the chatbot.
     * @param img chatbot image.
     * @return the dialog box for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    // Solution taken from: https://stackoverflow.com/questions/68631386/javafx-crop-image-as-a-circle
    private static Image getRoundedImage(Image image) {
        // Prompted about ideal radius using ChatGPT.
        double radius = Math.min(image.getWidth(), image.getHeight()) / 2;

        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }
}
