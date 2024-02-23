package shon.component;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import shon.MainWindow;

/**
 * Represents the user's dialog box.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle circle;

    /**
     * Creates a dialog box for user.
     * @param text The message to be displayed.
     * @param img The anon image of user.
     */
    public UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        circle.setFill(new ImagePattern(img));
    }
}
