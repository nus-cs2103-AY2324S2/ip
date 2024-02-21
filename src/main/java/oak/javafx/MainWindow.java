package oak.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import oak.controller.OakController;
import oak.feedback.enums.CommandEnum;
import oak.task.ReminderService;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends VBox {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private OakController oak;
    private ReminderService reminderService = new ReminderService();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/oak_dex.png"));
    private Image oakImage = new Image(this.getClass().getResourceAsStream("/images/Oak.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String welcomeMessage = "Welcome to Oak-Dex!\n" + reminderService.getReminders();
        dialogContainer.getChildren().add(DialogBox.getOakDialog(welcomeMessage, oakImage));
    }

    public void setOak(OakController d) {
        oak = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Oak's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // @@author SherisseTJW-reused
        // Reused from https://stackoverflow.com/a/13602324
        // with minor modifications
        if (input.equals(CommandEnum.BYE.getCommandValue())) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }

        String response = this.oak.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOakDialog(response, oakImage)
        );
        userInput.clear();
    }
}
