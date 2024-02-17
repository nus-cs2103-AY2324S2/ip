import exception.TobiasException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.Ui;

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
    private MenuItem about;

    private Tobias tobias;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/michaelUser.jpeg"));
    private final Image tobiasImage = new Image(this.getClass().getResourceAsStream("/images/tobiasBot.jpeg"));

    public void setTobias(Tobias tobias) {
        this.tobias = tobias;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tobias's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
        String input = userInput.getText();
        String response = "";
        try {
            response = tobias.getResponse(input);
        } catch (TobiasException e) {
            response = e.printMessage();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTobiasDialog(response, tobiasImage)
        );

        userInput.clear();

        if (input.trim().equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

    @FXML
    private void handleAbout() {
        Alert aboutPopup = new Alert(Alert.AlertType.INFORMATION);
        aboutPopup.setTitle("About");
        aboutPopup.setHeaderText("Tobias Bot");
        aboutPopup.setContentText("A powerful text-based chat-bot to keep track of your life !!");

        aboutPopup.showAndWait();
    }

    @FXML
    private void handleHelp() {
        Alert aboutPopup = new Alert(Alert.AlertType.INFORMATION);
        aboutPopup.setTitle("Commands");
        aboutPopup.setHeaderText("Tobias Bot");

        String hello = "hello \n   I will reply accordingly";
        String list = "list \n   View your list of tasks";
        String todo = "todo {name} \n   Creates a todo";
        String deadline = "deadline {name} /by {dd-mm-yyyy HHMM} \n   Creates a deadline with date & time";
        String event = "event {name} /from {dd-mm-yyyy HHMM} /to {dd-mm-yyyy HHMM} \n   Creates an event with date & time";
        String delete = "delete {task number} \n   Deletes this task";
        String mark = "mark {task number} \n   Marks this task as done";
        String unmark = "unmark {task number} \n   Un-marks this task as done";
        String tag = "tag {task number} {your tag} \n   Adds a tag to the task";
        String untag = "untag {task number} {tag number} \n   Removes that tag from the task";
        String find = "find {keyword} \n   Finds and returns tasks with those keywords";
        String save = "save \n   Saves your tasks to the local data";
        String bye = "bye \n   Saves and exits the app";

        String info = hello
                + System.lineSeparator()
                + System.lineSeparator()
                + list
                + System.lineSeparator()
                + System.lineSeparator()
                + todo
                + System.lineSeparator()
                + System.lineSeparator()
                + deadline
                + System.lineSeparator()
                + System.lineSeparator()
                + event
                + System.lineSeparator()
                + System.lineSeparator()
                + delete
                + System.lineSeparator()
                + System.lineSeparator()
                + mark
                + System.lineSeparator()
                + System.lineSeparator()
                + unmark
                + System.lineSeparator()
                + System.lineSeparator()
                + tag
                + System.lineSeparator()
                + System.lineSeparator()
                + untag
                + System.lineSeparator()
                + System.lineSeparator()
                + find
                + System.lineSeparator()
                + System.lineSeparator()
                + save
                + System.lineSeparator()
                + System.lineSeparator()
                + bye;

        aboutPopup.setContentText(info);

        aboutPopup.showAndWait();
    }

    public void initialize() {
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        String result = Ui.printDivider()
                + System.lineSeparator()
                + "   Hello there ! I'm Tobias Funke !!"
                + System.lineSeparator()
                + "   What can I do for you today ?"
                + System.lineSeparator()
                + Ui.printDivider();

        dialogContainer.getChildren().addAll(
                DialogBox.getTobiasDialog(result, tobiasImage)
        );
    }
}
