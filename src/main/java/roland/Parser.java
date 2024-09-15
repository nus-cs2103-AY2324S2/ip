package roland;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.*;
import task.Deadlines;
import task.Events;
import task.ToDos;


/**
 * The Parser class is responsible for parsing user input and converting it into executable commands
 * in the task management application. It contains a static method, `parse`, which takes a full command string
 * and returns the corresponding Command object based on the recognized command keywords.
 *
 * @author wolffe88
 */

public class Parser {

    /**
     * Parses the given full command string and returns the corresponding Command object.
     *
     * @param fullCommand The user input representing a command.
     * @return A Command object corresponding to the parsed command.
     * @throws RolandException If the input command is not recognized or lacks necessary details.
     */
    public static Command parse(String fullCommand) throws RolandException {
        String commandType = fullCommand.split("\\s+")[0];

        switch (commandType) {
            case "list":
                return new ListCommand();
            case "find":
                return parseFindCommand(fullCommand);
            case "mark":
                return parseMarkCommand(fullCommand, true);
            case "unmark":
                return parseMarkCommand(fullCommand, false);
            case "note":
                return parseNoteCommand(fullCommand);
            case "delete":
                return parseDeleteCommand(fullCommand);
            case "todo":
                return parseTodoCommand(fullCommand);
            case "deadline":
                return parseDeadlineCommand(fullCommand);
            case "event":
                return parseEventCommand(fullCommand);
            case "clear":
                return new ClearCommand();
            case "help":
                return new HelpCommand();
            default:
                throw new RolandException("I do not understand you :(");
        }
    }

    private static Command parseFindCommand(String fullCommand) {
        String keyword = fullCommand.substring(5).trim();
        return new FindCommand(keyword);
    }

    private static Command parseMarkCommand(String fullCommand, boolean isMarked) {
        int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
        return new MarkCommand(index, isMarked);
    }

    private static Command parseNoteCommand(String fullCommand) {
        int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
        String[] split = fullCommand.split(" /");
        String notes = split[1];
        return new AddNotesCommand(index, notes);
    }

    private static Command parseDeleteCommand(String fullCommand) {
        int index = Integer.parseInt(fullCommand.replaceAll("[\\D]", ""));
        return new DeleteCommand(index);
    }

    private static Command parseTodoCommand(String fullCommand) throws RolandException {
        if (fullCommand.length() <= 5) {
            throw new RolandException("Please provide description for todo");
        }
        String description = fullCommand.substring(5).trim();
        return new AddCommand(new ToDos(description));
    }

    private static Command parseDeadlineCommand(String fullCommand) throws RolandException {
        if (fullCommand.length() <= 9) {
            throw new RolandException("Please provide description for deadline");
        }
        if (fullCommand.split("/").length < 2) {
            throw new RolandException("Please include when is the deadline by with /by <YYYY-mm-dd>");
        }
        String[] split = fullCommand.split(" /");
        String description = split[0].substring(9).trim();
        String by = split[1].substring(3).trim();
        LocalDate date = LocalDate.parse(by);
        return new AddCommand(new Deadlines(description, date));
    }

    private static Command parseEventCommand(String fullCommand) throws RolandException {
        if (fullCommand.length() <= 6) {
            throw new RolandException("Please provide description for event");
        }
        if (fullCommand.split("/").length != 3) {
            throw new RolandException(
                    "Please include when is the start and end of the event with /from <start> /to <end>");
        }
        String[] split = fullCommand.split(" /");
        String description = split[0].substring(6).trim();
        String from = split[1].substring(5).trim();
        String to = split[2].substring(4).trim();
        return new AddCommand(new Events(description, from, to));
    }


}
