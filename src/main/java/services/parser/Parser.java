package services.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import commands.AbstractCommand;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.DeleteTaskCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkTaskCommand;
import commands.UnmarkTaskCommand;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The Parser class is responsible for interpreting user inputs and converting them into commands.
 * It handles various types of commands including adding tasks, marking tasks as done or undone,
 * deleting tasks, and exiting the program.
 */
public class Parser {
    /**
     * Parses the user input to determine the type of command to be executed.
     *
     * @param userInput The user's input as a string.
     * @return Command The command corresponding to the user's input.
     */
    public AbstractCommand parseCommand(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        String initialCommand = parsed[0].toUpperCase();
        switch (initialCommand) {
        case "EVENT":
            return parseAddEvent(userInput);
        case "DEADLINE":
            return parseAddDeadline(userInput);
        case "TODO":
            return parseAddTodo(userInput);
        case "MARK":
            return parseMark(userInput);
        case "UNMARK":
            return parseUnmark(userInput);
        case "DELETE":
            return parseDelete(userInput);
        case "FIND":
            return parseFind(userInput);
        case "LIST":
            return new ListCommand();
        case "BYE":
            return new ExitCommand();
        default:
            return new InvalidCommand("Invalid command added");
        }
    }
    /**
     * Parses the user input to create an AddEventCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command An AddEventCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseAddEvent(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter a task name");
        }
        String taskDesc = parsed[1];
        String[] parsedEvent = taskDesc.split(" /from | /to ");
        if (parsedEvent.length <= 2) {
            return new InvalidCommand("Please enter valid event format");
        }
        String eventName = parsedEvent[0];
        LocalDateTime start = null;
        LocalDateTime end = null;
        try {
            start = parseDate(parsedEvent[1]);
            end = parseDate(parsedEvent[2]);
        } catch (DukeException e) {
            return new InvalidCommand("Invalid date format");
        }
        return new AddEventCommand(eventName, start, end);
    }
    /**
     * Parses the user input to create an AddDeadlineCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command An AddDeadlineCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseAddDeadline(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter a task name");
        }
        String taskDesc = parsed[1];
        String[] parsedDeadline = taskDesc.split(" /by ");
        if (parsedDeadline.length <= 1) {
            return new InvalidCommand("Please enter a valid deadline format");
        }
        String deadlineName = parsedDeadline[0];
        LocalDateTime by;
        try {
            by = parseDate(parsedDeadline[1]);
        } catch (DukeException e) {
            return new InvalidCommand("Please enter a valid deadline format!!");
        }
        return new AddDeadlineCommand(deadlineName, by);
    }

    /**
     * Parses the user input to create an AddTodoCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command An AddTodoCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseAddTodo(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter a task name");
        }
        String taskDesc = parsed[1];
        return new AddTodoCommand(taskDesc);
    }
    /**
     * Parses the user input to create a DeleteTaskCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command A DeleteTaskCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseDelete(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an index");
        }
        int index = Integer.parseInt(parsed[1]);
        return new DeleteTaskCommand(index);
    }
    /**
     * Parses the user input to create a UnmarkTaskCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command A UnmarkTaskCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseUnmark(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an index");
        }
        int index = Integer.parseInt(parsed[1]);
        return new UnmarkTaskCommand(index);
    }
    /**
     * Parses the user input to create a MarkTaskCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command A MarkTaskCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseMark(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an index");
        }
        int index = Integer.parseInt(parsed[1]);
        return new MarkTaskCommand(index);
    }
    /**
     * Parses the user input to create an FindCommand.
     *
     * @param userInput The user's input as a string.
     * @return Command A FindCommand if input is valid, otherwise an InvalidCommand.
     */
    public AbstractCommand parseFind(String userInput) {
        assert userInput != null && !userInput.isEmpty() : "userInput cannot be null or empty";
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an task to find");
        }
        return new FindCommand(parsed[1]);

    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param dateString The date string to parse.
     * @return LocalDateTime The parsed LocalDateTime object.
     * @throws DukeException If the date string cannot be parsed into a valid date and time.
     */
    public LocalDateTime parseDate(String dateString) throws DukeException {
        assert dateString != null && !dateString.isEmpty() : "dateString cannot be null or empty";
        List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("M/d/yyyy h:mm a"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("d MMM yyyy h:mma")
        );

        List<DateTimeFormatter> dateFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/M/d"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDate.parse(dateString, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to try the next format
            }
        }
        throw new DukeException("Invalid Date and time format");
    }

    /**
     * Parses a task string representation from a file into a Task object.
     *
     * @param taskString The string representation of the task.
     * @return Task The Task object parsed from the string.
     * @throws DukeException If the task string cannot be parsed into a valid task.
     */
    public static Task parseTaskFromString(String taskString) throws DukeException {
        assert taskString != null && !taskString.isEmpty() : "taskString cannot be null or empty";
        String[] parts = taskString.split(" \\| ");
        assert parts.length >= 3 : "Task string format is invalid";
        String taskType = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;
        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (additionalInfo == null) {
                throw new DukeException("Invalid tasks.Deadline format in file");
            }
            LocalDateTime by = LocalDateTime.parse(additionalInfo);
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            String[] times = additionalInfo.split(" to ");
            if (times.length < 2) {
                throw new DukeException("Invalid tasks.Event time format in file.");
            }
            LocalDateTime start = LocalDateTime.parse(times[0].trim());
            LocalDateTime end = LocalDateTime.parse(times[1].trim());

            Event event = new Event(description, start, end);
            if (isDone) {
                event.markAsDone();
            }

            return event;

        default:
            return null;
        }
    }
}
