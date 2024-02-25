package raphael.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ProfilePicture {
    private final StackPane picture;
    public ProfilePicture(Image image) {
        StackPane pictureFramePane = new StackPane();
        pictureFramePane.setStyle(
                "-fx-border-color: black; "
                + "-fx-border-width: 10px;"
        );
        pictureFramePane.getChildren().add(new ImageView(image));
        this.picture = pictureFramePane;
    }
    public StackPane getPicture() {
        return this.picture;
    }
}
