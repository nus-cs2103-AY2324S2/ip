package GUI;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class DialogBox extends HBox {

    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox dialog;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        Platform.runLater(() -> scrollPane.setVvalue(0.0));
    }

    private DialogBox(ArrayList<String> texts, Image img, String chatter) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Label currChatter = new Label(chatter + " said:");
        dialog.getChildren().add(currChatter);

        for (String i : texts) {
            Label message = new Label(i);
            dialog.getChildren().add(message);
        }
        Circle clip = new Circle(displayPicture.getFitWidth() / 2,
                displayPicture.getFitHeight() / 2, displayPicture.getFitWidth() / 2);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(ArrayList<String> texts, Image img) {
        return new DialogBox(texts, img, "You");
    }

    public static DialogBox getDukeDialog(ArrayList<String> texts, Image img) {
        var db = new DialogBox(texts, img, "Alfred");
        db.flip();
        return db;
    }
}
