package nihao.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogueBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogueBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogueBox getUserDialogue(Label l, ImageView iv) {
        return new DialogueBox(l, iv);
    }

    public static DialogueBox getNihaoDialogue(Label l, ImageView iv) {
        DialogueBox db = new DialogueBox(l, iv);
        db.flip();
        return db;
    }
}
