package duke;

import java.io.IOException;
import java.util.Collections;

import duke.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents a custom control using FXML, representing a dialog box.
 * This control consists of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text, image, and speaker type.
     *
     * @param text   The text content of the dialog.
     * @param img    The image to be displayed.
     * @param isUser A boolean indicating if the speaker is the user.
     */
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

        setBoxAlignment(isUser);

        dialog.setStyle("-fx-background-color: #e9f7ff; "
                + "-fx-background-radius: 10; "
                + "-fx-padding: 10px; "
        );
    }

    private void setBoxAlignment(boolean isUser) {
        if (!isUser) {
            setAlignment(Pos.TOP_LEFT);
            getChildren().setAll(displayPicture, dialog);
            setSpacing(5);
        }
    }

    /**
     * Creates and returns a DialogBox representing a user message.
     *
     * @param text The text content of the user message.
     * @param img The image to be displayed in the user's dialog box.
     * @return A DialogBox instance representing the user message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates and returns a DialogBox representing a message from the chatbot.
     *
     * @param text The text content of the chatbot message.
     * @param img The image to be displayed in the chatbot's dialog box.
     * @return A DialogBox instance representing the chatbot message.
     */
    public static DialogBox getCookieDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        return db;
    }
}
