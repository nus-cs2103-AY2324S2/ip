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

    /**
     * Constructs a Dialog Box for the Signal chatbot.
     *
     * @param l The text in the dialog box.
     * @param iv The image respresenting the user or Signal.
     * @param type Indicates if the dialog box is for a user or for Signal.
     */
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

    /**
     * Styles the rectangle of the textbox and stacks the text on top of it.
     *
     * @param stack The stack containing the rectangle and the text.
     * @param text The text.
     */
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

    /**
     * Styles the text of the dialog.
     *
     * @param text The text.
     */
    private void textStyle(Label text) {
        text.setWrapText(true);
    }

    /**
     * Styles the display picture.
     *
     * @param img The display picture.
     */
    private void displayPicStyle(ImageView img) {
        img.setFitWidth(100.0);
        img.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        img.setClip(clip);
    }

    /**
     * Styles the dialog box.
     *
     * @param stack The stack of the textbox.
     * @param img The display picture.
     */
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
