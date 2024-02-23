package duke.parser;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.command.bye.ByeCommand;
import duke.command.sort.SortCommand;
import duke.command.taskNo.DeleteCommand;
import duke.command.find.FindCommand;
import duke.command.list.ListCommand;
import duke.command.taskNo.MarkCommand;
import duke.command.taskNo.TaskNoCommand;
import duke.command.taskNo.UnmarkCommand;
import duke.command.task.DeadlineCommand;
import duke.command.task.EventCommand;
import duke.command.task.TaskCommand;
import duke.command.task.ToDoCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Parser class that deals with making sense of the user command
 * @author se-edu
 * Adapted from https://github.com/se-edu/addressbook-level2
 */
public class Parser {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<args>.*)");

    /**
     * Processes a string input to decide which command to execute
     * @param input
     * @return Command
     */
    public Command parse(String input) {
        final Matcher matcher = COMMAND_FORMAT.matcher(input.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(Command.INVALID_COMMAND);
        }

        String command = matcher.group("command");
        String args = matcher.group("args");

        switch (command) {
            case ToDoCommand.COMMAND:
                return parseToDoCommand(args);

            case DeadlineCommand.COMMAND:
                return parseDeadlineCommand(args);

            case EventCommand.COMMAND:
                return parseEventCommand(args);

            case MarkCommand.COMMAND:
                return parseMarkCommand(args);

            case UnmarkCommand.COMMAND:
                return parseUnmarkCommand(args);

            case FindCommand.COMMAND:
                return parseFindCommand(args);

            case DeleteCommand.COMMAND:
                return parseDeleteCommand(args);

            case ListCommand.COMMAND:
                return new ListCommand();

            case SortCommand.COMMAND:
                return parseSortCommand(args);

            case ByeCommand.COMMAND:
                return new ByeCommand();

            default:
                return new InvalidCommand(Command.INVALID_COMMAND);
        }

    }

    /**
     * parses ToDo Command
     * @param args
     * @return Command (ToDo/ Invalid)
     */
    public Command parseToDoCommand(String args) {
        String task = args.trim();
        if (task.isEmpty()) {
            return new InvalidCommand(ToDoCommand.INVALID_COMMAND);
        }

        return new ToDoCommand(task);
    }

    /**
     * parses Deadline Command
     * @param args
     * @return Command (Deadline/ Invalid)
     */
    public Command parseDeadlineCommand(String args) {
        final Matcher matcher = DeadlineCommand.ARG_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(DeadlineCommand.INVALID_COMMAND);
        }

        try {
            final String task = matcher.group("task");
            final LocalDateTime by = LocalDateTime.parse(matcher.group("by"), dateTimeFormatter);

            if (task.isEmpty()) {
                return new InvalidCommand(DeadlineCommand.INVALID_COMMAND);
            }
            return new DeadlineCommand(task, by);
        } catch (DateTimeException e) {
            return new InvalidCommand(TaskCommand.COMMAND_INVALID_DATETIME);
        }
    }

    /**
     * parses Event Command
     * @param args
     * @return Command (Event/ Invalid)
     */
    private Command parseEventCommand(String args) {
        final Matcher matcher = EventCommand.ARG_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(EventCommand.INVALID_COMMAND);
        }

        try {
            final String task = matcher.group("task");
            final LocalDateTime from = LocalDateTime.parse(matcher.group("from"), dateTimeFormatter);
            final LocalDateTime to = LocalDateTime.parse(matcher.group("to"), dateTimeFormatter);

            if (task.isEmpty()) {
                return new InvalidCommand(EventCommand.INVALID_COMMAND);
            }
            return new EventCommand(task, from, to);
        } catch (DateTimeException e) {
            return new InvalidCommand(TaskCommand.COMMAND_INVALID_DATETIME);
        }
    }

    /**
     * parses Mark Command
     * @param args
     * @return Command (Mark/ Invalid)
     */
    private Command parseMarkCommand(String args) {
        try {
            int taskNo = Integer.parseInt(args.trim());
            return new MarkCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MarkCommand.COMMAND_INVALID_INTEGER);
        } catch (NullPointerException e) {
            return new InvalidCommand(Command.ERROR_LIST_EMPTY);
        } catch (IndexOutOfBoundsException e) {
            return TaskNoCommand.errorTaskNotExist();
        }
    }

    /**
     * parses Unmark Command
     * @param args
     * @return Command (Unmark/ Invalid)
     */
    private Command parseUnmarkCommand(String args) {
        try {
            int taskNo = Integer.parseInt(args.trim());
            return new UnmarkCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(UnmarkCommand.COMMAND_INVALID_INTEGER);
        } catch (NullPointerException e) {
            return new InvalidCommand(Command.ERROR_LIST_EMPTY);
        } catch (IndexOutOfBoundsException e) {
            return TaskNoCommand.errorTaskNotExist();
        }
    }

    /**
     * parses Delete Command
     * @param args
     * @return Command (Delete/ Invalid)
     */
    private Command parseDeleteCommand(String args) {
        try {
            int taskNo = Integer.parseInt(args.trim());
            return new DeleteCommand(taskNo);
        } catch (NumberFormatException e) {
            return new InvalidCommand(DeleteCommand.COMMAND_INVALID_INTEGER);
        } catch (NullPointerException e) {
            return new InvalidCommand(Command.ERROR_LIST_EMPTY);
        } catch (IndexOutOfBoundsException e) {
            return TaskNoCommand.errorTaskNotExist();
        }
    }

    /**
     * parses Find Command
     * @param args
     * @return Command (Find/ Invalid)
     */
    private Command parseFindCommand(String args) {
        String keyword = args.trim();
        return new FindCommand(keyword);
    }

    private Command parseSortCommand(String args) {
        String type = args.trim();
        if (type.isEmpty()) {
            return new InvalidCommand(SortCommand.INVALID_COMMAND);
        }

        return new SortCommand(type);
    }

}