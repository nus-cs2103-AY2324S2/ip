package duke.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Collections;

public class DukeDialogBox extends DialogBox {

    public DukeDialogBox(String text, Image img) {
        super("Sophia", text, img);
        setLabelStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 10px;", Color.BLACK);
        this.dialog.setText("");
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
        if (this.getChildren().size() == 2) {
            Node firstChild = this.getChildren().get(0);
            Node secondChild = this.getChildren().get(1);

            this.getChildren().clear();
            this.getChildren().addAll(secondChild, firstChild);

            if (firstChild instanceof VBox) {
                ((VBox) firstChild).setAlignment(Pos.CENTER_LEFT);
            }
            if (secondChild instanceof VBox) {
                ((VBox) secondChild).setAlignment(Pos.CENTER_RIGHT);
            }

        }
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
