package dude.gui;

import dude.Dude;
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
    private static final String USER_IMAGE_PATH = "/images/user.png";
    private static final String DUDE_IMAGE_PATH = "/images/dude.png";
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button sendButton;

    @FXML
    private TextField userInputField;

    @FXML
    private VBox dialogContainer;

    private Image userImage;
    private Image dudeImage;
    private Dude dude;

    /**
     * Initializes the MainView, setting up the images and the Dude object.
     */
    @FXML
    public void initialize() {
        this.dude = new Dude("data/tasks.ser");
        this.userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
        this.dudeImage = new Image(this.getClass().getResourceAsStream(DUDE_IMAGE_PATH));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dude's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInputField.getText();
        if (input.equals("bye")) {
            System.exit(0);
        }

        String response = dude.getResponse(input);
        ;
        userInputField.clear();

        showInputAndResponse(input, response);
        scrollDown();
    }

    private void scrollDown() {
        dialogContainer.heightProperty().addListener((observable) -> {
            scrollPane.setVvalue(1.0);
        });
    }

    private void showInputAndResponse(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dudeImage)
        );
    }

}
