package shon.component;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import shon.MainWindow;

/**
 * Represents the dialog box of Shon.
 */
public class ShonDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle circle;

    /**
     * Creates a dialog box for Shon.
     * @param text The message to be displayed.
     * @param img The image of Shon bot.
     */
    public ShonDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ShonDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.flip();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        circle.setFill(new ImagePattern(img));
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

    public static ShonDialogBox getErrorDialog(String text, Image img) {
        ShonDialogBox db = new ShonDialogBox(text, img);
        db.dialog.setTextFill(Paint.valueOf("#E70000"));
        return db;
    }
}
