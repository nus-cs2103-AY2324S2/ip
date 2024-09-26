package duke.gui;

import java.io.IOException;
import java.util.Objects;

import duke.exceptions.DukeException;
import duke.fileUtils.FilePaths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class DialogBox extends HBox {

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private static final Image userImage = new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/images/DaUser.png")));
    private static final Image dukeImage = new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/images/DaDuke.png")));

    public DialogBox(String text, Image img, boolean isDuke) {
        try {
            String dialogBoxPath;
            if (isDuke) {
                dialogBoxPath = FilePaths.DUKE_DIALOG_BOX;
            } else {
                dialogBoxPath = FilePaths.USER_DIALOG_BOX;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(dialogBoxPath));
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

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, userImage, false);
    }

    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, dukeImage, true);
    }
}

