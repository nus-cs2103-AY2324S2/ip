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

    /**
     * Handles the mark command to mark a task as done.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the result of this command in the form of a String.
     * @throws FloofyException If the input index does not exist.
     */
    public String handleMarkCommand(String[] input) throws FloofyException {
        try {
            int idx = parseIdx(input[1]);
            if (!isValidIdx(idx)) {
                throw new FloofyException("Input index does not exist! " +
                        "You can only mark an existing task. Try again :)");
            }
            assert idx > -1 : "Task number should be greater than 0!";
            tasks.markTaskAsDone(idx);
            return ui.showMarkedTask(this.tasks.getTask(idx));
        } catch (FloofyException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the unmark command to mark a task as undone.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the result of this command in the form of a String.
     * @throws FloofyException If the input index does not exist.
     */
    public String handleUnmarkCommand(String[] input) throws FloofyException {
        try {
            int unmarkIdx = parseIdx(input[1]);
            if (!isValidIdx(unmarkIdx)) {
                throw new FloofyException("Input index does not exist! " +
                        "You can only unmark an existing task. Try again :)");
            }
            assert unmarkIdx > -1 : "Task number should be greater than 0!";
            tasks.markTaskAsUndone(unmarkIdx);
            return ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
        } catch (FloofyException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the find command to find tasks that match the keyword.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the matching tasks found, in the form of a String.
     * @throws FloofyException If no matching tasks are found.
     */
    public String handleFindCommand(String[] input) throws FloofyException {
        try {
            TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
            if (isEmptyList(matchingTasks)) {
                throw new FloofyException("No matching tasks found! Try again :)");
            }
            return ui.showMatchingTasks(matchingTasks);
        } catch (FloofyException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the todo command to add a todo task.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the added ToDo task, in the form of a String.
     */
    public String handleTodoCommand(String[] input) {
        ToDo newTodo = new ToDo(input[1]);
        tasks.addTask(newTodo);
        return ui.showAddedTask(newTodo, tasks.getSize());
    }

    /**
     * Handles the deadline command to add a deadline task.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the added Deadline task, in the form of a String.
     */
    public String handleDeadlineCommand(String[] input) {
        Deadline newDeadline = new Deadline(input[1], parseDate(input[2]));
        tasks.addTask(newDeadline);
        return ui.showAddedTask(newDeadline, tasks.getSize());
    }

    /**
     * Handles the event command to add an event task.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the added Event task, in the form of a String.
     */
    public String handleEventCommand(String[] input) {
        Event newEvent = new Event(input[1], parseDate(input[2]), parseDate(input[3]));
        tasks.addTask(newEvent);
        return ui.showAddedTask(newEvent, tasks.getSize());
    }

    /**
     * Handles the delete command to delete a task.
     *
     * @param input The user input command pre-processed by the parser.
     * @return The ui to display the deleted task, in the form of a String.
     */
    public String handleDeleteCommand(String[] input) {
        int deleteIdx = parseIdx(input[1]);
        Task deletedTask = tasks.getTask(deleteIdx);
        tasks.deleteTask(deleteIdx);
        return ui.showDeletedTask(deletedTask, tasks.getSize());
    }

    /**
     * Handles the list command to list all tasks.
     *
     * @return The ui to display the list of tasks, in the form of a String.
     */
    public String handleListCommand() {
        return ui.showTaskList(tasks);
    }

    /**
     * Handles the bye command to exit the application.
     *
     * @return The ui to display the goodbye message, in the form of a String.
     */
    public String handleByeCommand() {
        return ui.showGoodbyeMsg();
    }

    /**
     * Checks if the index is valid.
     *
     * @param idx The index of the task in the list.
     * @return True if the index is valid, false otherwise.
     * @throws FloofyException If the index is invalid.
     */
    public boolean isValidIdx(int idx) throws FloofyException {
        return idx > -1 && idx <= tasks.getSize();
    }

    /**
     * Checks if the list of tasks is empty.
     *
     * @param tasks The list of tasks.
     * @return True if the list of tasks is empty, false otherwise.
     */
    public boolean isEmptyList(TaskList tasks) {
        return tasks.getSize() == 0;
    }

    /**
     * Parses the index of the task from the user input.
     *
     * @param idx The index of the task in the list, in the form of a String.
     * @return The index of the task in the list, in the form of an int.
     */
    public int parseIdx(String idx) {
        return Integer.parseInt(idx) - 1;
    }

    /**
     * Parses the date from the user input.
     *
     * @param date The date in the form of a String.
     * @return The date in the form of a LocalDate.
     */
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
