package chaterpillar.launcher;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Label help;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox messageBox;

    /**
     * Basic constructor for this class
     *
     * @param text to be placed in the <code>Label</code>
     * @param img to be placed in the <code>ImageView</code>
     */
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
        setAlignment(Pos.CENTER_LEFT);
        messageBox.setBackground(
                new Background(new BackgroundFill(Paint.valueOf("MINTCREAM"),
                        new CornerRadii(10), null)));
    }

    /**
     * Gets a <code>DialogBox</code> designed for the user.
     *
     * @param text to be placed in the <code>Label</code>
     * @param img to be placed in the <code>ImageView</code>
     * @return <code>DialogBox</code>
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.messageBox.setBackground(
                new Background(new BackgroundFill(Paint.valueOf("AZURE"),
                        new CornerRadii(10), null)));
        return db;
    }

    /**
     * Gets a <code>DialogBox</code> designed for the ChatBot.
     *
     * @param text to be placed in the <code>Label</code>
     * @param img to be placed in the <code>ImageView</code>
     * @return <code>DialogBox</code>
     */
    public static DialogBox getChaterpillarDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Gets a default start <code>DialogBox</code> designed for the ChatBot.
     *
     * @param text to be placed in the <code>Label</code>
     * @param img to be placed in the <code>ImageView</code>
     * @return <code>DialogBox</code>
     */
    public static DialogBox getInitialiseChaterpillarDialog(String text, Image img) {
        var db = getChaterpillarDialog(text, img);
        db.help.setText("'help' for commands");
        return db;
    }

    /**
     * Gets a <code>DialogBox</code> designed for ChatBot Exceptions.
     *
     * @param text to be placed in the <code>Label</code>
     * @param img to be placed in the <code>ImageView</code>
     * @return <code>DialogBox</code>
     */
    public static DialogBox getExceptionDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.messageBox.setBackground(new Background(
                new BackgroundFill(Paint.valueOf("LIGHTPINK"),
                        new CornerRadii(10), null)));
        return db;
    }
}
