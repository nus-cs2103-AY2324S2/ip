package ficin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ficin.task.Deadline;
import ficin.task.Event;
import ficin.task.Task;
import ficin.task.Todo;

/**
 * Handles parsing of user input into commands, tasks, and task details for the Duke application.
 * Provides utility methods to interpret user commands and to extract relevant details for task creation.
 */
public class Parser {

    /**
     * Parses the command from user input.
     *
     * @param userInput The user input string.
     * @return The CommandType corresponding to the parsed command.
     * @throws DukeException If the command is not recognized.
     */
    public static CommandType parseCommand(String userInput) throws DukeException {
        String[] parts = userInput.trim().split("\\s+", 2);

        String commandStr = parts[0].toUpperCase();

        try {
            return CommandType.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Processes the user input and creates the appropriate Command object.
     *
     * @param userInput The user input string.
     * @return The Command object corresponding to the user input.
     * @throws DukeException If there is an issue processing the command.
     */
    public static Command processCommand(String userInput) throws DukeException {
        try {
            CommandType command = Parser.parseCommand(userInput);

            switch (command) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand();
            case UNMARK:
                return new UnmarkCommand();
            case DELETE:
                return new DeleteCommand();
            case BYE:
                return new ExitCommand();
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                return new AddCommand();
            case FIND:
                return new FindCommand();
            case HELP:
                return new HelpCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses task details from user input and creates a Task object.
     *
     * @param userInput The user input string containing task details.
     * @return The Task object created based on the input.
     * @throws DukeException If there is an issue parsing the task or if the task type is not recognized.
     */
    public static Task parseTask(String userInput) throws DukeException {
        String[] parts = userInput.split("\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException("Description cannot be empty.");
        }

        String taskType = parts[0].toLowerCase();
        String taskDetails = parts[1];

        switch (taskType) {
        case "todo":
            return new Todo(taskDetails);
        case "deadline":
            return parseDeadline(taskDetails);
        case "event":
            return parseEvent(taskDetails);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses deadline details from user input and creates a Deadline object.
     *
     * @param taskDetails The task details string containing the description and deadline.
     * @return The Deadline object created based on the input.
     * @throws DukeException If there is an issue parsing the deadline or if the format is invalid.
     */
    private static Deadline parseDeadline(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid deadline format. Please include '/by' followed by the deadline.");
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        LocalDateTime dateTime = parseDateTime(by);

        return new Deadline(description, dateTime);
    }

    /**
     * Parses event details from user input and creates an Event object.
     *
     * @param taskDetails The task details string containing the description and event details.
     * @return The Event object created based on the input.
     * @throws DukeException If there is an issue parsing the event or if the format is invalid.
     */
    private static Event parseEvent(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Invalid event format. Please include '/from' "
                    + "followed by start time and '/to' followed by end time.");
        }

        String description = parts[0].trim();
        String[] dateAndTime = parts[1].split("/to", 2);
        if (dateAndTime.length < 2) {
            throw new DukeException("Invalid event format. Please include '/from' "
                    + "followed by start time and '/to' followed by end time.");
        }

        String start = dateAndTime[0].trim();
        String end = dateAndTime[1].trim();

        LocalDateTime startTime = parseDateTime(start);
        LocalDateTime endTime = parseDateTime(end);

        // Check if the start time is before the end time
        if (startTime.isAfter(endTime)) {
            throw new DukeException("Invalid event times. The start time is after the end time.");
        }

        return new Event(description, startTime, endTime);
    }

    /**
     * Parses date and time from a string and creates a LocalDateTime object.
     * Validates that the date is not before the current date.
     *
     * @param dateTimeStr The string containing date and time information.
     * @return The LocalDateTime object created based on the input.
     * @throws DukeException If there is an issue parsing the date and time, if the format is invalid,
     *                       or if the date is before today.
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) throws DukeException {
        LocalDateTime parsedDateTime;
        try {
            if (dateTimeStr.equalsIgnoreCase("today")) {
                parsedDateTime = LocalDateTime.now().with(LocalTime.MIN);
            } else if (dateTimeStr.equalsIgnoreCase("tomorrow")) {
                parsedDateTime = LocalDateTime.now().plusDays(1).with(LocalTime.MAX);
            } else if (dateTimeStr.contains(" ")) {
                parsedDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
            } else {
                LocalDate date = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                parsedDateTime = date.atStartOfDay();
            }
        } catch (Exception e) {
            throw new DukeException("Invalid date/time format. Please use "
                    + "'yyyy/MM/dd HHmm', 'yyyy/MM/dd', 'today', or 'tomorrow'.");
        }

        // Check if the parsed date is before today
        if (parsedDateTime.toLocalDate().isBefore(LocalDate.now())) {
            throw new DukeException("The date cannot be in the past.");
        }
        return parsedDateTime;
    }

    /**
     * Extracts the task index from user input.
     *
     * @param userInput The user input string containing the task index.
     * @return The extracted task index.
     * @throws DukeException If there is an issue extracting the index or if it's not a valid integer.
     */
    public static int extractTaskIndex(String userInput) throws DukeException {
        try {
            return Integer.parseInt(userInput.split("\\s+")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
    }
}
