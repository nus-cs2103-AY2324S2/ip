package wis.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

//@@author: Jeffry Lum
//All code in this file is reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications

public class DialogBox extends HBox {
    private TextArea text;
    private ImageView displayPicture;

    public DialogBox(TextArea l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setEditable(false);
        text.setWrapText(true);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
}
