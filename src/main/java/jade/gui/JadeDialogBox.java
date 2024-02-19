package jade.gui;

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
import javafx.scene.shape.Rectangle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the bot,
 * and a label containing text from the bot.
 */
public class JadeDialogBox extends HBox {
    private static final String FXML_FILE_PATH = "/view/JadeDialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private JadeDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_FILE_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        setImgStyle();
    }
    /**
     * Styles the user image and jade image which will appear in dialog boxes.
     */
    private void setImgStyle() {
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        displayPicture.setClip(clip);
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

    public static JadeDialogBox getUserDialog(String text, Image img) {
        return new JadeDialogBox(text, img);
    }

    public static JadeDialogBox getJadeDialog(String text, Image img) {
        var db = new JadeDialogBox(text, img);
        db.flip();
        return db;
    }
}
