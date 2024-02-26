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
import tasklist.Michelle;
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

    private Michelle michelle;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image michelleImage = new Image(this.getClass().getResourceAsStream("/images/bear.jpg"));

    /** Initializes the chatbot */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getMichelleDialog(Ui.showWelcomeMessage(), michelleImage, null)
        );
    }

    public void setMichelle(Michelle d) {
        michelle = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Michelle's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        TaskList thelist = null;
        String input = userInput.getText();
        String response = getResponse(input);
        System.out.println(input);

        if (input.equalsIgnoreCase("viewschedule")) {
            thelist = michelle.getList();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMichelleDialog(response, michelleImage, thelist)
        );
        if (!michelle.isRunning()) {
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
        String response = this.michelle.processResponse(input);
        return response;
    }
}
