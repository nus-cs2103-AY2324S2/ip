package bond.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Benny Loh
 * @version 0.2
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

    private Bond bond;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Anya.jpeg"));
    private Image bondImage = new Image(this.getClass().getResourceAsStream("/images/Bond.jpeg"));

    /**
     * Sets up the window initially when program opens up.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        welcomeUser();
    }

    public void setBond(Bond d) {
        bond = d;
    }


    protected void tellUser(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getBondDialog(message, bondImage)
        );
    }

    protected void welcomeUser() {
        this.tellUser(bond.ui.showWelcome());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bond's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bond.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBondDialog(response, bondImage)
        );
        userInput.clear();

        if (response == "Bye. Hope to see you again soon!") {
            Main.exitApplication();
        }
    }

}

