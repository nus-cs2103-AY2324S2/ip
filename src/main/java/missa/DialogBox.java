package missa;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * DialogBox that contains ImageView and Label.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text = new Label();
    @FXML
    private Circle cir = new Circle(250, 250, 120);

    /**
     * Creates a dialogBox to display image and label.
     *
     * @param content Contents to be displayed.
     * @param image Images to be displayed.
     */
    public DialogBox(String content, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(content);

        // Solution below inspired by https://stackoverflow.com/questions/42116313/how-to-set-an-image-in-a-circle
        cir.setFill(new ImagePattern(image));
        cir.setEffect(new DropShadow(+25d, 0d, +2d, Color.WHITE));
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

    public static DialogBox getUserDialog(String content, Image image) {
        return new DialogBox(content, image);
    }

    public static DialogBox getMissADialog(String content, Image image) {
        var db = new DialogBox(content, image);
        db.flip();
        return db;
    }
}

