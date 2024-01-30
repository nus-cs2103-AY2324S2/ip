package nollid;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import nollid.commands.ByeCommand;
import nollid.commands.Command;
import nollid.commands.DeadlineCommand;
import nollid.commands.DeleteCommand;
import nollid.commands.EventCommand;
import nollid.commands.HelpCommand;
import nollid.commands.ListCommand;
import nollid.commands.MarkCommand;
import nollid.commands.TodoCommand;
import nollid.commands.UnmarkCommand;
import nollid.exceptions.InvalidCommandException;
import nollid.tasks.Deadline;

/**
 * Parser class provides a static method to parse user input and return the corresponding Command object.
 */
public class Parser {
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    public static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    /**
     * Parses the full user command and returns the appropriate Command object.
     *
     * @param fullCommand The full user command input.
     * @return The corresponding Command object based on the parsed input.
     * @throws InvalidCommandException If the user command is invalid or not recognized.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        if (fullCommand.isBlank()) {
            throw new InvalidCommandException("Command is blank.");
        }
        // Split user input into individual words
        // e.g. "i am user input" -> ["i", "am", "user", "input"]
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(fullCommand.split(" ")));
        String commandKeyword = argsList.get(0);

        if (commandKeyword.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (commandKeyword.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (commandKeyword.equalsIgnoreCase("mark")) {
            return new MarkCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("unmark")) {
            return new UnmarkCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("todo")) {
            return new TodoCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("deadline")) {
            return new DeadlineCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("event")) {
            return new EventCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("delete")) {
            return new DeleteCommand(argsList);
        } else if (commandKeyword.equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else {
            throw new InvalidCommandException("No valid command detected.");
        }
    }

    /**
     * Returns a LocalDateTime object from a date (and time) string.
     *
     * @throws DateTimeParseException if unable to retrieve a date (and time) from the string.
     */
    public static LocalDateTime getLocalDateTimeFromString(String deadlineString) throws DateTimeParseException {
        ArrayList<String> deadlineList = new ArrayList<>(Arrays.asList(deadlineString.split(" ")));

        LocalDate deadlineDate = LocalDate.parse(deadlineList.get(0), Parser.DATE_INPUT_FORMAT);

        // If only date provided, use the default time
        if (deadlineList.size() == 1) {
            return LocalDateTime.of(deadlineDate, Parser.DEFAULT_TIME);
        } else {
            LocalTime deadlineTime = LocalTime.parse(deadlineList.get(1), Parser.TIME_FORMAT);
            return LocalDateTime.of(deadlineDate, deadlineTime);
        }
    }
}
