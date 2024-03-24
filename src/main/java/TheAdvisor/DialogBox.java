package theadvisor;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in the application GUI. Each dialog box contains a message and an image.
 * The appearance and alignment of the dialog box depend on whether it represents a user message or an advisor message.
 */
public class DialogBox extends HBox {
    private static final double IMAGE_SIZE = 50;
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the provided message and image.
     * The appearance and alignment of the dialog box depend on whether it represents a user message or an
     *      advisor message.
     *
     * @param text  The message text to be displayed in the dialog box.
     * @param img   The image representing the speaker.
     * @param isUserMessage  A boolean flag indicating whether the message is from the user (true) or advisor (false).
     */
    public DialogBox(String text, Image img, boolean isUserMessage) {
        this.text = new Label(text);
        displayPicture = new ImageView(img);

        this.text.setWrapText(true);
        displayPicture.setFitWidth(IMAGE_SIZE);
        displayPicture.setFitHeight(IMAGE_SIZE);
        Circle clip = new Circle(IMAGE_SIZE / 2, IMAGE_SIZE / 2, IMAGE_SIZE / 2);
        displayPicture.setClip(clip);
        if (isUserMessage) {
            this.text.setStyle("-fx-background-color: #FFCCCC; -fx-padding: 8px;");
            setAlignment(Pos.TOP_RIGHT);
        } else {
            this.text.setStyle("-fx-background-color: #CCCCFF; -fx-padding: 8px;");
            setAlignment(Pos.TOP_LEFT);
        }

        this.getChildren().addAll(this.text, displayPicture);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getAdvisorDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}
