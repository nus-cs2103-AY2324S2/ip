package nihao.ui;

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

import java.io.IOException;

public class DialogueBoxController extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox dialogueContainer;

    private DialogueBoxController(String text, Image displayPicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowController.class.getResource("/view/DialogueBox.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.displayPicture.setImage(displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogueBoxController getUserDialogue(String l, Image iv) {
        return new DialogueBoxController(l, iv);
    }

    public static DialogueBoxController getNihaoDialogue(String l, Image iv) {
        DialogueBoxController db = new DialogueBoxController(l, iv);
        db.flip();
        return db;
    }
}
