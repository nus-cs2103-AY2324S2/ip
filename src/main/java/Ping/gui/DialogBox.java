package ping.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
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

        // Set text format based on the type of dialog box
        if (isUser) {
            dialog.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: Bungee;");
        } else {
            dialog.setStyle("-fx-text-fill: black; -fx-font-family: Calibre;");
        }

        // Set background color based on the type of dialog box
        if (isUser) {
            this.setStyle("-fx-background-color: #2C5F2D; -fx-background-radius: 10; -fx-padding: 10;"
                + "-fx-border-radius: 10; -fx-border-color: #97BC62FF; -fx-border-width: 2; -fx-border-insets: 2; "
                + "-fx-border-style: solid; -fx-border-radius: 10; -fx-border-color: #97BC62FF; -fx-border-width: 2; "
                + "-fx-border-insets: 2; -fx-border-style: solid; -fx-border-radius: 10; -fx-border-color: #97BC62FF; "
                + "-fx-border-width: 2; -fx-border-insets: 2; -fx-border-style: solid; -fx-border-radius: 10; "
                + "-fx-border-color: #97BC62FF; ");
        } else {
            this.setStyle("-fx-background-color: #97BC62FF; -fx-background-radius: 10; -fx-padding: 10;"
                + "-fx-border-radius: 10; -fx-border-color: #2C5F2D; -fx-border-width: 2; -fx-border-insets: 2; "
                + "-fx-border-style: solid; -fx-border-radius: 10; -fx-border-color: #2C5F2D; -fx-border-width: 2; "
                + "-fx-border-insets: 2; -fx-border-style: solid; -fx-border-radius: 10; -fx-border-color: #2C5F2D; "
                + "-fx-border-width: 2; -fx-border-insets: 2; -fx-border-style: solid; -fx-border-radius: 10; "
                + "-fx-border-color: #2C5F2D;");
        }
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

    public static DialogBox getUserDialog(String text, Image img) {
        boolean isUser = true;
        return new DialogBox(text, img, isUser);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        boolean isUser = false;
        var db = new DialogBox(text, img, isUser);
        db.flip();
        return db;
    }
}

