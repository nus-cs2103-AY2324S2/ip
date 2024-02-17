package fluffy.ui;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import fluffy.FluffyException;
import fluffy.task.Task;
import fluffy.tasklist.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

    private final String lokiFilePath = "/images/Loki.png";
    private final String thorFilePath = "/images/Thor.png";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image loki;
    private Image thor;
    private Consumer<String> commandHandler;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.loki = getImageFromPath(lokiFilePath);
        this.thor = getImageFromPath(thorFilePath);
    }

    /**
     * Constructor for Ui.
     * @param commandHandler The command handler to handle the user input.
     */
    public Ui(Consumer<String> commandHandler) {
        this();
        this.commandHandler = commandHandler;
    }

    public Image getImageFromPath(String path) {
        InputStream resourceAsStream;
        resourceAsStream = this.getClass().getResourceAsStream(path);
        assert resourceAsStream != null;
        return new Image(resourceAsStream);
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
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.prefWidthProperty().bind(scene.widthProperty());
        mainLayout.prefHeightProperty().bind(scene.heightProperty());

        scrollPane.prefWidthProperty().bind(mainLayout.widthProperty());
        scrollPane.prefHeightProperty().bind(mainLayout.heightProperty().subtract(35));

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.prefWidthProperty().bind(mainLayout.widthProperty().subtract(55));

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

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
            } catch (FluffyException e) {
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
            } catch (FluffyException e) {
                showError(e.getMessage());
            }
        }
        fluffySpeak(sb.toString());
    }

    /**
     * Shows the task that was added to the task list.
     * @param task The task that was added to the task list.
     * @param newSize The new size of the task list.
     */
    public void showTaskAdded(Task task, int newSize) {
        fluffySpeak("Got it. I've added this task:\n" + task + "\nNow you have " + newSize + " tasks in the list.");
    }

    /**
     * Shows a deleted task.
     * @param task The task that was deleted.
     * @param newSize The new size of the task list.
     */
    public void showTaskDeleted(Task task, int newSize) {
        fluffySpeak("Noted. I've removed this task:\n" + task + "\nNow you have " + newSize + " tasks in the list.");
    }

    /**
     * Shows a piechart of the tasks.
     */
    public void showPieChart(HashMap<String, Integer> data) {
        StringBuilder sb = new StringBuilder("Here is a pie chart of your tasks:\n");
        PieChart pieChart = new PieChart();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            pieChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }
        fluffySpeak(sb.toString());
        dialogContainer.getChildren().addAll(
            pieChart
        );
    }
}
