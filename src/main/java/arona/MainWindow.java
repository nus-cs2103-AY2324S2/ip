package arona;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for arona.MainWindow. Provides the layout for the other controls.
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

    private Arona arona;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Shiroko_Icon.png"));
    private Image aronaImage = new Image(this.getClass().getResourceAsStream("/images/Arona_Icon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setArona(Arona arona) {
        this.arona = arona;
        showGreetings();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String fullCommand = userInput.getText();
        arona.run(fullCommand);
        String response = arona.getResponse();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(fullCommand, userImage),
                DialogBox.getAronaDialog(response, aronaImage)
        );
        userInput.clear();

        if (fullCommand == "bye") {
            stage.close();
        }
    }

    private void showGreetings() {
        arona.greetUser();
        dialogContainer.getChildren().add(
                DialogBox.getAronaDialog(arona.getResponse(), aronaImage)
        );
    }
}