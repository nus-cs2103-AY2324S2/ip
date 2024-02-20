package javassist.util;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a dialog box.
 */
public class DialogBox extends HBox {
    @FXML
    private TextFlow txt;
    @FXML
    private Circle displayPicture;
    @FXML
    private HBox box;

    private DialogBox(String text, Image img) {
        assert (img != null) : "Image is null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text t = new Text(text);
        t.setFont(Font.font("Maiandra GD", 14));
        t.setFill(Paint.valueOf("#321f43"));
        txt.getChildren().add(t);
        displayPicture.setFill(new ImagePattern(img));
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.box.setStyle("-fx-background-color: #AA99FF; -fx-border-radius: 10; -fx-border-color: #F5F3F8;"
                + "-fx-border-width: 1; -fx-background-radius: 10;");
        return db;
    }
}
