package duke;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setStyle("-fx-text-fill: white;");
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Clip the ImageView to a circle
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        // Set alignment and padding
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 10, 10, 10));
        HBox.setMargin(displayPicture, new Insets(0, 10, 0, 0));
        this.setStyle("-fx-background-color: #1d1d1d; -fx-background-radius: 10;");
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

