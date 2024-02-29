package chillchief;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import util.TextUi;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ChillChief chillChief;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image chillChiefImage = new Image(this.getClass().getResourceAsStream("/images/chillchief.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        TextUi textUi = new TextUi();

        String welcomeMessage = textUi.showIntroMessage();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(welcomeMessage, chillChiefImage));
    }

    public void setChillChief(ChillChief chillChief) {
        this.chillChief = chillChief;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chillChief.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChillChiefDialog(response, chillChiefImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}
