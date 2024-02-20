package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Collections;

public class DukeDialogBox extends DialogBox {

    public DukeDialogBox(String text, Image img) {
        super(text, img);
        setLabelStyle("-fx-background-color: #F5F5F5; -fx-background-radius: 10px;", Color.BLACK);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public static DukeDialogBox getDukeDialog(String dukeText, Image img) {
        DukeDialogBox db = new DukeDialogBox(dukeText, img);
        db.flip();
        return db;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

}
