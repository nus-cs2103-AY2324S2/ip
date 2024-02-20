package duke.graphics;

import java.util.Objects;

import duke.logic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



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

    private Logic logic;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/dave.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/hal9000.png")));
    /**
     * Creates a dialog box that greets the user based on the Logic class' greet method
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setUserInterface(Logic ui) {
        logic = ui;
    }
    /**
     * Creates a dialog box that greets the user based on the Logic class' greet method
     */
    @FXML
    public void greet() {
        String greeting = logic.greet();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greeting, dukeImage));
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = logic.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
