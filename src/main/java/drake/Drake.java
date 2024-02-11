package drake;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import drake.task.Deadline;
import drake.task.Event;
import drake.task.Task;
import drake.task.TaskList;
import drake.task.Todo;
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

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND;
    public static Command fromString(String commandString) {
        switch (commandString.toLowerCase()) {
        case "bye":
            return BYE;
        case "list":
            return LIST;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        default:
            return INVALID;
        }
    }
}

/**
 * This is the main class for the Drake application, which is a task manager program.
 * It initializes the application, loads existing tasks, and enters a command loop that
 * allows users to interact with their task list through various commands.
 */
public class Drake {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isRunning;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static final String USER_IMAGE_PATH = "/images/user.jpeg";
    private static final String DRAKE_IMAGE_PATH = "/images/drake.jpeg";

    private Image user = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image duke = new Image(this.getClass().getResourceAsStream(DRAKE_IMAGE_PATH));

    /**
     * Constructs a new instance of the Drake application.
     *
     * @param filePath The path to the file where tasks are stored and retrieved from.
     */
    public Drake(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        isRunning = true;
    }

    /**
     * Constructs a new instance of the Drake application.
     */
    public Drake() {
        ui = new Ui();
        storage = new Storage("./list.dat");
        tasks = new TaskList(storage.loadTasks());
        isRunning = true;
    }

    /**
     * Gets the response from Drake.
     *
     * @param input the user's input.
     * @return the response from Drake.
     */
    protected String getResponse(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0];
        Command command = Command.fromString(commandWord);
        try {
            switch (command) {
            case BYE:
                return handleBye();
            case LIST:
                return handleList();
            case MARK:
                return handleMark(input);
            case UNMARK:
                return handleUnmark(input);
            case TODO:
                return handleTodo(input);
            case DEADLINE:
                return handleDeadline(input);
            case EVENT:
                return handleEvent(input);
            case DELETE:
                return handleDelete(input);
            case FIND:
                return handleFind(input);
            default:
                throw new NotValidCommand("That's not a valid command!");
            }
        } catch (NotValidCommand | TodoLeftBlank e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) { // General exception catch for unforeseen errors
            return ui.showError("An unexpected error occurred.");
        }
    }
    private String handleBye() throws IOException {
        isRunning = false;
        storage.saveTasks(tasks.getTasks());
        return ui.showGoodbye();
    }

    private String handleList() {
        return ui.showTaskList(tasks);
    }

    private String handleMark(String input) {
        int markIndex = Parser.parseTaskIndex(input);
        tasks.markTask(markIndex);
        return ui.showMarkTask(tasks.getTask(markIndex));
    }

    private String handleUnmark(String input) {
        int unmarkIndex = Parser.parseTaskIndex(input);
        tasks.unmarkTask(unmarkIndex);
        return ui.showUnmarkTask(tasks.getTask(unmarkIndex));
    }

    private String handleTodo(String input) {
        String todoDescription = Parser.parseDescription(input);
        Todo newTodo = new Todo(todoDescription);
        tasks.addTask(newTodo);
        return ui.showAddTask(newTodo, tasks.size());
    }

    private String handleDeadline(String input) {
        try {
            Object[] deadlineDetails = Parser.parseDeadline(input);
            Deadline newDeadline = new Deadline((String) deadlineDetails[0],
                    (LocalDateTime) deadlineDetails[1]);
            tasks.addTask(newDeadline);
            return ui.showAddTask(newDeadline, tasks.size());
        } catch (Exception e) {
            return ui.showError("Oops, format error! Type in a date in the form yy-mm-dd and try again!");
        }
    }

    private String handleEvent(String input) {
        String[] eventDetails = Parser.parseEvent(input);
        Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
        tasks.addTask(newEvent);
        return ui.showAddTask(newEvent, tasks.size());
    }

    private String handleDelete(String input) {
        int deleteIndex = Parser.parseTaskIndex(input);
        Task deletedTask = tasks.deleteTask(deleteIndex);
        return ui.showDeleteTask(deletedTask, tasks.size());
    }

    private String handleFind(String input) {
        String keyword = Parser.parseKeyword(input);
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }

    public static void main(String[] args) throws Exception {
        Application.launch(Main.class, args);
    }
}
