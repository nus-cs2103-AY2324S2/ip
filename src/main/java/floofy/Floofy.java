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
                int idx = Integer.parseInt(input[1]) - 1;
                tasks.markTaskAsDone(idx);
                return ui.showMarkedTask(this.tasks.getTask(idx));
            case "unmark":
                int unmarkIdx = Integer.parseInt(input[1]) - 1;
                tasks.markTaskAsUndone(unmarkIdx);
                return ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
            case "find":
                TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
                return ui.showMatchingTasks(matchingTasks);
            case "todo":
                String todoTask = input[1];
                ToDo newTodo = new ToDo(todoTask);
                tasks.addTask(newTodo);
                return ui.showAddedTask(newTodo, tasks.getSize());
            case "deadline":
                String deadlineTask = input[1];
                LocalDate deadlineBy = LocalDate.parse(input[2]);
                Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                tasks.addTask(newDeadline);
                return ui.showAddedTask(newDeadline, tasks.getSize());
            case "event":
                String eventTask = input[1];
                LocalDate eventDateFrom = LocalDate.parse(input[2]);
                LocalDate eventDateTo = LocalDate.parse(input[3]);
                Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                tasks.addTask(newEvent);
                return ui.showAddedTask(newEvent, tasks.getSize());
            case "delete":
                int deleteIdx = Integer.parseInt(input[1]) - 1;
                Task deletedTask = tasks.getTask(deleteIdx);
                tasks.deleteTask(deleteIdx);
                return ui.showDeletedTask(deletedTask, tasks.getSize());
            case "list":
                return ui.showTaskList(tasks);
            case "bye":
                return ui.showGoodbyeMsg();
            default:
                throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
            }
        } catch (FloofyException e) {
            return e.getMessage();
        } finally {
            storage.saveTasks(tasks);
        }
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
