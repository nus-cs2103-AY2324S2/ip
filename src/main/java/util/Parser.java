package util;

import commands.*;
import exceptions.ChatBotException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input to create commands for executing various actions in the task manager.
 */
public class Parser {

    /**
     * Parses the first word of the input to determine the command to execute.
     *
     * @param input The user input string.
     * @return A command object corresponding to the input.
     */
    public Command parseCommand(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.split(" ", 2);
        String commandWord = sections[0].toUpperCase();

        switch (commandWord) {
        case "BYE":
            return new ByeCommand();
        case "TODO":
            return parseTodo(input);
        case "MARK":
            return parseMark(input);
        case "UNMARK":
            return parseUnmark(input);
        case "LIST":
            return new ListCommand();
        case "DELETE":
            return parseDelete(input);
        case "DEADLINE":
            return parseDeadline(input);
        case "EVENT":
            return parseEvent(input);
        case "FIND":
            return parseFind(input);
        default:
            return new InvalidCommand("Oops! Please enter a valid command.");
        }
    }

    /**
     * Parses the input string to create a command for adding a Todo task.
     *
     * @param input The user input string.
     * @return A command object for adding a Todo task based on the input.
     */
    public static Command parseTodo(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
            if (sections.length < 2) {
                return new InvalidCommand("Oops! Please enter a task description.");
            }
        String taskDescription = sections[1].trim();
        return new TodoCommand(taskDescription);
    }

    /**
     * Parses the input string to create a command for marking a task as done.
     *
     * @param input The user input string.
     * @return A command object for marking a task as done based on the input.
     */
    public static Command parseMark(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task number to mark.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new MarkCommand(index);
    }

    /**
     * Parses the input string to create a command for unmarking a task as done.
     *
     * @param input The user input string.
     * @return A command object for unmarking a task as done based on the input.
     */
    public static Command parseUnmark(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task number to mark.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new UnmarkCommand(index);
    }

    /**
     * Parses the input string to create a command for deleting a task.
     *
     * @param input The user input string.
     * @return A command object for deleting a task based on the input.
     */
    public static Command parseDelete(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1 || sections[1].isEmpty()) {
            return new InvalidCommand("Oops! Please enter a task number to delete.");
        }
        int index = Integer.parseInt(sections[1].trim());
        if (index <= 0) {
            return new InvalidCommand("Oops! Task number cannot be zero or negative.");
        }
        return new DeleteCommand(index);
    }

    /**
     * Parses the input string to create a command for adding a deadline task.
     *
     * @param input The user input string.
     * @return A command object for adding a deadline task based on the input.
     */
    public static Command parseDeadline(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task description.");
        }
        String taskDescriptionPlusDue = sections[1].trim();
        String[] parseTaskDescriptionPlusDue = taskDescriptionPlusDue.split(" /by ");
        if (parseTaskDescriptionPlusDue.length <= 1) {
            return new InvalidCommand("Oops! Please enter a valid deadline format.");
        }
        String taskDescription = parseTaskDescriptionPlusDue[0].trim();
        LocalDateTime due;
        try {
            due = parseDate(parseTaskDescriptionPlusDue[1].trim());
        } catch (ChatBotException e) {
            return new InvalidCommand("Oops! Please enter a valid date/time format.");
        }
        return new DeadlineCommand(taskDescription, due);
    }

    /**
     * Parses the input string to create a command for adding an event task.
     *
     * @param input The user input string.
     * @return A command object for adding an event task based on the input.
     */
    public static Command parseEvent(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task description.");
        }
        String eventPlusStartAndEnd = sections[1].trim();
        String[] parseEventPlusStartAndEnd = eventPlusStartAndEnd.split(" /from | /to ");
        if (parseEventPlusStartAndEnd.length <= 2) {
            return new InvalidCommand("Oops! Please enter a valid event format.");
        }
        String event = parseEventPlusStartAndEnd[0].trim();
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = parseDate(parseEventPlusStartAndEnd[1].trim());
            end = parseDate(parseEventPlusStartAndEnd[2].trim());
        } catch (ChatBotException e) {
            return new InvalidCommand("Oops! Please enter a valid date/time format.");
        }
        return new EventCommand(event, start, end);
    }

    /**
     * Parses the input string to create a command for finding tasks.
     *
     * @param input The user input string.
     * @return A command object for finding tasks based on the input.
     */
    public static Command parseFind(String input) {
        assert input != null && !input.isEmpty() : "Oops! Input must not be null or empty.";
        String[] sections = input.trim().split(" ", 2);
        if (sections.length <= 1) {
            return new InvalidCommand("Oops! Please enter a task to find.");
        }
        String tasktoFind = sections[1].trim();
        return new FindCommand(tasktoFind);
    }

    /**
     * Parses a date string to LocalDateTime.
     *
     * @param dateString The date string to parse.
     * @return The LocalDateTime object parsed from the date string.
     * @throws ChatBotException If the date string is in an invalid format.
     */
    public static LocalDateTime parseDate(String dateString) throws ChatBotException {
        assert dateString != null && !dateString.isEmpty() : "Oops! DateString must not be null or empty.";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateString, dateTimeFormatter);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(dateString, dateFormatter).atStartOfDay();
            } catch (DateTimeParseException e2) {
            }
        }
        throw new ChatBotException("Oops! Invalid date and time format. " +
                "Please enter in yyyy-MM-dd HHmm format, you may leave HHmm empty.");
    }

    /**
     * Parses a string representing a task from a file and returns the corresponding Task object.
     *
     * @param tasksFromFile The string representing the task from the file.
     * @return The Task object parsed from the string.
     * @throws ChatBotException If the string representation of the task is invalid.
     */
    public static Task parseTasksFromFile(String tasksFromFile) throws ChatBotException {
        assert tasksFromFile != null && !tasksFromFile.isEmpty() : "Oops! TaskString must not be null or empty.";
        String[] sections = tasksFromFile.split("\\|");
        if (sections.length < 3 || sections.length > 4) {
            throw new ChatBotException("Oops! tasks.Task format is invalid.");
        }
        String taskType = sections[0];
        boolean isDone = sections[1].trim().equals("1");
        String taskDescription = sections[2].trim();
        String dateOrTime = sections.length > 3 ? sections[3].trim() : null;

        switch (taskType) {
        case "T":
            ToDo todo = new ToDo(taskDescription);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (dateOrTime == null) {
                throw new ChatBotException("Oops! tasks.Deadline format is invalid.");
            }
            LocalDateTime due = LocalDateTime.parse(dateOrTime);
            Deadline deadline = new Deadline(taskDescription, due);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            String[] startAndEnd = dateOrTime.split("to");
            if (startAndEnd.length < 2) {
                throw new ChatBotException("Oops! tasks.Event format is invalid.");
            }
            LocalDateTime eventStart = LocalDateTime.parse(startAndEnd[0].trim());
            LocalDateTime eventEnd = LocalDateTime.parse(startAndEnd[1].trim());
            Event event = new Event(taskDescription, eventStart, eventEnd);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new ChatBotException("Oops! Unknown task type");
        }
    }
}


