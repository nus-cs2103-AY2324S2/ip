package dave.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import dave.Dave;
import dave.Ui;
import dave.gui.DialogBox;

// @@author iynixil-reused
//     from https://se-education.org/guides/tutorials/javaFx.html
//     (all parts of the tutorial) with minor modifications
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

    private Dave dave;
    private Ui daveUi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image daveImage = new Image(this.getClass().getResourceAsStream("/images/dave.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDave(Dave d) {
        dave = d;
        daveUi = new Ui();
    }

    public void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDaveDialog(String.format("%s\n%s", daveUi.sayHello(), dave.getLoadResult()), daveImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dave.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDaveDialog(response, daveImage));
        userInput.clear();
    }
}
