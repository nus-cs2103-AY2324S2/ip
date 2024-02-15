package duck;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The TaskFactory class provides methods to create different types of tasks based on user input.
 */
public class TaskFactory {

    /**
     * Creates a task based on the provided user input.
     *
     * @param userInput The user input specifying the task type and details.
     * @return The created task.
     * @throws DukeException If the user input is invalid or incomplete.
     */
    public static Task createTask(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ", 2);

        if (!(inputParts[0].equals("todo")) && !(inputParts[0].equals("deadline"))
                && !(inputParts[0].equals("event"))) {
            throw new DukeException("Don't talk nonsense");
        } else if (inputParts.length < 2) {
            throw new DukeException("What do you want to do");
        }

        String taskType = inputParts[0].toLowerCase();

        switch (taskType) {
        case "todo":
            return new Todo(inputParts[1]);
        case "deadline":
            return createDeadlineTask(inputParts[1]);
        case "event":
            return createEventTask(inputParts[1]);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Creates a Deadline task based on the provided input.
     *
     * @param input The input specifying the deadline task details.
     * @return The created Deadline task.
     * @throws DukeException If the input is invalid or incomplete.
     */
    private static Task createDeadlineTask(String input) throws DukeException {
        String[] parts = input.split("/by");
        if (parts.length < 2) {
            throw new DukeException("Please provide a deadline in the format '/by <yyyy-mm-dd>'");
        }

        LocalDate dateTime = parseDate(parts[1].trim());
        return new Deadline(parts[0].trim(), dateTime);
    }

    /**
     * Creates an Event task based on the provided input.
     *
     * @param input The input specifying the event task details.
     * @return The created Event task.
     * @throws DukeException If the input is invalid or incomplete.
     */
    private static Task createEventTask(String input) throws DukeException {
        String[] parts = input.split("/from", 2);
        if (parts.length < 2) {
            throw new DukeException("Please provide an event in the format '/from <yyyy-mm-dd> /to <yyyy-mm-dd>'");
        }

        LocalDate fromDateTime = parseDate(parts[1].trim().split("/to")[0].trim());
        LocalDate toDateTime = parseDate(parts[1].trim().split("/to")[1].trim());

        return new Event(parts[0].trim(), fromDateTime, toDateTime);
    }

    /**
     * Parses a date string in the format 'yyyy-mm-dd'.
     *
     * @param dateString The date string to parse.
     * @return The parsed LocalDate.
     * @throws DukeException If the date string is in an invalid format.
     */
    private static LocalDate parseDate(String dateString) throws DukeException {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'yyyy-mm-dd'");
        }
    }
}
