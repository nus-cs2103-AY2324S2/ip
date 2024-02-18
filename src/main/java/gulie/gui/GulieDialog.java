package gulie.gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

/**
 * A dialog box for Gulie.
 */
public class GulieDialog extends Dialog {
    private static String IMAGE_PATH = "/images/gulie.png";

    /**
     * Creates a dialog box for Gulie with the given text.
     * @param text
     */
    public GulieDialog(String text) {
        super(text, new Image(IMAGE_PATH));
        assert !text.equals("") : "Gulie should never print nothing";
        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(this.getImage(), this.getText());
    }
}
