package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NothingCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;
import duke.command.UnMarkCommand;
import duke.exceptions.ChatException;


/**
 * General parser for commands from the user.
 */
public class Parser {

    /**
     * Wrapper around Integer.parseInt to re-raise our
     * own exception.
     *
     * @param val The value to parse to an int.
     * @return Parsed value.
     * @throws ChatException if the value is invalid.
     */
    private static int parseInt(String val) {
        assert val != null;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new ChatException("Task number incorrect format!");
        }
    }

    /**
     * Validates user input.
     *
     * @param line      User input.
     * @param startFrom Which character to start checking from.
     * @return Processed string.
     */
    private static String getContents(String line, int startFrom) {
        assert line != null;
        String msg = line.substring(startFrom);
        if (msg.equals(" ") || msg.isEmpty()) {
            throw new ChatException("The description of a task cannot be empty.");
        }
        msg = msg.stripLeading();
        return msg;
    }

    /**
     * Returns a parsed date in the format day/Month/Year HoursMinutes
     *
     * @param date Raw input to pass.
     * @return A LocalDateTime object of the parse input.
     * @throws ChatException if the input is invalid
     */
    private static LocalDateTime parseDate(String date) {
        assert date != null;
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new ChatException("Invalid date time format");
        }
    }

    /**
     * Returns the corresponding Command to execute after parsing input
     * from the user.
     *
     * @param line Raw input from user.
     * @return Command to execute.
     * @throws ChatException if the command isn't valid.
     */
    public static Command parse(String line) {
        assert line != null;
        String command = line.split(" ")[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "sort":
            return new SortCommand();
        case "mark": {
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new MarkCommand(idx - 1);
        }
        case "unmark": {
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new UnMarkCommand(idx - 1);
        }
        case "delete":
            int idx = Parser.parseInt(line.split(" ")[1]);
            return new DeleteCommand(idx - 1);
        case "todo":
            return new TodoCommand(Parser.getContents(line, 4));
        case "deadline": {
            String[] res = Parser.getContents(line, 8).split(" /by ");
            return new DeadlineCommand(res[0], Parser.parseDate(res[1]));
        }
        case "event": {
            String[] res = Parser.getContents(line, 5).split(" ((/from)|(/to)) ");
            return new EventCommand(res[0], Parser.parseDate(res[1]), Parser.parseDate(res[2]));
        }
        case "find":
            return new FindCommand(Parser.getContents(line, 4));
        case "":
            return new NothingCommand();
        default:
            throw new ChatException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
