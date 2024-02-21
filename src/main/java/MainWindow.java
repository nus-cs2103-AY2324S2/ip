import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private Button todoButton;
    @FXML
    private Button deadlineButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button listButton;
    @FXML
    private Button markButton;
    @FXML
    private Button unmarkButton;
    @FXML
    private Button findButton;
    @FXML
    private Button clearListButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button deleteButton;

    private Riz riz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Theo.png"));
    private Image rizImage = new Image(this.getClass().getResourceAsStream("/images/Riz.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Label welcomeMessageLabel = new Label("Welcome to the best to-do list manager... RizBot...");
        dialogContainer.getChildren().add(DialogBox.getRizDialog(welcomeMessageLabel, new ImageView(rizImage)));
    }

    public void setRiz(Riz riz) {
        this.riz = riz;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText() + " ");
        Label RizText = new Label(riz.getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getRizDialog(RizText, new ImageView(rizImage))
    );
        userInput.clear();
    }

    @FXML
    private void handleToDoButton() {
        userInput.requestFocus();
        userInput.setText("todo ");
        userInput.positionCaret(5);
    }

    @FXML
    private void handleDeadlineButton() {
        userInput.requestFocus();
        userInput.setText("deadline  /by ");
        userInput.positionCaret(9);
    }

    @FXML
    private void handleEventButton() {
        userInput.requestFocus();
        userInput.setText("event  /from /to");
        userInput.positionCaret(6);
    }

    @FXML
    private void handleMarkButton() {
        userInput.requestFocus();
        userInput.setText("mark ");
        userInput.positionCaret(5);
    }
    @FXML
    private void handleUnmarkButton() {
        userInput.requestFocus();
        userInput.setText("unmark ");
        userInput.positionCaret(7);
    }

    @FXML
    private void handleListButton() {
        Label userText = new Label("list ");
        Label RizText = new Label(riz.getResponse("list"));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getRizDialog(RizText, new ImageView(rizImage))
        );
        userInput.clear();
    }

    @FXML
    private void handleClearListButton() {
        Label userText = new Label("clear ");
        Label RizText = new Label(riz.getResponse("clear"));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getRizDialog(RizText, new ImageView(rizImage))
        );
        userInput.clear();
    }

    @FXML
    private void handleFindButton() {
        userInput.requestFocus();
        userInput.setText("find ");
        userInput.positionCaret(5);
    }

    @FXML
    private void handleHelpButton() {
        Label userText = new Label("help ");
        Label RizText = new Label(riz.getResponse("help"));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getRizDialog(RizText, new ImageView(rizImage))
        );
        userInput.clear();
    }

    @FXML
    private void handleDeleteButton() {
        userInput.requestFocus();
        userInput.setText("delete ");
        userInput.positionCaret(7);
    }
}
