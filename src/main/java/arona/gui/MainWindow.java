package arona.gui;

import arona.Arona;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    @FXML
    private Stage stage;

    private Arona arona;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sensei.png"));
    private Image aronaImage = new Image(this.getClass().getResourceAsStream("/images/Arona.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setArona(Arona d) {
        arona = d;
    }

    public void setStage(Stage s) {
        stage = s;
        dialogContainer.getChildren().add(
                DialogBox.getAronaDialog(arona.enterArona(), aronaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Arona's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = arona.getResponse(input);
        if (response.equals(Arona.exitArona())) {
            stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAronaDialog(response, aronaImage)
        );
        userInput.clear();
    }
}