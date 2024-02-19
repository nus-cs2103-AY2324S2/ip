package duke.kbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    /** What is to be printed on the dialogue box. */
    private Label text;

    /** What is to be the display picture. */
    private ImageView displayPicture;

    /**
     * Constructor for a dialogue box.
     * 
     * @param label     Label of what the dialogue box will say.
     * @param imageView Image of the user/chatbot.
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;
        text.setWrapText(true);
        displayPicture.setFitWidth(75.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flipDialogueBox() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Makes a dialogue box for the user.
     * 
     * @param label     Label containing what user will say.
     * @param imageView Image of the user.
     * @return The dialogue box of the user.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Flips the dialogue box and return the chatbot output.
     * 
     * @param label     Label containing what chatbot will say.
     * @param imageView Image of the chatbot.
     * @return The dialogue box of the chatbot.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var dialogueBox = new DialogBox(label, imageView);
        dialogueBox.flipDialogueBox();
        return dialogueBox;
    }

}
