import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class DialogBox extends HBox {
    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            String fxmlPath = isUser ? "/view/UserDialogBox.fxml" : "/view/DukeDialogBox.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle(50, 50, 40);
        dialog.setText(text);
        dialog.setWrappingWidth(330);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image, true);
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        return new DialogBox(text, image, false);
    }
}