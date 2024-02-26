package yarr.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yarr.Yarr;

/**
 * Controller for MainWindow.
 */
public class MainWindow extends AnchorPane {
    /** The scroll pane to contain the dialog */
    @FXML
    private ScrollPane scrollPane;
    /** The vertical box to contain the dialog boxes */
    @FXML
    private VBox dialogContainer;
    /** The text field to contain the user input */
    @FXML
    private TextField userInput;
    /** The button to send the user input */
    @FXML
    private Button sendButton;

    /** The Yarr object to be used in the application */
    private Yarr yarr;

    /** The image to represent the user */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImg.png"));
    /** The image to represent Yarr */
    private Image yarrImage = new Image(this.getClass().getResourceAsStream("/images/YarrImg.png"));

    /**
     * Initializes the scroll pane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.getStylesheets().add(this.getClass().getResource("/styles/scrollbar.css").toExternalForm());
        scrollPane.setFocusTraversable(false);
    }

    /**
     * Sets the Yarr object to be used in the application.
     *
     * @param y a Yarr object representing the Yarr object to be used in the application
     */
    public void setYarr(Yarr y) {
        yarr = y;
        dialogContainer.getChildren().add(DialogBox.getYarrDialog(y.showWelcome(), yarrImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yarr's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yarr.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYarrDialog(response, yarrImage)
        );
        userInput.clear();
        if (yarr.isExit()) {
            System.exit(0);
        }
    }
}
