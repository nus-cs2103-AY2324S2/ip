package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Collections;

public class UserDialogBox extends DialogBox {

    public UserDialogBox(String text, Image img) {
        super("You", text, img);
        setLabelStyle("-fx-background-color: #0052F5; -fx-background-radius: 10px;", Color.WHITE);
    }

    public static UserDialogBox getUserDialog(String userText, Image img) {
        return new UserDialogBox(userText, img);
    }
}