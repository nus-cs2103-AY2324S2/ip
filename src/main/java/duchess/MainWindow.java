package duchess;

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
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpeg"));
    private final Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/DaDuchess.jpeg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duchess duchess;
    private boolean canClose = false;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); //Uses Streams
    }

    public void setDuchess(Duchess d) {
        duchess = d;
        dialogContainer.getChildren().add(DialogBox.getDuchessDialog(d.getOpeningGreeting(), duchessImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Sets dialog box to can close only if last message is bye.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            assert userImage != null : "User image is null";
            assert duchessImage != null : "Duchess image is null";
            String input = userInput.getText();
            String response = duchess.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDuchessDialog(response, duchessImage)
            );

            if (input.equals("bye")) {
                setCanClose(true);
            } else {
                setCanClose(false); // Set canClose to false for any other input
                duchess.restartDuchess();
            }

            userInput.clear();

        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the flag indicating whether the window can be closed.
     *
     * @param value true if the window can be closed, false otherwise
     */
    public void setCanClose(boolean value) {
        canClose = value;
    }

    public boolean canClose() {
        return canClose;
    }
}
