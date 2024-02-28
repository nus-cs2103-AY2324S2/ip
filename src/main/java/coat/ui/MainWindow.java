package coat.ui;

import coat.Coat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private Coat coat;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image coatImage = new Image(this.getClass().getResourceAsStream("/images/Coat.png"));
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/send.png"));


    /**
     * Initializes the DialogBox and display the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendButton.getStylesheets().add(getClass().getResource("/css/MainWindow.css").toExternalForm());
        sendButton.setGraphic(new ImageView(sendIcon));
    }

    public void setCoat(Coat d) {
        this.coat = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Coat's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty()) {
            return;
        }

        String response = coat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCoatDialog(response, coatImage)
        );
        userInput.clear();
    }
}
