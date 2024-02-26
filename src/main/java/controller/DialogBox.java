package controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * represents a dialog box consisting of an ImageView to represent user profile picture and a label
 * containing dialog text.
 */
public class DialogBox extends HBox {
    @FXML
    private VBox dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle pictureBorder;

    public final int FONT_SIZE = 12;
    public final String FONT_FAMILY = "Roboto";


    private DialogBox(String text, Image img, String backgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Color textFillColor = Color.BLACK;
        if (backgroundColor == "#A366F9") {
            textFillColor = Color.WHITE;
        }

        String[] texts = text.split("\n");
        for (int i = 0; i < texts.length; i++) { 
            Node c;
            if (texts[i].contains("[ ]")) {
                String replacedText = texts[i].replace("[ ]", "");
                c = new CheckBox(replacedText + "\n"); 
                ((CheckBox) c).setIndeterminate(false); 
                ((CheckBox) c).setWrapText(true);
                ((CheckBox) c).setFont(new Font(FONT_FAMILY, FONT_SIZE));
                ((CheckBox) c).setTextFill(textFillColor);
            } else if (texts[i].contains("[X]")) {
                String replacedText = texts[i].replace("[X]", "");
                c = new CheckBox(replacedText + "\n"); 
                ((CheckBox) c).setIndeterminate(true); 
                ((CheckBox) c).setWrapText(true);
                ((CheckBox) c).setFont(new Font(FONT_FAMILY, FONT_SIZE));
                ((CheckBox) c).setTextFill(textFillColor);
            } else {
                c = new Label(texts[i] + "\n");
                ((Label) c).setWrapText(true);
                ((Label) c).setFont(new Font(FONT_FAMILY, FONT_SIZE));
                ((Label) c).setTextFill(textFillColor);
            }
            dialog.getChildren().add(c);
        } 

        displayPicture.setImage(img);
        pictureBorder.setStroke(Color.valueOf(backgroundColor));
        pictureBorder.setFill(Color.valueOf(backgroundColor));
        dialog.setStyle("-fx-background-color: " + backgroundColor
                + "; -fx-font-family: " + FONT_FAMILY 
                + "; -fx-font-size: " + String.valueOf(FONT_SIZE) 
                + "; -fx-background-radius: 10;");
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

    public static DialogBox getUserDialog(String text, Image img, String backgroundColor) {
        return new DialogBox(text, img, backgroundColor);
    }

    public static DialogBox getCalDialog(String text, Image img, String backgroundColor) {
        var db = new DialogBox(text, img, backgroundColor);
        db.flip();
        return db;
    }
}
