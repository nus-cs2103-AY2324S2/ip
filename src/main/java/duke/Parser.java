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
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            throw new ChatException("Task number incorrect format!");
        }
    }

    /**
     * Simple validator to ensure the user doesn't pass in
     * bad content.
     *
     * @param line      User input.
     * @param startFrom Which character to start checking from.
     * @return Processed string.
     */
    private static String getContents(String line, int startFrom) {
        String msg = line.substring(startFrom);
        if (msg.equals(" ") || msg.isEmpty()) {
            throw new ChatException("The description of a todo cannot be empty.");
        }
        msg = msg.stripLeading();
        return msg;
    }

    /**
     * Parse a date in the format day/Month/Year HoursMinutes
     *
     * @param date Raw input to pass.
     * @return A LocalDateTime object of the parse input.
     * @throws ChatException if the input is invalid
     */
    private static LocalDateTime parseDate(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new ChatException("Invalid date time format");
        }
    }

    /**
     * Parse input from the user, returning the corresponding Command to execute.
     *
     * @param line Raw input from user.
     * @return Command to execute.
     * @throws ChatException if the command isn't valid.
     */
    public static Command parse(String line) {
        String command = line.split(" ")[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
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
