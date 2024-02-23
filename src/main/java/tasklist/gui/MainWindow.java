package tasklist.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tasklist.Duke;
import tasklist.TaskList;
import tasklist.Ui;
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
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bear.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
        DialogBox.getDukeDialog(Ui.showWelcomeMessage(), dukeImage, null)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        TaskList thelist = null;
        String input = userInput.getText();
        String response = getResponse(input);
        System.out.println(input);

        if (input.equalsIgnoreCase("viewschedule")) {
            thelist = duke.getList();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage, thelist)
        );
        if (!duke.isRunning()) {
            userInput.setEditable(false);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event ->
                Platform.exit()
            );
            pause.play();
        }
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        String response = this.duke.processResponse(input);
        return response;
    }
}
