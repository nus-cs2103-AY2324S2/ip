package duke.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Collections;

public class DukeDialogBox extends DialogBox {

    public DukeDialogBox(String text, Image img) {
        super(text, img);
        this.dialog.setText("");
        setLabelStyle("-fx-background-color: #0052F5; -fx-background-radius: 10px;", Color.WHITE);
        displayTextWithTypingEffect(text, 30);
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

    private void displayTextWithTypingEffect(String text, double letterInterval) {
        final StringBuilder displayedText = new StringBuilder();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(text.length());

        KeyFrame keyFrame = new KeyFrame(Duration.millis(letterInterval), event -> {
            if (displayedText.length() < text.length()) {
                displayedText.append(text.charAt(displayedText.length()));
                this.dialog.setText(displayedText.toString());
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

}
