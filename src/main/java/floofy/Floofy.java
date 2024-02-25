package floofy;

import floofy.task.Deadline;
import floofy.task.Event;
import floofy.task.Task;
import floofy.task.ToDo;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents the main class of the Floofy chat-bot application.
 */
public class Floofy extends Application {

    /** The storage object to handle the loading and saving of tasks. */
    private Storage storage;

    /** The parser object to handle the parsing of user input. */
    private Parser parser;

    /** The task list object to handle the list of tasks. */
    private TaskList tasks;

    /** The user interface object to handle the interaction with the user. */
    private Ui ui;

    /** The file path of the file to store the tasks. */
    private static final String FILE_PATH = "./data/floofy.txt";

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
     * Runs the command based on the user input.
     *
     * @param userInput The user input command.
     * @return The result of the command.
     */
    public String runCommand(String userInput) {
        try {
            String[] input = parser.parse(userInput);
            switch (input[0]) {
            case "mark":
                return handleMarkCommand(input);
            case "unmark":
                return handleUnmarkCommand(input);
            case "find":
                return handleFindCommand(input);
            case "todo":
                return handleTodoCommand(input);
            case "deadline":
                return handleDeadlineCommand(input);
            case "event":
                return handleEventCommand(input);
            case "delete":
                return handleDeleteCommand(input);
            case "list":
                return handleListCommand();
            case "bye":
                return handleByeCommand();
            default:
                throw new FloofyException("To add a task, " +
                        "please start with any of these commands: 'todo', 'deadline' or 'event'!");
            }
        } catch (FloofyException e) {
            return e.getMessage();
        } finally {
            storage.saveTasks(tasks);
        }
    }

    public String handleMarkCommand(String[] input) {
        int idx = parseIdx(input[1]);
        // To verify assumption that task number is greater than 0
        assert idx > -1 : "Task number should be greater than 0!";
        tasks.markTaskAsDone(idx);
        return ui.showMarkedTask(this.tasks.getTask(idx));
    }

    public String handleUnmarkCommand(String[] input) {
        int unmarkIdx = parseIdx(input[1]);
        // To verify assumption that task number is greater than 0
        assert unmarkIdx > -1 : "Task number should be greater than 0!";
        tasks.markTaskAsUndone(unmarkIdx);
        return ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
    }

    public String handleFindCommand(String[] input) {
        TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
        return ui.showMatchingTasks(matchingTasks);
    }

    public String handleTodoCommand(String[] input) {
        ToDo newTodo = new ToDo(input[1]);
        tasks.addTask(newTodo);
        return ui.showAddedTask(newTodo, tasks.getSize());
    }

    public String handleDeadlineCommand(String[] input) {
        Deadline newDeadline = new Deadline(input[1], parseDate(input[2]));
        tasks.addTask(newDeadline);
        return ui.showAddedTask(newDeadline, tasks.getSize());
    }

    public String handleEventCommand(String[] input) {
        Event newEvent = new Event(input[1], parseDate(input[2]), parseDate(input[3]));
        tasks.addTask(newEvent);
        return ui.showAddedTask(newEvent, tasks.getSize());
    }

    public String handleDeleteCommand(String[] input) {
        int deleteIdx = parseIdx(input[1]);
        Task deletedTask = tasks.getTask(deleteIdx);
        tasks.deleteTask(deleteIdx);
        return ui.showDeletedTask(deletedTask, tasks.getSize());
    }

    public String handleListCommand() {
        return ui.showTaskList(tasks);
    }

    public String handleByeCommand() {
        return ui.showGoodbyeMsg();
    }


    public int parseIdx(String idx) {
        return Integer.parseInt(idx) - 1;
    }

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }


    /**
     * Gets the welcome message from Ui for special case of starting the application.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcomeMsg();
    }

    /**
     * Starts the Floofy chat-bot application.
     *
     * @param stage The stage to display the application.
     */
    @Override
    public void start(Stage stage) {

    }

    /**
     * Returns the response of Floofy to the user input.
     *
     * @param inputCommand The user input command.
     * @return The response of Floofy to the user input.
     */
    public String getResponse(String inputCommand) {
        return "FLOOFED: " + "\n" + runCommand(inputCommand);
    }
}
