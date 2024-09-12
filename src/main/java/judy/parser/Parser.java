package judy.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import judy.commands.AddTaskCommand;
import judy.commands.Command;
import judy.commands.DeleteTaskCommand;
import judy.commands.ExitCommand;
import judy.commands.FindTaskCommand;
import judy.commands.HelpCommand;
import judy.commands.InvalidCommand;
import judy.commands.ListTaskCommand;
import judy.commands.MarkTaskCommand;
import judy.commands.UnmarkTaskCommand;
import judy.exceptions.InvalidDateTimeException;
import judy.exceptions.InvalidDeadlineException;
import judy.exceptions.InvalidEventException;
import judy.exceptions.InvalidTaskIndexException;
import judy.exceptions.InvalidTodoException;
import judy.exceptions.JudyException;
import judy.task.Deadline;
import judy.task.Event;
import judy.task.Task;
import judy.task.TaskList;
import judy.task.Todo;

/**
 * The Parser class is responsible for parsing user input and converting it into corresponding Command objects.
 */
public class Parser {
    private final String userInput;
    private final TaskList taskList;

    /**
     * Constructs a Parser with the specified user input and task list.
     *
     * @param userInput The user input to be parsed.
     * @param taskList  The task list on which the commands operate.
     */
    public Parser(String userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @return The Command object representing the parsed user input.
     * @throws JudyException If an error occurs during the parsing process.
     */
    public Command parse() throws JudyException {
        String[] commandParts = userInput.trim().split(" ", 2);
        Command command;
        switch (commandParts[0]) {
        case ListTaskCommand.COMMAND_WORD:
            command = new ListTaskCommand(taskList);
            break;
        case MarkTaskCommand.COMMAND_WORD:
            command = handleMark(commandParts);
            break;
        case UnmarkTaskCommand.COMMAND_WORD:
            command = handleUnmark(commandParts);
            break;
        case DeleteTaskCommand.COMMAND_WORD:
            return handleDelete(commandParts);
        case AddTaskCommand.TODO:
            Task todo = handleTodo(commandParts);
            command = new AddTaskCommand(todo, this.taskList);
            break;
        case AddTaskCommand.DEADLINE:
            Task deadline = handleDeadline(commandParts);
            command = new AddTaskCommand(deadline, this.taskList);
            break;
        case AddTaskCommand.EVENT:
            Task event = handleEvent(commandParts);
            command = new AddTaskCommand(event, this.taskList);
            break;
        case FindTaskCommand.COMMAND_WORD:
            command = handleFind(commandParts);
            break;
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
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
     * Parses a mark task input.
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a MarkTaskCommand based on user's input.
     * @throws JudyException if user entered empty or invalid index.
     */

    private Command handleMark(String[] commandParts) throws JudyException {
        if (isEmptyDescription(commandParts)) {
            throw new JudyException("The index of task cannot be empty. ");
        }
        try {
            int taskId = Integer.parseInt(commandParts[1]) - 1;
            return new MarkTaskCommand(taskId, this.taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        } catch (NumberFormatException e) {
            throw new JudyException("The index you've input is not an integer. ");
        }
    }

    /**
     * Parses a unmark task input
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a UnmarkTaskCommand based on user's input.
     * @throws JudyException if user entered empty or invalid index.
     */
    private Command handleUnmark(String[] commandParts) throws JudyException {
        if (isEmptyDescription(commandParts)) {
            throw new JudyException("The index of task cannot be empty. ");
        }
        try {
            int taskId = Integer.parseInt(commandParts[1]) - 1;
            return new UnmarkTaskCommand(taskId, this.taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        } catch (NumberFormatException e) {
            throw new JudyException("The task index you've input is not an integer. ");
        }
    }

    /**
     * Parses a delete task input
     *
     * @param commandParts The parts of the user input after splitting by space.
     * @return a DeleteCommand based on user's input.
     * @throws JudyException if user entered empty or invalid index.
     */
    private Command handleDelete(String[] commandParts) throws JudyException {
        if (isEmptyDescription(commandParts)) {
            throw new JudyException("The index of task cannot be empty. ");
        }
        try {
            int taskIndex = Integer.parseInt(commandParts[1].trim()) - 1;
            return new DeleteTaskCommand(taskIndex, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        } catch (NumberFormatException e) {
            throw new JudyException("The task index you've input is not an integer. ");
        }
    }

    /**
     * Parses a todo input.
     *
     * @return a Todo based on user's input.
     * @throws JudyException if user left the description empty.
     */
    private static Todo handleTodo(String[] todo) throws JudyException {
        if (isEmptyDescription(todo)) {
            throw new InvalidTodoException();
        }
        return new Todo(todo[1]);
    }

    /**
     * Parses a deadline input.
     *
     * @return a Deadline based on user's input.
     * @throws JudyException if user left the description empty or entered invalid format.
     */
    private static Deadline handleDeadline(String[] commandParts) throws JudyException {
        if (isEmptyDescription(commandParts)) {
            throw new JudyException("The description of a deadline cannot be empty.");
        }
        String deadlineDetails = commandParts[1];
        if (deadlineDetails.contains("/by")) {
            String[] deadlineParts = deadlineDetails.split("/by ", 2);
            String taskDescription = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();

            LocalDateTime byDateTime = parseDateTime(by);
            return new Deadline(taskDescription, byDateTime);
        } else {
            throw new InvalidDeadlineException();
        }
    }

    /**
     * Parses an event input.
     *
     * @return an Event based on user's input.
     * @throws JudyException if user left the description empty or entered invalid format.
     */
    private static Event handleEvent(String[] commandParts) throws JudyException {
        if (isEmptyDescription(commandParts)) {
            throw new JudyException("The description of an event cannot be empty. ");
        }
        String eventDetails = commandParts[1];
        if (eventDetails.contains("/from") && eventDetails.contains("/to")) {
            String[] eventParts = eventDetails.split("/from ", 2);
            String taskDescription = eventParts[0].trim();

            String[] eventDates = eventParts[1].split("/to ", 2);
            String from = eventDates[0].trim();
            String to = eventDates[1].trim();

            LocalDateTime fromDateTime = parseDateTime(from);
            LocalDateTime toDateTime = parseDateTime(to);
            return new Event(taskDescription, fromDateTime, toDateTime);
        } else {
            throw new InvalidEventException();
        }
    }

    /**
     * Parses a find task input.
     *
     * @return a FindTaskCommand based on user's input.
     * @throws JudyException if user left the description empty.
     */
    private FindTaskCommand handleFind(String[] input) throws JudyException {
        if (isEmptyDescription(input)) {
            throw new JudyException("The description of find cannot be empty. ");
        }
        return new FindTaskCommand(this.taskList, input[1]);
    }

    /**
     * Parses a date time from a string format to a LocalDateTime format.
     *
     * @param inputDateTIme a String format date time entered by users.
     * @return a LocalDateTime with respect to the input date time string entered.
     * @throws InvalidDateTimeException if encountered invalid date time format.
     */
    public static LocalDateTime parseDateTime(String inputDateTIme) throws InvalidDateTimeException {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(inputDateTIme, pattern);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Checks if the user input has empty description.
     *
     * @param commandParts user input split.
     * @return boolean indicating if there is an empty description.
     */
    private static boolean isEmptyDescription(String[] commandParts) {
        return (commandParts.length != 2) || (commandParts[1].isEmpty());
    }
}
