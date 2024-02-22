package liv.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import liv.Liv;
import liv.exception.LivException;

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

    private Liv liv = new Liv("./data/Liv.txt");

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image livImage = new Image(this.getClass().getResourceAsStream("/images/liv.png"));

    private static final String GREETING_MESSAGE = String.join("\n",
            "Liv, under your instructions!", "What is your command?");


    @FXML
    public void initialize() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getLivDialog(GREETING_MESSAGE, livImage)
        );
    }

    public void setLiv(Liv l) {
        this.liv = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws LivException {
        String input = userInput.getText();
        assert input != null: "Command cannot be empty!";
        String response = liv.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLivDialog(response, livImage)
        );
        userInput.clear();
    }
}
