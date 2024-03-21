package duke.Ui;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * Custom control using FXML. This control represents a dialog box with an ImageView for the speaker's face
 * and a label with text.
 */
public class DialogBox extends HBox {

    @FXML
    private TextFlow dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
        displayPicture.setEffect(new DropShadow(30, Color.DARKRED));
        Text temp = new Text(text);
        temp.setFill(Color.WHITE);
        dialog.getChildren().add(temp);


        fadeInTransition(this);
        applyHoverEffect(this);

        slideInFromRight(this);
        applyPulseEffect(this);
    }

    private void fadeInTransition(Node node) {
        FadeTransition fade = new FadeTransition(Duration.millis(500), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void applyHoverEffect(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> node.setEffect(new DropShadow(20, Color.CHARTREUSE)));
        node.addEventHandler(MouseEvent.MOUSE_EXITED, e -> node.setEffect(new DropShadow(30, Color.DARKRED)));
    }

    private void slideInFromRight(Node node) {
        TranslateTransition slide = new TranslateTransition(Duration.millis(500), node);
        slide.setFromX(300);
        slide.setToX(0);
        slide.play();
    }

    private void applyPulseEffect(Node node) {
        ScaleTransition pulse = new ScaleTransition(Duration.millis(1000), node);
        pulse.setFromX(1);
        pulse.setFromY(1);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setCycleCount(ScaleTransition.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
