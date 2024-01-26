package services.parser;

import commands.*;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    // First parse
    public Command parseCommand(String userInput) {
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
            // return new HelpCommand();
            return new InvalidCommand("INVALID");

        }
    }

    public Command parseAddEvent(String userInput) {
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

    public Command parseAddDeadline(String userInput) {
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

    public Command parseAddTodo(String userInput) {
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter a task name");
        }
        String taskDesc = parsed[1];
        return new AddTodoCommand(taskDesc);
    }

    public Command parseDelete(String userInput) {
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an index");
        }
        int index = Integer.parseInt(parsed[1]);
        return new DeleteTaskCommand(index);
    }

    private Command parseUnmark(String userInput) {
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an index");
        }
        int index = Integer.parseInt(parsed[1]);
        return new UnmarkTaskCommand(index);
    }

    private Command parseMark(String userInput) {
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
    public Command parseFind(String userInput) {
        String[] parsed = userInput.split(" ", 2);
        if (parsed.length <= 1 || parsed[1].isEmpty()) {
            return new InvalidCommand("OOPS! Please enter an task to find");
        }
        return new FindCommand(parsed[1]);

    }

    public LocalDateTime parseDate(String dateString) throws DukeException {
        List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("M/d/yyyy h:mm a"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"),
                DateTimeFormatter.ofPattern("d MMM yyyy h:mma"),
                DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyyMMdd h:mm a"),
                DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/M/d h:mma"),
                DateTimeFormatter.ofPattern("d MMMM yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d h:mm a")
        );

        List<DateTimeFormatter> dateFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("yyyy/M/d"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-M-d"),
                DateTimeFormatter.ofPattern("d MMM yyyy"),
                DateTimeFormatter.ofPattern("MMM d, yyyy"),
                DateTimeFormatter.ofPattern("d MMMM yyyy"),
                DateTimeFormatter.ofPattern("yyyyMMdd")
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

    // for reading from file
    public static Task parseTaskFromString(String taskString) throws DukeException {
        String[] parts = taskString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String additionalInfo = parts.length > 3 ? parts[3].trim() : null;

        // need to add tasks to storage
        switch (taskType) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                if (additionalInfo == null) {
                    throw new DukeException("Invalid tasks.Deadline format in file");
                }
                LocalDateTime by = LocalDateTime.parse(additionalInfo);
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                String[] times = additionalInfo.split(" to ");
                if (times.length < 2) {
                    throw new DukeException("Invalid tasks.Event time format in file.");
                }
                LocalDateTime start = LocalDateTime.parse(times[0].trim());
                LocalDateTime end = LocalDateTime.parse(times[1].trim());

                Event event = new Event(description, start, end);
                if (isDone) event.markAsDone();
                return event;

            default:
                return null;
        }
    }

}
