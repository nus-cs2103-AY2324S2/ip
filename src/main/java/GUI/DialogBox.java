package GUI;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
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


import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private Color textboxColor;
    private int textboxWidth = 310;

    private DialogBox(String text, Image img, String type) {

        dialog = new Label(text);
        displayPicture = new ImageView(img);

        dialog.setText(text);

        displayPicture.setImage(img);
        if (type.equals("USER")) {
            textboxColor = Color.LIGHTBLUE;
        } else {
            textboxColor = Color.LIGHTPINK;
        }

        textStyle(dialog);
        StackPane textBox = new StackPane();
        makeTextbox(textBox, dialog);
        displayPicStyle(displayPicture);
        dialogBoxStyle(textBox, displayPicture);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "USER");
    }

    public static DialogBox getSignalDialog(String text, Image img) {
        var db = new DialogBox(text, img, "");
        db.flip();
        return db;
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

        rect.widthProperty().bind(Bindings.min(text.widthProperty(), textboxWidth));
        rect.heightProperty().bind(text.heightProperty());

        stack.setAlignment(text, Pos.CENTER_RIGHT);
        stack.getChildren().addAll(rect, text);
    }

    /**
     * Styles the text of the dialog.
     *
     * @param text The text.
     */
    private void textStyle(Label text) {
        text.setWrapText(true);
        text.setPadding(new Insets(10));
        text.setMinHeight(Double.NEGATIVE_INFINITY);

    }

    /**
     * Styles the display picture.
     *
     * @param img The display picture.
     */
    private void displayPicStyle(ImageView img) {
        img.setFitWidth(40.0);
        img.setFitHeight(40.0);

        Circle clip = new Circle(20, 20, 20);
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


}
