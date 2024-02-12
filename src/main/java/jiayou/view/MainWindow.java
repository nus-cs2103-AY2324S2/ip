package jiayou.view;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import jiayou.function.Jiayou;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Jiayou jiayou;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/JiaUser.PNG"));
    private Image jiayouImage = new Image(this.getClass().getResourceAsStream("/images/JiaJiayou.PNG"));

    /**
     * Initializes the main window and prints out the greeting message.
     */
    @FXML
    public void initialize() {
        String greeting = "Hello! I'm Jiayou.\nWhat can I do for you? > <"
                   + "\n(Enter 'help' to know all my functionalities!)";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getJiayouDialog(greeting, jiayouImage)
        );
    }

    public void setJiayou(Jiayou jiayou) {
        this.jiayou = jiayou;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jiayou's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String response = "Bye. Hope to see you again soon! > <";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getJiayouDialog(response, jiayouImage)
            );
            userInput.clear();
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> System.exit(0));
            delay.play();
        } else {
            String response = jiayou.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(input, userImage)
            );
            userInput.clear();
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
            delay.setOnFinished(event -> {
                dialogContainer.getChildren().add(
                        DialogBox.getJiayouDialog(response, jiayouImage)
                );
            });
            delay.play();
        }
    }
}
