package cat.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A dialogue box of the conversation between the bot and the user.
 */
public class UserCommand extends HBox {
    @FXML
    private Label dialog;

    /**
     * A constructor for a dialog box.
     * @param text The dialogue text.
     */
    public UserCommand(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserCommand.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }
}
