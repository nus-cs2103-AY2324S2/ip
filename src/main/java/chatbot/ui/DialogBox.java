package chatbot.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Reused from https://se-education.org/guides/tutorials/javaFx.html,
 * with minor modifications.
 */
public class DialogBox extends HBox {
    private static final Image USER_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image DUKE_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/DaDuke.png"));

    /**
     * Class Constructor.
     * Reused from https://se-education.org/guides/tutorials/javaFx.html.
     *
     * @param text a {@link String} of text to insert into this
     * @param displayPicture an {@link Image}
     */
    public DialogBox(String text, Image displayPicture) {
        Label label = new Label(text);
        label.setPadding(new Insets(10));
        label.setWrapText(true);

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(
                label,
                roundImage(displayPicture)
        );
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Sets this background given a {@link Color}.
     *
     * @param color the {@link Color} of the {@link Background}
     */
    private void setBackground(Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }

    /**
     * Converts the image to a circle.
     *
     * @param image the image display in a circle
     * @return the circle containing the image
     */
    private Circle roundImage(Image image) {
        Circle circle = new Circle(50);
        ImagePattern pattern = new ImagePattern(image);
        circle.setFill(pattern);
        return circle;
    }

    /**
     * Gets the {@link DialogBox} that represents the user's input.
     *
     * @param text the text to insert into the {@link DialogBox}
     * @return the {@link DialogBox} that contains the text
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, USER_IMAGE);
        dialogBox.setBackground(Color.LIGHTSTEELBLUE);
        return dialogBox;
    }

    /**
     * Gets the {@link DialogBox} by the chat-bot.
     *
     * @param text the text to insert into the {@link DialogBox}
     * @return the {@link DialogBox} that contains the text
     */
    public static DialogBox getDukeDialog(String text) {
        DialogBox dialogBox = new DialogBox(text, DUKE_IMAGE);
        dialogBox.setBackground(Color.LIGHTGRAY);
        dialogBox.flip();
        return dialogBox;
    }
}
