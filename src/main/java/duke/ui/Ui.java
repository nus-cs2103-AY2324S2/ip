package duke.ui;

import java.util.function.Consumer;

import duke.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import javafx.application.Application;
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
import javafx.stage.Stage;

/**
 * Represents the user interface of Fluffy.
 */
public class Ui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image loki = new Image(this.getClass().getResourceAsStream("/images/Loki.png"));
    private Image thor = new Image(this.getClass().getResourceAsStream("/images/Thor.png"));

    private Consumer<String> commandHandler;

    public Ui() {
    }

    public Ui(Consumer<String> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Fluffy");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        showWelcome();
    }

    /**
     * Exits the program.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Handles the user input and displays the response.
     */
    public void handleUserInput() {
        String userInputString = userInput.getText();

        userSpeak(userInputString);
        commandHandler.accept(userInputString);

        userInput.clear();
    }

    /**
     * Displays the response from User.
     * @param message The response from User.
     */
    public void userSpeak(String message) {
        Label response = new Label(message);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(response, new ImageView(loki))
        );
    }

    /**
     * Displays the response from Fluffy.
     * @param message The response from Fluffy.
     */
    public void fluffySpeak(String message) {
        Label response = new Label(message);
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(response, new ImageView(thor))
        );
    }

    public void showWelcome() {
        fluffySpeak("Hello! I'm Fluffy, \nWhat can I do for you?");
    }

    public void showGoodbye() {
        fluffySpeak("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        fluffySpeak("OOPS!!! " + errorMessage);
    }

    public void showLoadingError() {
        fluffySpeak("Error loading file. Creating new file...");
    }

    public void showMessage(String message) {
        fluffySpeak(message);
    }

    /**
     * Displays the list of tasks.
     * @param tasks The list of tasks.
     */
    public void showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                sb.append((i + 1))
                    .append(". ")
                    .append(tasks.getTask(i))
                    .append("\n");
            } catch (DukeException e) {
                showError(e.getMessage());
            }
        }
        fluffySpeak(sb.toString());
    }

    /**
     * Displays the list of found tasks.
     * @param tasks The list of found tasks.
     */
    public void showFoundTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                sb.append((i + 1))
                    .append(". ")
                    .append(tasks.getTask(i))
                    .append("\n");
            } catch (DukeException e) {
                showError(e.getMessage());
            }
        }
        fluffySpeak(sb.toString());
    }

    public void showTaskAdded(Task task, int newSize) {
        fluffySpeak("Got it. I've added this task:\n" + task + "\nNow you have " + newSize + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int newSize) {
        fluffySpeak("Noted. I've removed this task:\n" + task + "\nNow you have " + newSize + " tasks in the list.");
    }
}
