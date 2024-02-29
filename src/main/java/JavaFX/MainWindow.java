package JavaFX;
import LeBron.LeBron;
import actions.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private LeBron leBron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ugly.png"));
    private Image LeBronImage = new Image(this.getClass().getResourceAsStream("/images/monkey.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setSpacing(10);
        Ui ui = new Ui();
        String intro = ui.Intro();
        dialogContainer.getChildren().add(DialogBox.getLeBronDialog(intro, LeBronImage));
    }

    public void setLeBron(LeBron d) {
        this.leBron = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = leBron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLeBronDialog(response, LeBronImage)
        );
        userInput.clear();

        if(input.trim().equalsIgnoreCase("bye")) {
            System.out.println(" Bye. Hope to see you again soon!");
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        }
    }
}

