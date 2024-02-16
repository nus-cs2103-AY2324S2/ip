package grizzly.ui;

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
import javafx.scene.shape.Polygon;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Polygon arrow;
    @FXML
    private Circle displayPicture;

    /**
     * Creates a DialogBox for user and bot interactions.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
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

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        setDropShadow();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        this.arrow.setRotate(180);
        setAlignment(Pos.CENTER_LEFT);
        dialog.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox that is right justified.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);

        userDialog.arrow.setStyle("-fx-fill: aliceblue;");
        userDialog.dialog.setStyle("-fx-background-color: aliceblue;");

        return userDialog;
    }

    /**
     * Creates a DialogBox that is left justified.
     *
     * @param text User input or bot response.
     * @param img Image of user or bot.
     */
    public static DialogBox getGrizzlyDialog(String text, Image img) {
        DialogBox grizzlyDialog = new DialogBox(text, img);
        grizzlyDialog.flip();

        grizzlyDialog.arrow.setStyle("-fx-fill: #a0522d;");
        grizzlyDialog.dialog.setStyle("-fx-background-color: #a0522d; -fx-text-fill: whitesmoke;");

        return grizzlyDialog;
    }

    /**
     * Sets drop shadow effect for the dialog box.
     */
    private void setDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(1.5);
        dropShadow.setOffsetY(1.5);
        dropShadow.setColor(Color.color(0, 0, 0));

        this.setEffect(dropShadow);
    }
}
