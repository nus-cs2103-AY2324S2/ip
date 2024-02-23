package floofy;

import floofy.task.Deadline;
import floofy.task.Event;
import floofy.task.Task;
import floofy.task.ToDo;

import java.util.Scanner;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents the main class of the Floofy chat-bot application.
 */
public class Floofy extends Application{
    /** The storage object to handle the loading and saving of tasks. */
    private Storage storage;

    /** The parser object to handle the parsing of user input. */
    private Parser parser;

    /** The task list object to handle the list of tasks. */
    private TaskList tasks;

    /** The user interface object to handle the interaction with the user. */
    private Ui ui;

    /** The file path of the file to store the tasks. */
    private static final String FILE_PATH = "./data/duke.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Floofer.png"));
    private Image floofy = new Image(this.getClass().getResourceAsStream("/images/FloofBoss.png"));

    /**
     * Constructs a new object of the Floofy class.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Floofy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList();
            storage.loadTasks(tasks);
        } catch (FloofyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Floofy object with no arguments.
     * */
    public Floofy() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList();
            storage.loadTasks(tasks);
        } catch (FloofyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Floofy chat-bot application.
     */
    public void run() {
        ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        loop:
        while (true) {
            try {
                String userInput = scanner.nextLine();
                String[] input = parser.parse(userInput);
                switch (input[0]) {
                case "mark":
                    int idx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsDone(idx);
                    ui.showMarkedTask(this.tasks.getTask(idx));
                    storage.saveTasks(tasks);
                    continue;
                case "unmark":
                    int unmarkIdx = Integer.parseInt(input[1]) - 1;
                    tasks.markTaskAsUndone(unmarkIdx);
                    ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
                    storage.saveTasks(tasks);
                    continue;
                case "find":
                    TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
                    ui.showMatchingTasks(matchingTasks);
                    continue;
                case "todo":
                    String todoTask = input[1];
                    ToDo newTodo = new ToDo(todoTask);
                    tasks.addTask(newTodo);
                    ui.showAddedTask(newTodo, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "deadline":
                    String deadlineTask = input[1];
                    LocalDate deadlineBy = LocalDate.parse(input[2]);
                    Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                    tasks.addTask(newDeadline);
                    ui.showAddedTask(newDeadline, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "event":
                    String eventTask = input[1];
                    LocalDate eventDateFrom = LocalDate.parse(input[2]);
                    LocalDate eventDateTo = LocalDate.parse(input[3]);
                    Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                    tasks.addTask(newEvent);
                    ui.showAddedTask(newEvent, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "delete":
                    int deleteIdx = Integer.parseInt(input[1]) - 1;
                    Task deletedTask = tasks.getTask(deleteIdx);
                    tasks.deleteTask(deleteIdx);
                    ui.showDeletedTask(deletedTask, tasks.getSize());
                    storage.saveTasks(tasks);
                    continue;
                case "list":
                    ui.showTaskList(tasks);
                    continue;
                case "bye":
                    ui.showGoodbyeMsg();
                    scanner.close();
                    break loop;
                case "invalid":
                    throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
                }
            } catch (FloofyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method of the Floofy chat-bot application.
     */
    public static void main(String[] args) {
        new Floofy(FILE_PATH).run();
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
        stage.setTitle("Duke");
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

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String floofyText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getFloofyDialog(floofyText, floofy)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "FLOOFED: " + input;
    }
}
