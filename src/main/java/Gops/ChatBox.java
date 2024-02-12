package Gops;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
public class ChatBox extends HBox {
    private Label label;
    private ImageView imageView;

    public ChatBox(Label label, ImageView imageView) {
        this.label = label;
        this.imageView = imageView;

        label.setWrapText(true);
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.label, this.imageView);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static ChatBox getUserDialog(Label l, ImageView iv) {
        return new ChatBox(l, iv);
    }

    public static ChatBox getDukeDialog(Label l, ImageView iv) {
        var db = new ChatBox(l, iv);
        db.flip();
        return db;
    }
}
