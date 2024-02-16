package parser;

import actions.*;
import exceptionhandling.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The `Parser` class is responsible for parsing user commands and converting them into executable actions.
 * It provides methods to parse different types of commands and handles various exceptions related to command parsing.
 */
public class Parser {

    /**
     * Parses the user command and returns the corresponding action.
     *
     * @param command The user command to be parsed.
     * @return The corresponding action based on the parsed command.
     * @throws DukeException If there is an error in parsing the command or if the command is invalid.
     */
    public static Action parseCommand(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        String method = splitCommand[0];
        switch (method) {
        case "hi" :
            return new Greet();
        case "list":
            return new DisplayList();
        case "bye":
            return new Bye();
        case "mark":
            return new Mark(command);
        case "unmark":
            return new Unmark(command);
        case "todo":
            return new CreateTodo(command);
        case "deadline":
            return new CreateDeadline(command);
        case "event":
            return new CreateEvent(command);
        case "delete":
            return new Delete(command);
        case "find":
            return new Find(command);
        default:
            return new InvalidAction();
        }
    }

    /**
     * Parses a date string and returns a LocalDate object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the date string is in the wrong format.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, f);
    }
}
