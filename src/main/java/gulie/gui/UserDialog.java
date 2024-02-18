package gulie.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;


public class UserDialog extends Dialog {
    private static String IMAGE_PATH = "/images/user.png";
    public UserDialog(String text) {
        super(text, new Image(IMAGE_PATH));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.getText(), this.getImage());
    }
}
