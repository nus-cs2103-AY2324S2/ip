package jiayou.view;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import jiayou.function.Jiayou;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String SIGN_OF_ERROR = "OOPS!!!";
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
        String greeting = "Hellooooo!!! I'm Jiayou~~~!!!\nWhat can I do for you? > <\n"
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
            handleByeCommand();
        } else {
            handleNormalCommand(input);
        }
    }

    /**
     * Handles the bye command and exit.
     */
    private void handleByeCommand() {
        String response = "Bye. Hope to see you again soon! > <";
        createUserDialog("bye");
        userInput.clear();

        PauseTransition delayForJiayouResponse = new PauseTransition(Duration.seconds(0.5));
        delayForJiayouResponse.setOnFinished(event -> createJiayouDialog(response));
        delayForJiayouResponse.play();

        PauseTransition delayForExit = new PauseTransition(Duration.seconds(1));
        delayForExit.setOnFinished(event -> System.exit(0));
        delayForExit.play();
    }

    /**
     * Handles the normal input command other than bye.
     *
     * @param input the input command.
     */
    private void handleNormalCommand(String input) {
        createUserDialog(input);
        userInput.clear();

        String response = jiayou.getResponse(input);
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(event -> createJiayouDialog(response));
        delay.play();
    }

    /**
     * Creates a dialog box for Jiayou's response.
     *
     * @param input the response message to be included in the dialog box.
     */
    private void createJiayouDialog(String input) {
        DialogBox db = DialogBox.getJiayouDialog(input, jiayouImage);

        if (input.contains(SIGN_OF_ERROR)) {
            Label textLabel = db.getTextLabel();
            textLabel.setTextFill(Color.RED);
        }

        dialogContainer.getChildren().add(db);
    }

    /**
     * Creates a dialog box for user's response.
     *
     * @param input the response message to be included in the dialog box.
     */
    private void createUserDialog(String input) {
        DialogBox db = DialogBox.getUserDialog(input, userImage);
        dialogContainer.getChildren().add(db);
    }
}
