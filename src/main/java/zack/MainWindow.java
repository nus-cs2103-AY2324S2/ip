package zack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zack.util.TaskList;

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

    private Zack zack;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image zackImage = new Image(this.getClass().getResourceAsStream("/images/zack.jpg"));

    /**
     * Initializes the JavaFX controller. Binds the vertical scroll position of the scrollPane
     * to the height of the dialogContainer and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Displays a welcome message from Zack in the conversation dialog.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Zack\nWhat's on your mind?";
        dialogContainer.getChildren().add(DialogBox.getZackDialog(welcomeMessage, zackImage));
    }

    /**
     * Displays tasks retrieved from the Zack instance in the conversation dialog.
     * Throws a ZackException if there is an issue with Zack.
     *
     * @throws ZackException If there is an issue with Zack while retrieving tasks.
     */
    public void displayTasks() throws ZackException {
        TaskList tasks = zack.getTasks();
        StringBuilder taskInfoBuilder = new StringBuilder();

        if (tasks.getSize() == 0) {
            taskInfoBuilder.append("Don't worry we've got the file, it's just currently empty!");
        } else {
            taskInfoBuilder.append("Here are the tasks that I have loaded from storage:\n");
            for (int i = 0; i < tasks.getSize(); i++) {
                String taskInfo = (i + 1) + "." + tasks.getTask(i).toString();
                taskInfoBuilder.append(taskInfo).append("\n");
            }
        }
        dialogContainer.getChildren().add(DialogBox.getZackDialog(taskInfoBuilder.toString(), zackImage));
    }


    public void setZack(Zack z) throws ZackException {
        zack = z;
        displayTasks(); // Display tasks as soon as Zack is set
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zack's reply and then appends
     * them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zack.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZackDialog(response, zackImage)
        );
        userInput.clear();
    }
}

