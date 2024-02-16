package whisper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * The Parser class handles the parsing of user input and converts it into executable commands for the Whisper application.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object representing the parsed user input.
     * @throws WhisperException If there is an error parsing the input.
     */
    public static Command parse(String input) throws WhisperException {
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch(command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return createTodoCommand(parts);
        case "event":
            return createEventCommand(parts);
        case "deadline":
            return createDeadlineCommand(parts);
        case "delete":
            return createDeleteCommand(parts);
        case "mark":
            return createMarkCommand(parts);
        case "unmark":
            return createUnmarkCommand(parts);
        case "find":
            return createFindCommand(parts);
        default:
            //throw new WhisperException("Invalid command. Please enter a valid command.");
            throw WhisperException.unknownCommand();
        }
    }

    /**
     * Parses the user input for the 'todo' command and creates an AddCommand for adding a Todo task.
     *
     * @param parts The array of input parts.
     * @return The AddCommand for adding a Todo task.
     * @throws WhisperException If there is an error creating the Todo task.
     */
    private static Command createTodoCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Todo description cannot be empty.");
        }
        Task taskToAdd = new Task(parts[1], Task.TaskCategory.T);
        return new AddCommand(taskToAdd);
    }

    /**
     * Parses the user input for the 'event' command and creates an AddCommand for adding an Event task.
     *
     * @param parts The array of input parts.
     * @return The AddCommand for adding an Event task.
     * @throws WhisperException If there is an error creating the Event task.
     */
    private static Command createEventCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Event description cannot be empty.");
        }

        String[] eventDetails = parts[1].split("/from", 2);

        if (eventDetails.length < 2) {
            throw new WhisperException("Invalid event format. Please use: event [description] /from [start] /to [end]");
        }
        String eventName = eventDetails[0].trim();
        String[] timeParts = eventDetails[1].split("/to", 2);

        if (timeParts.length < 2) {
            throw new WhisperException("Invalid event format. Please use: event [description] /from [start] /to [end]");
        }
        LocalDateTime fromDateTime = parseDateTime(timeParts[0].trim());
        LocalDateTime toDateTime = parseDateTime(timeParts[1].trim());

        return new AddCommand(new Task(eventName, Task.TaskCategory.E, fromDateTime, toDateTime));
    }

    /**
     * Parses the user input for the 'deadline' command and creates an AddCommand for adding a Deadline task.
     *
     * @param parts The array of input parts.
     * @return The AddCommand for adding a Deadline task.
     * @throws WhisperException If there is an error creating the Deadline task.
     */
    private static Command createDeadlineCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Deadline description cannot be empty.");
        }

        String[] deadlineDetails = parts[1].split("/by", 2);

        if (deadlineDetails.length < 2) {
            throw new WhisperException("Invalid deadline format. Please use: deadline [description] /by [time]");
        }

        String deadlineName = deadlineDetails[0].trim();
        LocalDateTime deadlineDateTime = parseDateTime(deadlineDetails[1].trim());

        return new AddCommand(new Task(deadlineName, Task.TaskCategory.D, deadlineDateTime));
    }

    private static Command createDeleteCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Invalid delete command. Please specify the task number to delete.");
        }

        int index = parseTaskIndex(parts[1]);
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input for the 'mark' command and creates a MarkCommand for marking a task as done.
     *
     * @param parts The array of input parts.
     * @return The MarkCommand for marking a task as done.
     * @throws WhisperException If there is an error creating the MarkCommand.
     */
    private static Command createMarkCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Invalid mark command. Please specify the task number to mark as done.");
        }

        int index = parseTaskIndex(parts[1]);
        return new MarkCommand(index);
    }

    /**
     * Parses the user input for the 'unmark' command and creates an UnmarkCommand for marking a task as not done.
     *
     * @param parts The array of input parts.
     * @return The UnmarkCommand for marking a task as not done.
     * @throws WhisperException If there is an error creating the UnmarkCommand.
     */
    private static Command createUnmarkCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Invalid unmark command. Please specify the task number to mark as not done.");
        }

        int index = parseTaskIndex(parts[1]);
        return new UnmarkCommand(index);
    }

    /**
     * Parses the user input to extract the task index for 'delete', 'mark', and 'unmark' commands.
     *
     * @param input The input string containing the task index.
     * @return The parsed task index.
     * @throws WhisperException If there is an error parsing the task index.
     */
    private static int parseTaskIndex(String input) throws WhisperException {
        try {
            return Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new WhisperException("Invalid task number.");
        }
    }

    public static Command createFindCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Keyword cannot be empty for find command. Try again.");
        }
        return new FindCommand(parts[1].trim());
    }

    /**
     * Parses the date and time string into a LocalDateTime object.
     *
     * @param dateTime The date and time string.
     * @return The LocalDateTime object parsed from the input string.
     * @throws WhisperException If there is an error parsing the date and time.
     */
    private static LocalDateTime parseDateTime(String dateTime) throws WhisperException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            throw new WhisperException("Invalid datetime format. Please use the format: dd/MM/yyyy HH:mm");
        }
    }
}
