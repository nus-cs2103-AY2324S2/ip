package someboty.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class DialogBox extends HBox {
    @FXML
    private Label dialogLabel;
    @FXML
    private Circle circle = new Circle();

    private DialogBox(String text, Image img, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogLabel.setText(text);
        dialogLabel.setBackground(new Background(new BackgroundFill(color, null, null)));
        circle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     * Copied from the given JavaFX tutorial.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Create a Dialogue Box (consisting of a text and user's image) to display in the application.
     * 
     * @param text A String to be displayed in the box.
     * @param img An image of the user to display beside the text.
     * @return A DialogBox containing the text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Color.rgb(230, 230, 230));
    }

    /**
     * Create a Dialogue Box (consisting of a text and BOT's image) to display in the application.
     * 
     * @param text A String to be displayed in the box.
     * @param img An image of the BOT to display beside the text.
     * @return A DialogBox containing the text and image.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img, Color.ALICEBLUE);
        box.flip();
        return box;
    }
}