package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private Color textboxColor;
    private int TEXTBOX_HEIGHT = 100;
    private int TEXTBOX_WIDTH = 240;

    public DialogBox(Label l, ImageView iv, String type) {
        text = l;
        displayPicture = iv;
        if (type.equals("USER")) {
            textboxColor = Color.LIGHTBLUE;
        } else {
            textboxColor = Color.LIGHTPINK;
        }

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        // Create a rectangle shape with rounded corners to enclose the text
        Rectangle rect = new Rectangle();
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(textboxColor);
        rect.setWidth(TEXTBOX_WIDTH); // Adjust the width as needed
        rect.setHeight(TEXTBOX_HEIGHT); // Adjust the height as needed

        StackPane textBox = new StackPane();
        textBox.getChildren().addAll(rect, text);
        text.setTextAlignment(TextAlignment.RIGHT);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(textBox, displayPicture);
        this.setPadding(new Insets(10));
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, "USER");
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, "");
        db.flip();
        return db;
    }
}
