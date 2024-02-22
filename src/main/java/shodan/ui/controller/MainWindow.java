package shodan.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import shodan.Shodan;

/**
 * The main view for the application.
 */
public class MainWindow {
    @FXML
    private VBox dialogContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button sendButton;
    @FXML
    private TextField userInput;

    private Shodan shodan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image shodanImage = new Image(this.getClass().getResourceAsStream("/images/shodan.jpeg"));

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets shodan.
     *
     * @param shodan the shodan
     */
    public void setShodan(Shodan shodan) {
        this.shodan = shodan;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }
        String response = shodan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getShodanDialog(response, shodanImage)
        );
        userInput.clear();
    }

    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }
}
