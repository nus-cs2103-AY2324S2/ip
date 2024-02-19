package Gluti.Gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private ToggleButton themeToggleButton;
    static Media botSound = new Media(Objects.requireNonNull(DialogBox.class.getResource("/data/botNotif.wav")).toExternalForm());
    static MediaPlayer botMediaPlayer = new MediaPlayer(botSound);
    private static final Image userImage = new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/data/user.png")));
    private static final Image gluticon = new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/data/Gluticon.png")));

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
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


    public static DialogBox getUserDialog(String text) {
        DialogBox db = new DialogBox(text, userImage);
        db.setStyle("-fx-background-color: " + "#8bbcd9" + "; "
                + "-fx-background-radius: 10;");
        return db;
    }

    public static DialogBox getGlutiDialog(String text) {
        botMediaPlayer.stop();
        botMediaPlayer.play();
        assert text != null : "Text should not be empty!";
        var db = new DialogBox(text, gluticon);
        db.setStyle("-fx-background-color: " + "#a3d4c5" + "; "
                + "-fx-background-radius: 10;");
        db.flip();
        return db;
    }
}