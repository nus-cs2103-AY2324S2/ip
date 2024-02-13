package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lelu.Lelu;
import lelu.Main;

public class MainWindow extends AnchorPane {

    public static boolean canExit = false;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lelu lelu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image leluImage = new Image(this.getClass().getResourceAsStream("/images/DaLelu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        greet();
    }

    public void setLelu(Lelu l) {
        lelu = l;
    }

    @FXML
    private void greet() {
        Ui ui = new Ui();
        String response = ui.greet();
        dialogContainer.getChildren().addAll(
                DialogBox.getLeluDialog(response, leluImage)
        );

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lelu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeluDialog(response, leluImage)
        );
        userInput.clear();
        if (canExit) {
            Main.exitApplication();
        }

    }

}
