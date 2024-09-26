package wis.gui;

import javafx.scene.control.TextArea;
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
    private WisApp wisApp;

    public Bridge(WisApp wisApp) {
        this.wisApp = wisApp;
    }

    /**
     * Link the GUI to the main chatBox program which processes user input and
     * generates output.
     *
     * @param dialogContainer Visual container displaying dialog between user
     *                        and Wis.
     * @param wisImage Profile image of Wis.
     */
    public void link(VBox dialogContainer, Image wisImage) {
        String message = chatBox.launch();
        if (!message.equals("")) {
            // display fail-to-load error message to user
            TextArea errorText = new TextArea(message);
            dialogContainer.getChildren().addAll(
                    new DialogBox(errorText, new ImageView(wisImage)));
        } else {
            wisApp.displayWelcomeMessage(chatBox);
        }
    }

    public String getResponse(TextField userInput) {
        return chatBox.getResponse(userInput.getText());
    }

    public void quit() {
        chatBox.save();
    }
}
