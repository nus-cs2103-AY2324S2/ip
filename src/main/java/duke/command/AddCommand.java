package duke.command;

import static duke.DukeException.DEADLINE_ERROR;
import static duke.DukeException.EVENT_ERROR;
import static duke.DukeException.TODO_ERROR;
import static duke.DukeException.UNKNOWN_ERROR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The `AddCommand` class represents a command that adds task to the task list.
 * It provides methods to adds task to the task list, and to decide whether the program should continue.
 * It extends the `Command` class.
 */
public class AddCommand extends Command {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private final TaskType type;
    private String task;
    private String[] splitDateTime;
    private String deadline;
    private String content;
    private String[] splitFromDateTime;
    private String[] splitToDateTime;
    private String from;
    private String to;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Creates a command that creates `Todo` task when executed.
     *
     * @param task The full user input.
     */
    public AddCommand(String task) {
        this.task = task;
        this.type = TaskType.TODO;
    }

    /**
     * Creates a command that creates `Deadline` task when executed.
     *
     * @param splitDateTime The splited date (and time) of the deadline.
     * @param deadline The full date (and time) of the deadline.
     * @param content The description of task.
     */
    public AddCommand(String[] splitDateTime, String deadline, String content) {
        this.type = TaskType.DEADLINE;
        this.splitDateTime = splitDateTime;
        this.deadline = deadline;
        this.content = content;
    }

    /**
     * Creates a command that creates `Event` task when executed.
     *
     * @param splitFromDateTime The splited date (and time) of the start of event.
     * @param splitToDateTime The splited date (and time) of the end of event.
     * @param from The full date (and time) of the start of event.
     * @param to The full date (and time) of the end of event.
     * @param content The description of task.
     */
    public AddCommand(String[] splitFromDateTime, String[] splitToDateTime, String from, String to, String content) {
        this.type = TaskType.EVENT;
        this.splitFromDateTime = splitFromDateTime;
        this.splitToDateTime = splitToDateTime;
        this.from = from;
        this.to = to;
        this.content = content;
    }

    /**
     * Executes the command.
     *
     * @param tasks Existing tasks.
     * @param ui The Ui of the program.
     * @param storage The storage of the program.
     * @throws DukeException For any error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response;

        switch (type) {
        case TODO: {
            try {
                Task newTask = new Todo(task.substring(5));
                tasks.add(newTask);
                response = ui.add(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException(TODO_ERROR);
            }
            break;

        }

        case DEADLINE: {
            // Create deadline task
            try {
                Task newTask;
                if (splitDateTime.length == 2) {
                    LocalDateTime parsedDateTime = LocalDateTime.parse(deadline, dateTimeFormatter);
                    newTask = new Deadline(content, parsedDateTime);
                } else {
                    LocalDate parsedDate = LocalDate.parse(deadline, dateFormatter);
                    newTask = new Deadline(content, parsedDate);
                }
                tasks.add(newTask);
                response = ui.add(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException(DEADLINE_ERROR);
            }
            break;
        }

        case EVENT: {
            // Create event task
            try {
                Task newTask;
                if (splitFromDateTime.length == 2 && splitToDateTime.length == 2) {
                    LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
                    LocalDateTime parsedToDateTime = LocalDateTime.parse(to, dateTimeFormatter);
                    newTask = new Event(content, parsedFromDateTime, parsedToDateTime);
                } else if (splitFromDateTime.length == 2 && splitToDateTime.length == 1) {
                    LocalDateTime parsedFromDateTime = LocalDateTime.parse(from, dateTimeFormatter);
                    LocalDate parsedToDate = LocalDate.parse(to, dateFormatter);
                    newTask = new Event(content, parsedFromDateTime, parsedToDate);
                } else if (splitFromDateTime.length == 1 && splitToDateTime.length == 2) {
                    LocalDate parsedFromDate = LocalDate.parse(from, dateFormatter);
                    LocalDateTime parsedToDateTime = LocalDateTime.parse(to, dateTimeFormatter);
                    newTask = new Event(content, parsedFromDate, parsedToDateTime);
                } else {
                    LocalDate parsedFromDate = LocalDate.parse(from, dateFormatter);
                    LocalDate parsedToDate = LocalDate.parse(to, dateFormatter);
                    newTask = new Event(content, parsedFromDate, parsedToDate);
                }
                tasks.add(newTask);
                response = ui.add(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException(EVENT_ERROR);
            }
            break;
        }

        default: {
            throw new DukeException(UNKNOWN_ERROR);
        }
        }

        storage.saveChanges(tasks);
        return response;
    }

    /**
     * Returns False.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
