package gulie.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public abstract class Dialog extends HBox {
    private Label text;
    private ImageView image;

    public Dialog(String text, Image image) {
        this.text = new Label(text);
        this.image = new ImageView(image);

        this.text.setWrapText(true);
        this.image.setFitWidth(100);
        this.image.setFitHeight(100);
    }

    public Label getText() {
        return text;
    }

    public ImageView getImage() {
        return image;
    }
}
