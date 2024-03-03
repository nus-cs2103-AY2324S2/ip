package dude;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainView. Provides the layout for the other controls.
 */
public class MainView extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button sendButton;

    @FXML
    private TextField userInputField;

    @FXML
    private VBox dialogContainer;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dudeImage = new Image(this.getClass().getResourceAsStream("/images/dude.png"));

    private Dude dude;

    @FXML
    public void initialize() {
        dude = new Dude("data/tasks.ser");
    }

    @FXML
    public void handleUserInput() {
        String input = userInputField.getText();
        String response = dude.getResponse(input);

        System.out.println("User input: " + input);
        userInputField.clear();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dudeImage)
        );

        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });

        if (input.equals("bye")) {
            System.exit(0);
        }

    }

}
