package wis.gui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import wis.ChatBox;

/**
 * A bridge between the GUI and the main Wis program.
 */
public class Bridge {
    private ChatBox chatBox = new ChatBox();

    public Bridge() {

    }

    public void link(VBox dialogContainer, Image duke) {
        String message = chatBox.launch();
        if (!message.equals("")) {
            Label errorText = new Label(message);
            dialogContainer.getChildren().addAll(
                    new DialogBox(errorText, new ImageView(duke))
            );
        }
    }

    public String getResponse(TextField userInput) {
        return chatBox.getResponse(userInput.getText());
    }

    public void quit() {
        chatBox.save();
    }
}
