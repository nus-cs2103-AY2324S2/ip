package duke.util;

import java.util.regex.Pattern;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

/**
 * Represents a parser to process and parse command.
 */
public class Parser {
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String SPACE = "\\s";
    private static final String SPACE_DIGIT = SPACE + "\\d+";
    private static final String SPACE_MORE = SPACE + ".+";

    /**
     * Sets the possible types of input.
     */
    public enum InputType {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        UNKNOWN
    }

    /**
     * Returns the type of command by checking the keyword used at start of input.
     *
     * @param input Command input by user.
     * @return Corresponding enum value of command.
     */
    public static InputType getCommandType(String input) {
        if (input.equals("list")) {
            return InputType.LIST;
        } else if (input.startsWith("mark")) {
            return InputType.MARK;
        } else if (input.startsWith(UNMARK)) {
            return InputType.UNMARK;
        } else if (input.startsWith("delete")) {
            return InputType.DELETE;
        } else if (input.equals("bye")) {
            return InputType.BYE;
        } else if (input.startsWith("todo")) {
            return InputType.TODO;
        } else if (input.startsWith("deadline")) {
            return InputType.DEADLINE;
        } else if (input.startsWith("event")) {
            return InputType.EVENT;
        } else if (input.startsWith("find")) {
            return InputType.FIND;
        } else {
            return InputType.UNKNOWN;
        }
    }

    /**
     * Checks if input matches the regex pattern.
     *
     * @param input Command entered by user.
     * @param pattern Regex pattern of a valid command format.
     * @return True if matches, false otherwise.
     */
    public static boolean matchPattern(String input, String pattern) {
        return Pattern.matches(pattern, input);
    }

    /**
     * Returns the corresponding Command for command given by user.
     *
     * @param input Command entered by user.
     * @return Subclass of Command based on keyword and format of command.
     * @throws DukeException If command is not in the correct format.
     */
    public static Command parseCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        InputType commandType = getCommandType(lowerInput);

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
            try {
                return parseMarkCommand(input, true);
            } catch (DukeException e) {
                throw e;
            }
        case UNMARK:
            try {
                return parseMarkCommand(input, false);
            } catch (DukeException e) {
                throw e;
            }
        case DELETE:
            try {
                return parseDeleteCommand(input);
            } catch (DukeException e) {
                throw e;
            }
        case TODO:
            try {
                return parseTodoCommand(input);
            } catch (DukeException e) {
                throw e;
            }
        case DEADLINE:
            try {
                return parseDeadlineCommand(input);
            } catch (DukeException e) {
                throw e;
            }
        case EVENT:
            try {
                return parseEventCommand(input);
            } catch (DukeException e) {
                throw e;
            }
        case FIND:
            try {
                return parseFindCommand(input);
            } catch (DukeException e) {
                throw e;
            }
        case BYE:
            return new ExitCommand();
        case UNKNOWN:
            return new UnknownCommand();
        default:
        }
        return null;
    }

    private static Command parseMarkCommand(String input, boolean toMark) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (matchPattern(lowerInput, MARK + SPACE_DIGIT + "|" + UNMARK + SPACE_DIGIT)) {
            return new MarkCommand(input, toMark);
        } else {
            if (toMark) {
                throw new DukeException("Your " + MARK + " instruction is unclear.\n"
                        + "Try '" + MARK + " [task number to mark as done]'.");
            } else {
                throw new DukeException("Your " + UNMARK + " instruction is unclear.\n"
                        + "Try '" + UNMARK + " [task number to mark as not done]'.");
            }
        }
    }

    private static Command parseDeleteCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "delete" + SPACE_DIGIT)) {
            return new DeleteCommand(input);
        } else {
            throw new DukeException("Your delete instruction is unclear.\n"
                    + "Try 'delete [task number to be deleted]'.");
        }
    }

    private static Command parseTodoCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "todo" + SPACE_MORE)) {
            return new TodoCommand(input);
        } else {
            throw new DukeException("The description of a todo cannot be empty.\n"
                    + "Try 'todo [task description]'.");
        }
    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "deadline" + SPACE_MORE + SPACE + "/by" + SPACE_MORE)) {
            return new DeadlineCommand(input);
        } else {
            throw new DukeException("The description and due of a deadline cannot be empty.\n"
                    + "Try 'deadline [task description] /by [dd-MM-yyyy HH:mm]'.");
        }
    }

    private static Command parseEventCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput,
                "event" + SPACE_MORE + SPACE + "/from" + SPACE_MORE + SPACE + "/to" + SPACE_MORE)) {
            return new EventCommand(input);
        } else {
            throw new DukeException("The description, start and end time of an event cannot be empty.\n"
                    + "Try 'event [task description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]'.");
        }
    }

    /**
     * Returns FindCommand if input is valid.
     *
     * @param input Command entered by user.
     * @return FindCommand with input given.
     * @throws DukeException If input is in invalid format.
     */
    private static Command parseFindCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "find" + SPACE_MORE)) {
            String[] keywords = input.substring(4).trim().split(SPACE);
            return new FindCommand(keywords);
        } else {
            throw new DukeException("Specify 1 or more keyword/s to search.\n"
                    + "Try 'find [keywords]'.");
        }
    }

}
