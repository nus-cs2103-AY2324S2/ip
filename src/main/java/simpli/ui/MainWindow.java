package simpli.ui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import simpli.commands.GreetCommand;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.core.Simpli;

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

    private Simpli simpli;

    private Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private Image simpliImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Simpli.jpg")));

    /**
     * Driver code for MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

    }

    public void setSimpli(Simpli simpli) {
        this.simpli = simpli;
        Ui ui = new Ui();
        dialogContainer.getChildren().add(DialogBox.getSimpliDialog(
                new GreetCommand(ui).execute(new String[] {}).toString(),
                simpliImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        CommandResult output = simpli.processInput(input);
        String response = output.toString();

        assert !response.isEmpty() : "chatbot response cannot be an empty String";
        if (output.getStatus().equals(CommandWord.BYE)) {
            dialogContainer.getChildren().add(
                    DialogBox.getSimpliDialog(simpli.bye(), simpliImage)
            );
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSimpliDialog(response, simpliImage)
        );
        userInput.clear();
    }
}
