package chatbot.ui;

import chatbot.print.Message;
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
import javafx.scene.text.Font;

/**
 * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html},
 * with minor modifications.
 *
 * @author Titus Chew
 */
public class DialogBox extends HBox {
    /** An image representing the user. */
    private static final Image USER_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));

    /** An image representing the chatbot. */
    private static final Image CHAT_BOT_IMAGE = new Image(DialogBox.class.getResourceAsStream("/images/DaDuke.png"));

    /** The size of the icon which is equal to the icon's width. */
    private static final int ICON_SIZE = 100;

    /** The default padding to apply to the dialog box and components within. */
    private static final int DEFAULT_PADDING = 10;

    /** The default font family. */
    private static final String FONT_FAMILY = "Consolas";

    /**
     * Constructor.
     * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}.
     *
     * @param message A {@link Message} of text to insert into this.
     * @param displayPicture An {@link Image}.
     */
    public DialogBox(Message message, Image displayPicture) {
        this(message);
        this.getChildren().add(roundImage(displayPicture));
    }

    /**
     * Constructor.
     * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}.
     *
     * @param message A {@link Message} of text to insert into this.
     */
    public DialogBox(Message message) {
        Label label = new Label(message.toString());
        label.setPadding(new Insets(DEFAULT_PADDING));
        label.setWrapText(true);
        label.setFont(Font.font(FONT_FAMILY));
        setLabelTextColor(label, message.getStatus());

        this.setPadding(new Insets(DEFAULT_PADDING));
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().add(label);
    }

    private void setLabelTextColor(Label label, Message.Status messageStatus) {
        if (messageStatus == Message.Status.LOG) {
            label.setTextFill(Color.TEAL);
        } else if (messageStatus == Message.Status.ERROR) {
            label.setTextFill(Color.DARKRED);
        } else {
            label.setTextFill(Color.BLACK);
        }
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
     * @param color The {@link Color} of the {@link Background}.
     */
    private void setBackground(Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }

    /**
     * Converts the image to a circle.
     *
     * @param image The image display in a circle.
     * @return The circle containing the image.
     */
    private Circle roundImage(Image image) {
        Circle circle = new Circle(ICON_SIZE / 2.0);
        ImagePattern pattern = new ImagePattern(image);
        circle.setFill(pattern);
        return circle;
    }

    /**
     * Gets the {@link DialogBox} that represents the user's input.
     *
     * @param message The message to insert into the {@link DialogBox}.
     * @return The {@link DialogBox} that contains the text.
     */
    public static DialogBox getUserDialog(Message message) {
        DialogBox dialogBox = new DialogBox(message, USER_IMAGE);
        dialogBox.setBackground(Color.LIGHTSTEELBLUE);
        return dialogBox;
    }

    /**
     * Gets the {@link DialogBox} by the chatbot.
     *
     * @param message The message to insert into the {@link DialogBox}.
     * @return The {@link DialogBox} that contains the text.
     */
    public static DialogBox getChatBotDialog(Message message) {
        if (message.getStatus() == Message.Status.LOG) {
            return getLogDialog(message);
        }
        DialogBox dialogBox = new DialogBox(message, CHAT_BOT_IMAGE);
        dialogBox.setBackground(Color.LIGHTGRAY);
        dialogBox.flip();
        return dialogBox;
    }
    /**
     * Gets the {@link DialogBox} by the chatbot.
     *
     * @param message The message to insert into the {@link DialogBox}.
     * @return The {@link DialogBox} that contains the text.
     */
    public static DialogBox getLogDialog(Message message) {
        DialogBox dialogBox = new DialogBox(message);
        dialogBox.setBackground(Color.WHITESMOKE);
        dialogBox.flip();
        return dialogBox;
    }
}
