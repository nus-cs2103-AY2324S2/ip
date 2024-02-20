package lemona.oop;

import java.util.Arrays;

import lemona.command.ByeCommand;
import lemona.command.Command;
import lemona.command.DeadlineCommand;
import lemona.command.DeleteCommand;
import lemona.command.EventCommand;
import lemona.command.FindCommand;
import lemona.command.ListCommand;
import lemona.command.MarkCommand;
import lemona.command.TodoCommand;
import lemona.command.UnmarkCommand;
import lemona.command.WrongInputCommand;

import lemona.exceptions.MissingIndexException;
import lemona.exceptions.MissingDescriptionException;

/**
 * Represents a parser utility for parsing user input.
 * Parser parses the user input into meaningful parts for further processing.
 */
public class Parser {

    /**
     * Trims the input string and splits it into parts.
     *
     * @param input The input string to be trimmed and split.
     * @return An array containing the trimmed and non-empty parts of the input string.
     */
    public static String[] trim(String input) {
        assert !input.isEmpty() : "empty input is not allowed!";
        String[] untrimmedParts = input.split(" ", 2);
        String[] parts = Arrays.stream(untrimmedParts)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        return parts;
    }

    /**
     * Parses the input string into meaningful parts.
     *
     * @param input The input string to be parsed.
     * @return A Command corresponding to the user input.
     */
    public static Command parse(String input) throws MissingIndexException, MissingDescriptionException {
        String[] parts = trim(input);
        int size = parts.length;
        if (parts[0].equals("mark") || parts[0].equals("unmark") || parts[0].equals("delete") && size == 1) {
            if (size == 1) {
                throw new MissingIndexException();
            }
        }
        assert parts[0].equals("find") && size > 1: "No keyword will result in printing all tasks!";

        switch (parts[0]) {
        case ("deadline"):
            if (size == 1 || parts[1].split("/by ").length == 1) {
                throw new MissingDescriptionException();
            }

            String[] content = parts[1].split("/by ");
            String[] temp = parts;
            parts = new String[3];
            parts[0] = temp[0];
            parts[1] = content[0];
            parts[2] = content[1];
            return new DeadlineCommand(parts);
        case ("event"):
            if (size == 1 || parts[1].split("/from ").length == 1) {
                throw new MissingDescriptionException();
            }

            content = parts[1].split("/from ");

            if (size == 1 || content[1].split("/to ").length == 1) {
                throw new MissingDescriptionException();
            }

            String[] dates = content[1].split(" /to ");
            temp = parts;
            parts = new String[4];
            parts[0] = temp[0];
            parts[1] = content[0];
            parts[2] = dates[0];
            parts[3] = dates[1];

            return new EventCommand(parts);
        case ("todo"):
            if (size == 1) {
                throw new MissingDescriptionException();
            }
            return new TodoCommand(parts);
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(parts);
        case ("unmark"):
            return new UnmarkCommand(parts);
        case ("bye"):
            return new ByeCommand();
        case ("delete"):
            return new DeleteCommand(parts);
        case ("find"):
            return new FindCommand(parts);
        default:
            return new WrongInputCommand();
        }
    }
}
