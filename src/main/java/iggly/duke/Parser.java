package iggly.duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iggly.command.AddTaskCommand;
import iggly.command.Command;
import iggly.command.DeleteTaskCommand;
import iggly.command.FindTaskCommand;
import iggly.command.HelpCommand;
import iggly.command.InvalidCommand;
import iggly.command.ListTaskCommand;
import iggly.command.MarkTaskCommand;
import iggly.command.UnmarkTaskCommand;
import iggly.model.Deadline;
import iggly.model.Event;
import iggly.model.Schedule;
import iggly.model.Task;
import iggly.model.TaskList;
import iggly.model.ToDo;

/**
 * The {@link Parser} class is responsible for parsing user input and extracting relevant information
 * to perform actions within the program.
 */
public class Parser {
    private final String command;
    private final TaskList taskList;

    /**
     * Constructs a {@link Parser} with the specified command string and {@link TaskList}.
     *
     * @param command The raw command string provided by the user for parsing.
     * @param taskList The {@code TaskList} associated with the parser, providing context for command execution.
     */
    public Parser(String command, TaskList taskList) {
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Parses user input and return the corresponding command based on user's input.
     *
     * @return a {@code Command} based on user's input.
     * @throws DukeException if user enters an invalid input.
     */
    public Command parse() throws DukeException {
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
        case AddTaskCommand.SCHEDULE:
            task = parseSchedule(splitTask);
            command = new AddTaskCommand(task, taskList);
            break;
        case DeleteTaskCommand.COMMAND_WORD:
            command = parseDelete(splitTask);
            break;
        case FindTaskCommand.COMMAND_WORD:
            command = parseFind(splitTask);
            break;
        case HelpCommand.COMMAND_WORD:
            command = new HelpCommand();
            break;
        default:
            command = new InvalidCommand();
        }
        return command;
    }

    /**
     * Parses a todo input.
     *
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
     * Parses an event input.
     *
     * @return a {@code Event} based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Event parseEvent(String[] event) throws DukeException {
        if (event.length != 2 || event[1].isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
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
     * Parses a deadline input.
     *
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
     * Parses a schedule input.
     *
     * @return a {@code Schedule} based on user's input.
     * @throws DukeException if user left the description empty or entered invalid format.
     */
    private static Schedule parseSchedule(String[] schedule) throws DukeException {
        if (schedule.length != 2 || schedule[1].isEmpty()) {
            throw new DukeException("The description of a schedule cannot be empty.");
        }

        String[] splitSchedule = schedule[1].split(" /from ", 2);
        if (splitSchedule.length != 2) {
            throw new DukeException("Invalid input. Use: schedule schedule_title /from dd-mm-yyyy /to dd-mm-yyyy");
        }

        String[] splitDuration = splitSchedule[1].split(" /to ", 2);
        if (splitDuration.length != 2) {
            throw new DukeException("Invalid input. Use: schedule schedule_title /from dd-mm-yyyy /to dd-mm-yyyy");
        }

        String title = splitSchedule[0];
        String from = splitDuration[0];
        String to = splitDuration[1];

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
            LocalDate fromDate = LocalDate.parse(from, formatter);
            LocalDate toDate = LocalDate.parse(to, formatter);
            return new Schedule(title, fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date input. Use /from dd-mm-yyyy /to dd-mm-yyyy");
        }
    }

    /**
     * Parses a mark task input.
     *
     * @return a {@code MarkTaskCommand} based on user's input.
     * @throws DukeException if user entered invalid index.
     */
    private MarkTaskCommand parseMark(String[] command) throws DukeException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            try {
                Task task = this.taskList.get(markIndex);
                if (task.isDone()) {
                    throw new DukeException("This task is already marked as done.");
                } else {
                    return new MarkTaskCommand(task, taskList);
                }
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
     * Parses an unmark task input.
     *
     * @return a {@code UnmarkTaskCommand} based on user's input.
     * @throws DukeException if user entered invalid index.
     */
    private UnmarkTaskCommand parseUnmark(String[] command) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(command[1]) - 1;
            try {
                Task task = this.taskList.get(unmarkIndex);
                if (!task.isDone()) {
                    throw new DukeException("This task is already marked as not done.");
                } else {
                    return new UnmarkTaskCommand(task, taskList);
                }
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
     * Parses a delete task input.
     *
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

    /**
     * Parses a find task input.
     *
     * @return a {@link FindTaskCommand} based on user's input.
     * @throws DukeException if user left the description empty.
     */
    private FindTaskCommand parseFind(String[] input) throws DukeException {
        if (input.length != 2 || input[1].isEmpty()) {
            throw new DukeException("The description of find cannot be empty.");
        }
        return new FindTaskCommand(input[1], this.taskList);
    }
}
