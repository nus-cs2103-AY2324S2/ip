package missminutes;

import java.util.Objects;

import javafx.application.Platform;
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

    private MissMinutes mm;

    private final Image userImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image mmImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaMissMinutes.png"))
    );

    /**
     * Initializes the UI for the MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getMissMinutesDialog(Ui.sayHello(), mmImage));
    }

    /**
     * Sets the MissMinutes object for the MainWindow
     * @param mm The MissMinutes object to be set
     */
    public void setMissMinutes(MissMinutes mm) {
        this.mm = mm;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing MissMinute's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mm.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMissMinutesDialog(response, mmImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().add(DialogBox.getMissMinutesDialog(Ui.sayBye(), mmImage));
            Platform.exit();
        }
    }
}
