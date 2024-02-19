import command.CommandResponse;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private GeePeeTee geepeetee;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image neutralImage = new Image(this.getClass().getResourceAsStream("/images/bot_neutral.jpeg"));
    private Image happyImage = new Image(this.getClass().getResourceAsStream("/images/bot_happy.jpeg"));
    private Image unhappyImage = new Image(this.getClass().getResourceAsStream("/images/bot_unhappy.jpeg"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.setVvalue(0.0);

        // Styling
        scrollPane.setStyle("-fx-background: #010101;");
        dialogContainer.setStyle("-fx-background-color: #010101;");
        userInput.setStyle(
                "-fx-background-color: #010101; " +
                "-fx-text-fill: #ffffff; " +
                "-fx-font-family: 'Monospaced'; " +
                "-fx-font-size: 12pt"
        );
        dialogContainer.getChildren()
                .add(DialogBox.getGeePeeTeeDialog("Hello! I'm GeePeeTee. How can I help you?", neutralImage));
    }

    /**
     * Sets the GeePeeTee instance for the main window.
     * 
     * @param geepeetee The GeePeeTee instance to be set
     */
    public void setGeePeeTee(GeePeeTee geepeetee) {
        this.geepeetee = geepeetee;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the user input is "bye", the application will exit.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getGeePeeTeeDialog("Bye. Hope to see you again soon!", happyImage));
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> System.exit(0));
            pause.play();

        } else {
            CommandResponse response = geepeetee.getResponse(input);
            String responseString = response.getMessage();
            Boolean isError = response.isError();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    isError ? DialogBox.getGeePeeTeeDialog(responseString, unhappyImage)
                            : DialogBox.getGeePeeTeeDialog(responseString, happyImage));
            userInput.clear();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Adds a dialog box to the dialog container to display an error message when
     * an error occurs during initialization.
     * 
     * @param errorMessage The error message to be displayed
     */
    public void handleInitializationError(String errorMessage) {
        dialogContainer.getChildren().add(DialogBox.getGeePeeTeeDialog(errorMessage, unhappyImage));
    }
}
