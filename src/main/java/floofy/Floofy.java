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
     * Runs Floofy for each command input.
     *
     * @param userInput The user input command.
     * @return The result of the command.
     */
    public String runCommand(String userInput) {
        String result = "";
        try {
            String[] input = parser.parse(userInput);
            switch (input[0]) {
            case "mark":
                int idx = Integer.parseInt(input[1]) - 1;
                tasks.markTaskAsDone(idx);
                result = ui.showMarkedTask(this.tasks.getTask(idx));
                break;
            case "unmark":
                int unmarkIdx = Integer.parseInt(input[1]) - 1;
                tasks.markTaskAsUndone(unmarkIdx);
                result = ui.showUnmarkedTask(this.tasks.getTask(unmarkIdx));
                break;
            case "find":
                TaskList matchingTasks = tasks.findMatchingTasks(input[1]);
                result = ui.showMatchingTasks(matchingTasks);
                break;
            case "todo":
                String todoTask = input[1];
                ToDo newTodo = new ToDo(todoTask);
                tasks.addTask(newTodo);
                result = ui.showAddedTask(newTodo, tasks.getSize());
                break;
            case "deadline":
                String deadlineTask = input[1];
                LocalDate deadlineBy = LocalDate.parse(input[2]);
                Deadline newDeadline = new Deadline(deadlineTask, deadlineBy);
                tasks.addTask(newDeadline);
                result = ui.showAddedTask(newDeadline, tasks.getSize());
                break;
            case "event":
                String eventTask = input[1];
                LocalDate eventDateFrom = LocalDate.parse(input[2]);
                LocalDate eventDateTo = LocalDate.parse(input[3]);
                Event newEvent = new Event(eventTask, eventDateFrom, eventDateTo);
                tasks.addTask(newEvent);
                result = ui.showAddedTask(newEvent, tasks.getSize());
                break;
            case "delete":
                int deleteIdx = Integer.parseInt(input[1]) - 1;
                Task deletedTask = tasks.getTask(deleteIdx);
                tasks.deleteTask(deleteIdx);
                result = ui.showDeletedTask(deletedTask, tasks.getSize());
                break;
            case "list":
                result = ui.showTaskList(tasks);
                break;
            case "bye":
                result = ui.showGoodbyeMsg();
                break;
            case "invalid":
//                result = ui.showInvalidInput();
                throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
            }
            return result;
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
