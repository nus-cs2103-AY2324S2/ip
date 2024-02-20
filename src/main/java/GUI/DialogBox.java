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

        textStyle(text);
        StackPane textBox = new StackPane();
        makeTextbox(textBox, text);
        displayPicStyle(displayPicture);
        dialogBoxStyle(textBox, displayPicture);
    }



    private void makeTextbox(StackPane stack, Label text) {
        // Create a rectangle shape with rounded corners to enclose the text
        Rectangle rect = new Rectangle();
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(textboxColor);
        rect.setWidth(TEXTBOX_WIDTH);
        rect.setHeight(TEXTBOX_HEIGHT);

        stack.getChildren().addAll(rect, text);
    }

    private void textStyle(Label text) {
        text.setWrapText(true);
    }

    private void displayPicStyle(ImageView img) {
        img.setFitWidth(100.0);
        img.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        img.setClip(clip);
    }

    private void dialogBoxStyle(StackPane stack, ImageView img) {
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(stack, img);
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
