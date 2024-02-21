package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main class for the Duke application.
 * This class initializes the application, loads existing tasks from storage, and processes user commands.
 */

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a new Duke application instance. Initializes the UI, storage, and task list components.
     * Tries to loadFromFile any existing tasks from storage; if unsuccessful, starts with an empty task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application. Enters a loop to read and execute commands until the user issues the 'bye' command.
     */
    public void run() {
        ui.showHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String[] fullCommand = Parser.parse(ui.readCommand());
                String command = fullCommand[0];

                if (command.equals("bye")) {
                    ui.showBye();
                    isExit = true;
                    break;
                } else if (command.equals("list")) {
                    ui.showList(tasks);
                } else if (command.equals("mark") || command.equals("unmark") || command.equals("todo")
                        || command.equals("deadline") || command.equals("event") || command.equals("delete")) {
                    if (fullCommand.length > 1) {
                        if (command.equals("mark")) {
                            int indexToMark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToMark);
                            task.markComplete();
                            ui.showTask(" Nice! I've marked this task as done:", task);
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("unmark")) {
                            int indexToUnmark = Integer.parseInt(fullCommand[1]) - 1;
                            Task task = tasks.getTasks().get(indexToUnmark);
                            task.markIncomplete();
                            ui.showTask(" OK, I've marked this task as not done yet:", task);
                            storage.saveToFile(tasks.getTasks());
                        } else if (command.equals("todo")) {
                            Task task = new ToDo(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum(" Got it. I've added this task:\n  ", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("deadline")) {
                            Task task = new Deadline(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else if (command.equals("event")) {
                            Task task = new Event(fullCommand[1]);
                            tasks.addTask(task);
                            ui.showTaskWithNum("Got it. I've added this task:\n", task, tasks);
                            storage.saveToFile(tasks.getTasks()); // Save after adding new task
                        } else {
                            int deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
                            Task deletedTask = tasks.deleteTask(deleteIndex);
                            ui.showTaskWithNum(" Noted. I've removed this task:\n", deletedTask, tasks);

                            storage.saveToFile(tasks.getTasks());
                        }
                    } else {
                        throw new DukeException("____________________________________________________________\n"
                                + " OOPS! Your Only Friend cannot take in an empty "
                                + command + "\n Make sure " + command + "has a description!\n"
                                + "____________________________________________________________\n");
                    }

                } else if (command.equals("find")) {
                    if (fullCommand.length < 2) {
                        throw new DukeException("OOPS! Please make sure you enter a keyword");
                    }

                    String keyword = fullCommand[1];
                    TaskList foundTasks = tasks.findTasks(keyword);
                    ui.showMatchingList(foundTasks);
                } else {
                    throw new DukeException(" OOPS! Turns out Your Only Friend does not know what that is :(\n");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
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
        stage.setTitle("Your Only Friend");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //More code to be added here later
    }

    /**
     * Serves as the entry point of the application.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
