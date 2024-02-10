package ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box for displaying messages in the GUI that has been built.
 * This class extends HBox to arrange a text label and an image view horizontally.
 *
 * Note: This class has been heavily referenced from the JavaFX guide provided in
 * the CS2103T module.
 * Link: https://se-education.org/guides/tutorials/javaFxPart3.html
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object with the specified label and ImageView.
     *
     * @param label The Label containing the text to display.
     * @param iv The ImageView containing the image to display.
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        Color backgroundColor = Color.PALETURQUOISE;
        this.setBackground(new Background(new BackgroundFill(backgroundColor, new CornerRadii(5), Insets.EMPTY)));

        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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
     * This method helps to flip the Duke Label to the left side, by
     * creating a Duke dialog box with the specified label and image view, then flipping it.
     *
     * @param label The label to display in the Duke dialog.
     * @param imageView The image view to display in the Duke dialog.
     * @return A DialogBox configured for displaying Duke's messages, with elements flipped.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}
