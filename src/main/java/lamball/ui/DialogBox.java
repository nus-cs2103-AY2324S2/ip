package lamball.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class for Dialog box - usable for both user input and chatbot response.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;


    /**
     * Private constructor for dialogbox.
     *
     * @param l
     * @param iv
     */
    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setTextFill(Color.rgb(255, 255, 255));
        text.setWrapText(true);


        // Create a Circle as a clip
        Circle clip = new Circle(50);
        clip.setCenterX(50);
        clip.setCenterY(50);

        // Set the circle clip to the ImageView
        displayPicture.setClip(clip);

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);


        BackgroundFill backgroundFill = new BackgroundFill(Color.rgb(50, 50, 50), null, null);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10));
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
     * @param l Text label
     * @param iv Imageview of desired image.
     * @return Instance of user dialog.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a dialog box simulating the chatbot's response.
     *
     * @param l Text label
     * @param iv Imageview of desired image.
     * @return Instance of chatbot dialog.
     */
    public static DialogBox getLamballDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
