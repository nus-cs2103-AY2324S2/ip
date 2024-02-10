package duke.ui;

import java.util.Scanner;
import java.util.function.Function;

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

public class Ui extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image loki = new Image(this.getClass().getResourceAsStream("/images/Loki.png"));
    private Image thor = new Image(this.getClass().getResourceAsStream("/images/Thor.png"));

    private Function<String, String> commandHandler;

    public Ui() {
    }

    public Ui(Function<String, String> commandHandler) {
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
        fluffySpeak(getResponse(userInputString));
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

    private String getResponse(String input) {
        return commandHandler.apply(input);
    }

    public void showWelcome() {
        fluffySpeak("Hello! I'm Fluffy, \nWhat can I do for you?");
    }

    public void setCommandHandler(Function<String, String> commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("OOPS!!! " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Creating new file...");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Shows all tasks.
     * @param tasks tasks to show.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (DukeException e) {
                showError(e.getMessage());
            }
        }
    }

    /**
     * Shows the tasks that match the search keyword.
     * @param tasks The list of tasks that match the search keyword.
     */
    public void showFoundTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                System.out.println((i + 1) + ". " + tasks.getTask(i));
            } catch (DukeException e) {
                showError(e.getMessage());
            }
        }
    }

    /**
     * Shows the task that was added to the task list.
     * @param task The task that was added to the task list.
     * @param newSize The new size of the task list.
     */
    public void showTaskAdded(Task task, int newSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Shows a deleted task.
     * @param task The task that was deleted.
     * @param newSize The new size of the task list.
     */
    public void showTaskDeleted(Task task, int newSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }
}
