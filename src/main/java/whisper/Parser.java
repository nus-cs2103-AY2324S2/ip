package whisper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
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
        default:
            //throw new WhisperException("Invalid command. Please enter a valid command.");
            throw WhisperException.unknownCommand();
        }
    }

    private static Command createTodoCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Todo description cannot be empty.");
        }
        Task taskToAdd = new Task(parts[1], Task.TaskCategory.T);
        return new AddCommand(taskToAdd);
    }

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

    private static Command createMarkCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Invalid mark command. Please specify the task number to mark as done.");
        }

        int index = parseTaskIndex(parts[1]);
        return new MarkCommand(index);
    }

    private static Command createUnmarkCommand(String[] parts) throws WhisperException {
        if (parts.length < 2) {
            throw new WhisperException("Invalid unmark command. Please specify the task number to mark as not done.");
        }

        int index = parseTaskIndex(parts[1]);
        return new UnmarkCommand(index);
    }

    private static int parseTaskIndex(String input) throws WhisperException {
        try {
            return Integer.parseInt(input.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new WhisperException("Invalid task number.");
        }
    }

    private static LocalDateTime parseDateTime(String dateTime) throws WhisperException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (Exception e) {
            throw new WhisperException("Invalid datetime format. Please use the format: dd/MM/yyyy HH:mm");
        }
    }
}
