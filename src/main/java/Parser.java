import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The Parser class is responsible for parsing various user inputs related to tasks and dates.
 *
 * @author Tan Qin Yong
 */
public class Parser {
    /**
     * Constructs a Parser object.
     */
    public Parser() {}

    /**
     * Parses the user input for a deadline command.
     *
     * @param deadlineCommand The user input for the deadline command.
     * @return An array containing the task description and deadline date.
     */
    public String[] parseDeadline(String deadlineCommand) {
        deadlineCommand = deadlineCommand.replace("deadline", "")
                                         .replace("/by", "/");
        String[] splitCommand = deadlineCommand.split("/");
        splitCommand =  Arrays.stream(splitCommand)
                              .map(String::trim)
                              .toArray(String[]::new);
        return splitCommand;
    }

    /**
     * Parses the user input for an event command.
     *
     * @param eventCommand The user input for the event command.
     * @return An array containing the task description, start date, and end date.
     */
    public String[] parseEvent(String eventCommand) {
        eventCommand = eventCommand.replace("event", "")
                                   .replace("/from", "/")
                                   .replace("/to", "/");
        String[] splitCommand = eventCommand.split("/");
        splitCommand = Arrays.stream(splitCommand)
                             .map(String::trim)
                             .toArray(String[]::new);
        return splitCommand;
    }

    /**
     * Parses the user input for a to-do command.
     *
     * @param toDoCommand The user input for the to-do command.
     * @return The task description.
     */
    public String parseToDo(String toDoCommand) {
        toDoCommand = toDoCommand.replace("todo", "").trim();
        return toDoCommand;
    }

    /**
     * Parses the date string and converts it to a LocalDate object using the specified format.
     *
     * @param date The date string to be parsed.
     * @return The LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string cannot be parsed.
     */
    public LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
