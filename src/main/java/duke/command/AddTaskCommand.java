package duke.command;

import java.sql.SQLException;
import java.time.LocalDate;

import database.TaskOrm;
import duke.Parser;
import exceptions.BadInputException;
import exceptions.BadTaskInputException;
import task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    public static final String ADD_TODO_COMMAND = "todo";
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    public static final String ADD_EVENT_COMMAND = "event";
    private final String command;
    private final String description;
    private final TaskOrm tm = new TaskOrm();

    /**
     * Constructs an AddTaskCommand.
     *
     * @param command The type of task to add.
     * @param description The details of the task to add.
     */
    public AddTaskCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Constructs an AddTaskCommand with no command and description.
     */
    public AddTaskCommand(String command) {
        this.command = command;
        this.description = null;
    }

    @Override
    public String execute() {
        Task task;
        try {
            switch (this.command) {
            case ADD_TODO_COMMAND:
                task = this.checkedCreateTodo();
                break;

            case ADD_DEADLINE_COMMAND:
                task = this.checkedCreateDeadline();
                break;

            case ADD_EVENT_COMMAND:
                task = this.checkedCreateEvent();
                break;

            default:
                throw new IllegalArgumentException("Invalid task type: " + this.command + "\n");
            }

            return "Got it. I've added this task:\n"
                    + "   " + task + "\n"
                    + String.format("Now you have %d tasks in the list.\n", tm.count());
        } catch (SQLException | BadInputException e) {
            return e.getMessage();
        }
    }

    private Task checkedCreateTodo() throws SQLException, BadTaskInputException {
        if (this.description == null || this.description.isEmpty()) {
            throw new BadTaskInputException(
                    "Details of a todo cannot be empty.",
                    "todo <description>",
                    "todo read book",
                    this.description);
        }

        return this.tm.createTodo(this.description);
    }

    private Task checkedCreateDeadline() throws SQLException, BadTaskInputException {
        String[] deadlineDetails = this.description.split(" /by ");

        if (deadlineDetails.length < 2) {
            throw new BadTaskInputException(
                    "Details of a deadline must include a deadline.",
                    "deadline <description> /by <deadline>",
                    "deadline return book /by 2019-12-15",
                    this.description);
        }

        LocalDate deadline = Parser.parseDate(deadlineDetails[1]);
        return this.tm.createDeadline(deadlineDetails[0], deadline);
    }

    private Task checkedCreateEvent() throws SQLException, BadTaskInputException {
        String[] eventDetails = this.description.split(" /from | /to ");

        if (eventDetails.length < 3) {
            throw new BadTaskInputException(
                    "Details of an event must include a start date and an end date.",
                    "event <description> /from <start date> /to <end date>",
                    "fun event /from 2019-12-1 /to 2019-12-15",
                    this.description);
        }

        LocalDate startDate = Parser.parseDate(eventDetails[1]);
        LocalDate endDate = Parser.parseDate(eventDetails[2]);

        if (startDate.isAfter(endDate)) {
            throw new BadTaskInputException(
                    "Start date cannot be after end date.",
                    "event <description> /from <start date> /to <end date>",
                    "fun event /from 2019-12-1 /to 2019-12-15",
                    this.description);
        }

        return this.tm.createEvent(eventDetails[0], startDate, endDate);
    }

    @Override
    public String explain() {
        switch (this.command) {
        case ADD_TODO_COMMAND:
            return "Adds a todo to the task list.\n"
                        + "Format: todo <description>\n"
                        + "Example: todo read book";
        case ADD_DEADLINE_COMMAND:
            return "Adds a deadline to the task list.\n"
                        + "Format: deadline <description> /by <deadline>\n"
                        + "Example: deadline return book /by 2019-12-15";
        case ADD_EVENT_COMMAND:
            return "Adds an event to the task list.\n"
                        + "Format: event <description> /from <start date> /to <end date>\n"
                        + "Example: fun event /from 2019-12-1 /to 2019-12-15";
        default:
            return "Adds a task to the task list.";
        }
    }
}
