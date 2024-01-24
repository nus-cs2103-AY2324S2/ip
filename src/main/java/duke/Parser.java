package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import controller.AddTaskCommand;
import controller.Command;
import controller.DeleteTaskCommand;
import controller.InvalidCommand;
import controller.ListTaskCommand;
import controller.MarkTaskCommand;
import controller.UnmarkTaskCommand;
import model.Deadline;
import model.Event;
import model.Task;
import model.TaskList;
import model.ToDo;

/**
 * The {@code Parser} class is responsible for parsing user input and extracting relevant information
 * to perform actions within the program.
 */
public class Parser {
    private final String command;
    private final TaskList taskList;

    /**
     * Constructs a {@code Parser} with the specified command string and {@code TaskList}.
     *
     * @param command The raw command string provided by the user for parsing.
     * @param taskList The {@code TaskList} associated with the parser, providing context for command execution.
     */
    public Parser(String command, TaskList taskList) {
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Parse user input.
     * @return a {@code Command} based on user's input.
     * @throws DukeException if user enters an invalid input.
     */
    public Command parse() throws DukeException{
        String[] splitTask = command.split(" ", 2);
        Task task;
        Command command = null;
        switch(splitTask[0]) {
        case ListTaskCommand.COMMAND_WORD:
            command = new ListTaskCommand(taskList);
            break;
        case MarkTaskCommand.COMMAND_WORD:
            command = parseMark(splitTask);
            break;
        case UnmarkTaskCommand.COMMAND_WORD:
            command = parseUnmark(splitTask);
            break;
        case AddTaskCommand.TODO:
            task = parseToDo(splitTask);
            command = new AddTaskCommand(task, taskList);
            break;
        case AddTaskCommand.EVENT:
            task = parseEvent(splitTask);
            command = new AddTaskCommand(task, taskList);
            break;
        case AddTaskCommand.DEADLINE:
            task = parseDeadline(splitTask);
            command = new AddTaskCommand(task, taskList);
            break;
        case DeleteTaskCommand.COMMAND_WORD:
            command = parseDelete(splitTask);
            break;
        default:
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Parse a todo input.
     * @return a {@code ToDo} based on user's input.
     * @throws DukeException if user left the description empty.
     */
    private static ToDo parseToDo(String[] todo) throws DukeException {
        if (todo.length != 2 || todo[1].isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDo(todo[1]);
    }

    /**
     * Parse an event input.
     * @return a {@code Event} based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Event parseEvent(String[] event) throws DukeException {
        if (event.length != 2 || event[1].isEmpty()) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] splitEvent = event[1].split(" /from ", 2);
        if (splitEvent.length != 2) {
            throw new DukeException("Invalid input. Use: event event_title /from dd-mm-yyyy HHmm /to HHmm");
        }
        String[] splitDuration = splitEvent[1].split(" /to ", 2);
        if (splitDuration.length != 2) {
            throw new DukeException("Invalid input. Use: event event_title /from dd-mm-yyyy HHmm /to HHmm");
        }

        String title = splitEvent[0];
        String from = splitDuration[0];
        String to = splitDuration[1];
        String temp = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
            LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
            temp = fromDateTime.format(DateTimeFormatter.ofPattern("dd-M-yyyy")) + " " + to;
            LocalDateTime toTime = LocalDateTime.parse(temp, formatter);
            return new Event(title, fromDateTime, toTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date & time input. Use /from dd-mm-yyyy HHmm /to HHmm");
        }
    }

    /**
     * Parse a deadline input.
     * @return a {@code Deadline} based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Deadline parseDeadline(String[] deadline) throws DukeException {
        if (deadline.length != 2 || deadline[1].isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] splitDeadline = deadline[1].split(" /by ", 2);
        if (splitDeadline.length != 2) {
            throw new DukeException("Invalid input. Use: deadline deadline_title /by dd-mm-yyyy HHmm");
        }
        String title = splitDeadline[0];
        String time = splitDeadline[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            return new Deadline(title, dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Use dd-mm-yyyy HHmm as the date format.");
        }
    }

    /**
     * Parse a mark task input.
     * @return a {@code MarkTaskCommand} based on user's input.
     * @throws DukeException if user entered invalid index.
     */
    private MarkTaskCommand parseMark(String[] command) throws DukeException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new MarkTaskCommand(markIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }

    /**
     * Parse an unmark task input.
     * @return a {@code UnmarkTaskCommand} based on user's input.
     * @throws DukeException if user entered invalid index.
     */
    private UnmarkTaskCommand parseUnmark(String[] command) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new UnmarkTaskCommand(unmarkIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }

    /**
     * Parse a delete task input.
     * @return a {@code DeleteTaskCommand} based on user's input.
     * @throws DukeException if user entered invalid index.
     */
    private DeleteTaskCommand parseDelete(String[] command) throws DukeException {
        try {
            int deleteIndex = Integer.parseInt(command[1]) - 1;
            try {
                return new DeleteTaskCommand(deleteIndex, this.taskList);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index of task cannot be empty.");
        } catch (NumberFormatException e) {
            throw new DukeException("The index you've input is not an integer.");
        }
    }
}
