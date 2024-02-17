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
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
        String info = "You can ask me to do the following quests for you, mate:" +
                "\n\n" +
                "hello: I will aptly reply!" +
                "\n\n" +
                "list: I will print the list of tasks you have at the moment, sire..." +
                "\n\n" +
                "todo: I will create a todo:" +
                "\n" +
                "todo conquer the world" +
                "\n\n" +
                "deadline: I will create a deadline with, well... a deadline:" +
                "\n" +
                "deadline Issue a worldwide threat /by 15-02-2024 1600" +
                "\n\n" +
                "event: I will create an event with 'from' and 'to':" +
                "\n" +
                "event World Domination /from 11-02-2024 1000 /to 22-02-2030 2359" +
                "\n\n" +
                "mark: Suppose you finish a task, I can mark it if you give me the task index:" +
                "\n" +
                "mark 1" +
                "\n\n" +
                "unmark: Suppose you un-finished a task, I can unmark it if you give me the task index:" +
                "\n" +
                "unmark 1" +
                "\n\n" +
                "delete: If you want to ELIMINATE a task, give me the task index and watch it burn:" +
                "\n" +
                "delete 1" +
                "\n\n" +
                "find: Give me the task index and I will find it:" +
                "\n" +
                "find ham" +
                "\n\n" +
                "tag: You can add some unique tags to some tasks (you can use this command repeatedly for a task):" +
                "\n" +
                "tag 1 urgent" +
                "\n\n" +
                "untag: You can remove a tag of a task by providing the taskIndex followed by tagIndex:" +
                "\n" +
                "untag 1 2";

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
