package bob.gui;

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
    private static final double DISPLAY_PICTURE_SIZE = 100;
    private static final int DISPLAY_PICTURE_RADIUS = 45;

    private static final Color USER_BACKGROUND_COLOR = Color.web("#37CAEC");
    private static final Color BOB_BACKGROUND_COLOR = Color.web("#2A93D5");

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        displayPicture.setClip(new Circle(
                DISPLAY_PICTURE_SIZE / 2, DISPLAY_PICTURE_SIZE / 2, DISPLAY_PICTURE_RADIUS));
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private void setBackgroundColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, new CornerRadii(0), new Insets(0))));
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBackgroundColor(USER_BACKGROUND_COLOR);
        return db;
    }

    public static DialogBox getBobDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBackgroundColor(BOB_BACKGROUND_COLOR);
        db.flip();
        return db;
    }
}
