package Kokbot;

import Kokbot.task.Deadline;
import Kokbot.task.Event;
import Kokbot.task.Task;
import Kokbot.task.Todo;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;

/**
 * Represents the main Kokbot class
 */
public class Kokbot {

    /**
     * Represents the type of command
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     * Represents the storage of tasks
     */
    private Storage storage;

    /**
     * Represents the list of tasks
     */
    private TaskList tasks;

    /**
     * Represents the user interface
     */
    private Ui ui;

    /**
     * Represents the parser
     */
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Kokbot() {
        ui = new Ui("KokBot");
        storage = new Storage(Paths.get("data", "kokbot.txt"));
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke
     *
     * @param filePath Path of the file
     * @param botName  Name of the bot
     */
    public Kokbot(Path filePath, String botName) {
        ui = new Ui(botName);
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Carries out command and gets the response from the bot
     * @param input Input from the user
     * @return Response from the bot
     */
    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            assert cmd != null : "Command should not be null";
            switch (cmd.type) {
            case BYE:
                return ui.showGoodbye();
            case LIST:
                if (cmd.args.length > 0) {
                    return ui.showTaskList(tasks.getTaskStrings(cmd.args[0]));
                } else {
                    return ui.showTaskList(tasks.getTaskStrings(""));
                }
            case MARK:
                int toMark = Integer.parseInt(cmd.args[0]) - 1;
                tasks.markTaskAsDone(toMark);
                return ui.showTaskMarked(tasks.getTask(toMark));
            case UNMARK:
                int toUnmark = Integer.parseInt(cmd.args[0]) - 1;
                tasks.markTaskAsUndone(toUnmark);
                return ui.showTaskUnmarked(tasks.getTask(toUnmark));
            case TODO:
                Todo newTodo = createTodo(cmd.args[0]);
                tasks.addTask(newTodo);
                return ui.showTaskAdded(newTodo, tasks.getSize());
            case DEADLINE:
                Deadline newDeadline = createDeadline(cmd.args[0], cmd.args[1]);
                tasks.addTask(newDeadline);
                return ui.showTaskAdded(newDeadline, tasks.getSize());
            case EVENT:
                Event newEvent = createEvent(cmd.args[0], cmd.args[1], cmd.args[2]);
                tasks.addTask(newEvent);
                return ui.showTaskAdded(newEvent, tasks.getSize());
                //numList(duke.tasks.getSize());
            case DELETE:
                Task deletedTask = tasks.deleteTask(Integer.parseInt(cmd.args[0]) - 1);
                return ui.showTaskDeleted(deletedTask, tasks.getSize());
            case FIND:
                return ui.showMatchingTasks(tasks.getMatchingTasks(cmd.args[0]));
            default:
                throw new DukeException("Unknown command");
            }
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            save();
        }
    }

    /**
     * Saves the current tasks back to the file
     */
    public void save() {
        try {
            storage.updateFile(tasks.getFileStrings());
        } catch (DukeException e) {
            // do nothing
        }
    }

    /**
     * Creates a LocalDateTime object from a string
     *
     * @param input String to be parsed
     * @return LocalDateTime object
     * @throws DukeException If the string is not in a valid date-time format
     */
    public static LocalDateTime createDateTime(String input) throws DukeException {

        String[] possibleDates = {
                "d/M/yyyy",
                "d-M-yyyy",
                "d/M/yy",
                "d-M-yy",
                "dMMyyyy",
                "dMMyy",

                "dd/MM/yyyy",
                "dd-MM-yyyy",
                "yyyy-MM-dd",

                "dd/MM/yy",
                "dd-MM-yy",
                "ddMMyyyy",
                "ddMMyy",};

        String[] possibleTimes = {"HHmm", "HH:mm", "HH", "h:mma",};

        for (String datePattern : possibleDates) {
            for (String timePattern : possibleTimes) {
                //check that time pattern comes before date pattern
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timePattern + " " + datePattern);
                    return LocalDateTime.parse(input, formatter);
                } catch (Exception e) {
                    //do nothing
                }
                //check that time pattern comes after date pattern
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern + " " + timePattern);
                    return LocalDateTime.parse(input, formatter);
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        return null;
    }

    /**
     * Creates a To do task
     *
     * @param description Description of the To do
     * @return To do task
     * @throws DukeException If the description is empty
     */
    public static Todo createTodo(String description) throws DukeException {
        Todo newTodo = new Todo(description);
        return newTodo;
    }

    /**
     * Creates a Deadline task
     *
     * @param description Description of the Deadline
     * @param dueDate     Due date of the Deadline
     * @return Deadline task
     * @throws DukeException If the due date is not in a valid date-time format
     */
    public static Deadline createDeadline(String description, String dueDate) throws DukeException {

        LocalDateTime dueDateTime = createDateTime(dueDate);
        if (dueDateTime == null) {
            throw new DukeException("Unknown usage - due date of \"deadline\" is not in a valid date-time format.");
        }

        return new Deadline(description, dueDateTime);
    }

    /**
     * Creates an Event task
     *
     * @param description Description of the Event
     * @param startDate   Start date of the Event
     * @param endDate     End date of the Event
     * @return Event task
     * @throws DukeException If the start date is after the end date
     */
    public static Event createEvent(String description, String startDate, String endDate) throws DukeException {

        LocalDateTime startDateTime = createDateTime(startDate);
        if (startDateTime == null) {
            throw new DukeException("Unknown usage - start date of \"event\" is not in a valid date-time format.");
        }

        LocalDateTime endDateTime = createDateTime(endDate);
        if (endDateTime == null) {
            throw new DukeException("Unknown usage - end date of \"event\" is not in a valid date-time format.");
        }

        if (startDateTime.isAfter(endDateTime)) {
            throw new DukeException("Unknown usage - start date of \"event\" is after end date.");
        }

        return new Event(description, startDateTime, endDateTime);
    }
}
