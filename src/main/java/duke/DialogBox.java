package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;



/**
 * Main code to run program.
 *
 *
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Custom dialog box.
     *
     *
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Region padding = new Region();
        padding.setPrefWidth(5);
        StackPane stackPanePic = new StackPane(displayPicture);

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);


        stackPanePic.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, padding, stackPanePic);

        this.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}

