package duke.util;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

import java.util.regex.Pattern;

public class Parser {
    public enum InputType {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        UNKNOWN
    }

    public static InputType getCommandType(String input) {
        if (input.equals("list")) {
            return InputType.LIST;
        } else if (input.startsWith("mark")) {
            return InputType.MARK;
        } else if (input.startsWith("unmark")) {
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
        } else {
            return InputType.UNKNOWN;
        }
    }

    public static boolean matchPattern(String input, String pattern) {
        Pattern regexPattern = Pattern.compile(pattern);
        return regexPattern.matcher(input).matches();
    }

    public static Command parseCommand(String input) throws DukeException{
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
        case BYE:
            return new ExitCommand();
        case UNKNOWN:
            return new UnknownCommand();
        }
        return null;
    }

    private static Command parseMarkCommand(String input, boolean toMark) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (matchPattern(lowerInput, "mark\\s\\d+|unmark\\s\\d+")) {
            return new MarkCommand(input, toMark);
        } else {
            if (toMark) {
                throw new DukeException("Your mark instruction is unclear.\n" +
                        "\t Try 'mark [task number to mark as done]'.");
            } else {
                throw new DukeException("Your unmark instruction is unclear.\n" +
                        "\t Try 'unmark [task number to mark as not done]'.");
            }
        }
    }

    private static Command parseDeleteCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "delete\\s\\d+")) {
            return new DeleteCommand(input);
        } else {
            throw new DukeException("Your delete instruction is unclear.\n" +
                    "\t Try 'delete [task number to be deleted]'.");
        }
    }

    private static Command parseTodoCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "todo\\s.+")) {
            return new TodoCommand(input);
        } else {
            throw new DukeException("The description of a todo cannot be empty.\n" +
                    "\t Try 'todo [task description]'.");
        }
    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "deadline\\s.+\\s/by\\s.+")) {
            return new DeadlineCommand(input);
        } else {
            throw new DukeException("The description and due of a deadline cannot be empty.\n" +
                    "\t Try 'deadline [task description] /by [dd-MM-yyyy HH:mm]'.");
        }
    }

    private static Command parseEventCommand(String input) throws DukeException {
        String lowerInput = input.trim().toLowerCase();
        if (Parser.matchPattern(lowerInput, "event\\s.+\\s/from\\s.+\\s/to\\s.+")) {
            return new EventCommand(input);
        } else {
            throw new DukeException("The description, start and end time of an event cannot be empty.\n"
                    + "\t Try 'event [task description] /from [dd-MM-yyyy HH:mm] /to [dd-MM-yyyy HH:mm]'.");
        }
    }

}
