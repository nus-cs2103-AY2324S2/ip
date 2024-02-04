package dino.command;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/** Represents the user interface interactions. */
public class Ui {

    /** Prints a greeting message. */
    public void welcome(VBox dialogContainer, Image dino) {
        String welcomeMessage = "Hola! I'm Dino.\nWhat are you doing here?";
        Label welcomeLabel = new Label(welcomeMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLabel, new ImageView(dino)));
    }

    /** Prints a goodbye message. */
    public void goodbye(VBox dialogContainer, Image user) {
        String goodbyeMessage = "Byeee";
        Label goodbyeLabel = new Label(goodbyeMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(goodbyeLabel, new ImageView(user)));
    }
}
