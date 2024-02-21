package checkbot;

import java.util.function.Consumer;

import checkbot.component.DialogBox;
import checkbot.exception.CheckbotException;
import checkbot.task.Task;
import checkbot.task.TodoList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Handles printing of messages to the user.
 */
public class Ui {
    private final Consumer<String> inputHandler;
    private final Image checkbotAvatar;
    private final Image userAvatar;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    /**
     * Constructor for Ui.
     *
     * @param checkbotAvatar The Image object for Checkbot's avatar.
     * @param userAvatar     The Image object for user avatar.
     */
    public Ui(Image checkbotAvatar,
              Image userAvatar,
              Consumer<String> inputHandler) {
        this.checkbotAvatar = checkbotAvatar;
        this.userAvatar = userAvatar;
        this.inputHandler = inputHandler;
    }

    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        printCheckbotMessage(
                "Hello, I'm Checkbot, your personal assistant.\n"
                        + "What tasks do you have to do?");
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbye() {
        printCheckbotMessage("Goodbye!");
    }

    /**
     * Prints the TodoList to the console.
     *
     * @param todoList The todolist in question.
     */
    public void printList(TodoList todoList) {
        printCheckbotMessage("Here is your todo list:\n"
                + todoList);
    }

    /**
     * Shows the message to the user when a task is added to the list.
     *
     * @param task   The task that was added.
     * @param length The number of tasks in the list.
     */
    public void showAddedTaskMessage(Task task, int length) {
        printCheckbotMessage("I have added this task to the list:\n"
                + task + "\n"
                + "You have now " + length + " task"
                + (length > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows the message to the user when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTaskMessage(Task task) {
        printCheckbotMessage("Good job! I have marked this task as completed:\n"
                + task);
    }

    /**
     * Shows the message to the user when a task is marked as incomplete.
     *
     * @param task The task that was marked as incomplete.
     */
    public void showUnmarkedTaskMessage(Task task) {
        printCheckbotMessage("Alright, I have marked this task as incomplete:\n"
                + task);
    }

    /**
     * Shows the message to the user when a task is deleted from the list.
     *
     * @param task   The task that was deleted.
     * @param length The number of tasks in the list.
     */
    public void showDeletedTaskMessage(Task task, int length) {
        printCheckbotMessage("Alright, I deleted this task:\n"
                + task + "\n"
                + "You have now " + length + " task"
                + (length > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Shows the error message to the user.
     *
     * @param e The exception that was thrown.
     */
    public void showError(CheckbotException e) {
        printCheckbotMessage(e.getMessage());
    }

    /**
     * Prints the given sublist to the user.
     *
     * @param subList The sublist containing tasks that matches the user's search query.
     */
    public void showSubList(TodoList subList) {
        if (subList.getLength() == 0) {
            printCheckbotMessage("You have no tasks in your list matching your query!");
            return;
        }
        printCheckbotMessage(
                "Here are the matching tasks in your list:\n"
                        + subList
        );
    }

    /**
     * Prints the given string to the GUI as a message from Checkbot.
     *
     * @param str The string to be printed.
     */
    protected void printCheckbotMessage(String str) {
        dialogContainer.getChildren().addAll(
                DialogBox.getCheckbotDialog(new Label(str), new ImageView(checkbotAvatar))
        );
    }

    /**
     * Sets up the Scene object for the first time.
     *
     * @return A Scene object with the welcome message.
     */
    public Scene initializeScene() {
        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        AnchorPane mainWindow = setupMainWindow();

        //Step 2. Formatting the window to look as expected
        formatWindow(mainWindow);

        return new Scene(mainWindow);
    }

    private void formatWindow(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400, 555);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private AnchorPane setupMainWindow() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return mainLayout;
    }

    protected void handleUserInput() {
        if (userInput.getText().isEmpty()) {
            return;
        }
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(userAvatar))
        );
        userInput.clear();

        inputHandler.accept(input);
    }
}
