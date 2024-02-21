package unim.io;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import unim.application.DialogBox;
import unim.application.Parser;
import unim.application.TaskList;
import unim.application.Unim;

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

    private Unim unim;
    private TaskList taskList;
    private Parser parser;

    private Image nervousUnicorn = new Image(this.getClass().getResourceAsStream("/images/nervousUnicorn.png"));

    private Image happyUnicorn = new Image(this.getClass().getResourceAsStream("/images/happyUnicorn.png"));

    /**
     * Initializes the MainWindow layout and binds the vertical scroll property to the dialog container height.
     */
    @FXML
    public void initialize() {
        dialogContainer.setStyle("-fx-background-color: #fff5fd;");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Unim instance for the MainWindow.
     *
     * @param unim The Unim instance to be set.
     */
    public void setUnim(Unim unim) {
        this.unim = unim;
        this.taskList = taskList;
        this.parser = parser;
    }

    /**
     * Displays a welcome message in the dialog container when the MainWindow is initialized.
     */
    public void showWelcomeMessage() {
        String welcomeText = Ui.showWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getUnimDialog(welcomeText, happyUnicorn));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Unim's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = unim.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, nervousUnicorn),
                DialogBox.getUnimDialog(response, happyUnicorn)
        );
        userInput.clear();
    }

}
