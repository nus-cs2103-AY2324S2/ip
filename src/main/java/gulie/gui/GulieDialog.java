package gulie.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

public class GulieDialog extends Dialog {
    private static String IMAGE_PATH = "/images/gulie.png";
    public GulieDialog(String text) {
        super(text, new Image(IMAGE_PATH));
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(this.getImage(), this.getText());
    }
}
