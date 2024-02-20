package duke.ui;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class UserDialogBox extends DialogBox {

    public UserDialogBox(String text, Image img) {
        super(text, img);
        setLabelStyle("-fx-background-color: #0052F5; -fx-background-radius: 10px;", Color.WHITE);
    }

    public static UserDialogBox getUserDialog(String userText, Image img) {
        return new UserDialogBox(userText, img);
    }
}