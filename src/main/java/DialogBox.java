import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * DialogBox class.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor of DialogBox.
     * @param text
     * @param img
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isUser) {
            Font currentFont = dialog.getFont();
            dialog.setFont(Font.font(currentFont.getFamily(), FontPosture.ITALIC, currentFont.getSize()));
        }

        Text message = new Text(text);
        message.setWrappingWidth(200);
        dialog.setWrapText(true);
        dialog.setText(message.getText());
        clipImageViewToCircle(displayPicture);
        displayPicture.setImage(img);
    }

    /**
     * Changes display photo to circle style.
     * @param imageView
     */
    private void clipImageViewToCircle(ImageView imageView) {
        Circle clip = new Circle(imageView.getFitWidth() / 2, imageView.getFitHeight() / 2, imageView.getFitWidth() / 2);
        imageView.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String l, Image iv) {
        DialogBox output = new DialogBox(l, iv, true);
        output.setBackground(new Background(new BackgroundFill(Color.web("#81c483"), null, null)));
        return output;
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.web("#ffff83"), null, null)));
        return db;
    }

}
