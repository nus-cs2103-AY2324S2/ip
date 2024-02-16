package duke.ui;

import javafx.scene.layout.AnchorPane;
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/userimg.jpg"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/sylviaimg.png"));

    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    private String getUserInput() {
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userInput.getText(), userImage));
        return userInput.getText();
    }

    @FXML
    private void handleUserInput() {
        String input = getUserInput();
        Response response = duke.runCommand(input);
        showResponse(response);
        userInput.clear();
    }

    public void showResponse(Response response) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response.toString(), dukeImage));
    }
}
