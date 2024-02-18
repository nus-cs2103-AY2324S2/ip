package gulie.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

/**
 * A dialog box for the user.
 */
public class UserDialog extends Dialog {
    private static String IMAGE_PATH = "/images/user.png";

    /**
     * Creates a dialog box for the user with the given text.
     * @param text
     */
    public UserDialog(String text) {
        super(text, new Image(IMAGE_PATH));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.getText(), this.getImage());
    }
}
