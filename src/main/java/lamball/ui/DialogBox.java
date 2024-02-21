package lamball.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * Class for Dialog box - usable for both user input and chatbot response.
 */
public class DialogBox extends HBox {

    private Text text;
    private ImageView displayPicture;

    private Number windowWidth;


    /**
     * Private constructor for dialogbox.
     *
     * @param t Text component.
     * @param iv Image component.
     */
    private DialogBox(Text t, ImageView iv, Number windowWidth) {
        this.windowWidth = windowWidth;
        text = t;
        displayPicture = iv;

        text.setFill(Color.rgb(255, 255, 255));
        text.setWrappingWidth(windowWidth.doubleValue() - 150);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        // Create a Circle as a clip
        Circle clip = new Circle(50);
        clip.setCenterX(50);
        clip.setCenterY(50);

        // Set the circle clip to the ImageView
        displayPicture.setClip(clip);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10));
        this.setSpacing(5);
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
     * Returns a dialog box simulating a user talking.
     *
     * @param t Text label
     * @param iv Imageview of desired image.
     * @return Instance of user dialog.
     */
    public static DialogBox getUserDialog(Text t, ImageView iv, Number windowWidth) {
        var db = new DialogBox(t, iv, windowWidth);
        Background bgUser = new Background(new BackgroundFill(Color.rgb(50, 50, 50), null, null));
        db.setBackground(bgUser);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Returns a dialog box simulating the chatbot's response.
     *
     * @param t Text label
     * @param iv Imageview of desired image.
     * @return Instance of chatbot dialog.
     */
    public static DialogBox getLamballDialog(Text t, ImageView iv, Number windowWidth) {
        var db = new DialogBox(t, iv, windowWidth);
        Background bgLamball = new Background(new BackgroundFill(Color.rgb(80, 80, 80), null, null));
        db.setBackground(bgLamball);
        db.flip();
        return db;
    }


    public void setWidth(Number width) {
        windowWidth = width;
    }
}
