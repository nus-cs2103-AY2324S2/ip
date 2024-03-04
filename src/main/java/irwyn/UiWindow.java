package irwyn;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * This is for the MainWindow. Provides the layout for the other controls.
 */
public class UiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Irwyn irwyn;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private final Image irwynImage = new Image(this.getClass().getResourceAsStream("/images/irwyn.jpg"));

    /** Initializes the main window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Instantiates the Irwyn object.
     *
     * @param i Irwyn object to be assigned.
     */
    public void setIrwyn(Irwyn i) {
        this.irwyn = i;
    }

    /**
     * Creates 2 dialog boxes for user input and Irwyn.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = irwyn.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getIrwynDialog(response, irwynImage)
        );
        userInput.clear();
    }
}
