package cat.ui;

import cat.Cat;
import cat.ui.response.Response;
import cat.ui.response.WelcomeResponse;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * The main window of the GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Cat cat;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(new WelcomeResponse());
    }

    public void setDuke(Cat d) {
        cat = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = cat.getResponse(input);
        dialogContainer.getChildren().addAll(
                new UserCommand(input),
                response
        );

        userInput.clear();
    }
}
