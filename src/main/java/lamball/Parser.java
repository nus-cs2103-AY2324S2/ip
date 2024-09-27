package lamball;

import lamball.command.ByeCommand;
import lamball.command.Command;
import lamball.command.DeadlineCommand;
import lamball.command.DeleteCommand;
import lamball.command.EventCommand;
import lamball.command.FindCommand;
import lamball.command.MarkCommand;
import lamball.command.MemoAddCommand;
import lamball.command.MemoClearCommand;
import lamball.command.MemoDelCommand;
import lamball.command.PrintListCommand;
import lamball.command.PrintMemosCommand;
import lamball.command.ToDoCommand;
import lamball.command.UnMarkCommand;
import lamball.exception.LamballParseException;
import lamball.memo.Memo;
import lamball.memo.MemoList;

/**
 *  Contains methods related to parsing user's inputs.
 *
 * @author ongzhili
 */
public class Parser {
    private static final int TASK_MODIFYING_ARGUMENT_MINIMUM = 2;
    private static final int TASK_COMMAND_ARGUMENT_MINIMUM = 2;
    private static final int EVENT_ARGUMENT_COUNT = 3;
    private static final int DEADLINE_ARGUMENT_COUNT = 2;
    private static final String DEADLINE_BY = "by ";
    private static final String EVENT_FROM = "from ";
    private static final String EVENT_TO = "to ";

    /**
     * Parses command into String array in the form command type, arguments.
     *
     * @param msg Original input command.
     * @param tasks Task list (used when parsing task commands)
     * @param memos Memo list (used when parsing memo commands)
     * @boolean isInit Whether this is ran during initialization
     * @return String array of length 2, containing command type and arguments respectively.
     * @throws LamballParseException if invalid arguments are parsed.
     */
    public static Command parse(String msg, TaskList tasks, MemoList memos, boolean isInit)
            throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        switch (parts[0]) {
        case "memo": {
            Command memComd = memoParse(parts, memos);
            return memComd;
        }
        case "delete": {
            if (parts.length < TASK_MODIFYING_ARGUMENT_MINIMUM || !parts[1].matches("-?\\d+")) {
                throw new LamballParseException("Invalid number, baa.");
            }

            int idx = Integer.valueOf(parts[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new LamballParseException("Taaask index out of range, baa.");
            }

            return new DeleteCommand(idx, tasks);
        }
        case "mark": {
            if (parts.length < TASK_MODIFYING_ARGUMENT_MINIMUM || !parts[1].matches("-?\\d+")) {
                throw new LamballParseException("Invalid number, baa.");
            }

            int idx = Integer.valueOf(parts[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new LamballParseException("Taaask index out of range, baa.");
            }

            return new MarkCommand(idx, tasks, isInit);
        }
        case "unmark": {
            if (parts.length < TASK_MODIFYING_ARGUMENT_MINIMUM || !parts[1].matches("-?\\d+")) {
                throw new LamballParseException("Invalid number, baa.");
            }

            int idx = Integer.valueOf(parts[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new LamballParseException("Taaask index out of range, baa.");
            }

            return new UnMarkCommand(idx, tasks);
        }
        case "bye": {
            if (parts.length > 1) {
                throw new LamballParseException("Do not type anything after \"" + parts[0] + "\"");
            }
            return new ByeCommand();
        }
        case "list":
            if (parts.length > 1) {
                throw new LamballParseException("Do not type anything after \"" + parts[0] + "\"");
            }
            return new PrintListCommand(tasks);
        case "todo":
            if (parts.length < TASK_COMMAND_ARGUMENT_MINIMUM || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }
            return new ToDoCommand(parts[1], tasks, isInit);
        case "deadline": {
            if (parts.length < TASK_COMMAND_ARGUMENT_MINIMUM || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }

            String[] furtherSplit = parts[1].split(" /", 2);
            if (furtherSplit.length < DEADLINE_ARGUMENT_COUNT || !furtherSplit[1].substring(0, 3).equals(DEADLINE_BY)) {
                throw new LamballParseException("Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct fo"
                        + "rmaaat is: deadline <name> /by <time>, baa.");
            }

            return new DeadlineCommand(furtherSplit, tasks, isInit);
        }
        case "event": {
            if (parts.length < TASK_COMMAND_ARGUMENT_MINIMUM || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }

            String[] furtherSplit = parts[1].split(" /", 3);
            if (furtherSplit.length < EVENT_ARGUMENT_COUNT || !furtherSplit[1].substring(0, 5).equals(EVENT_FROM)
                    || !furtherSplit[2].substring(0, 3).equals(EVENT_TO)) {
                throw new LamballParseException("Event is in the wrong formaaaaaaat, baa. :(\n    Correct "
                        + "formaaat is: event <name> /from <time> /to <time>, baa.");
            }

            return new EventCommand(furtherSplit, tasks, isInit);
        }
        case "find": {
            if (parts.length < TASK_COMMAND_ARGUMENT_MINIMUM || parts[1] == null) {
                throw new LamballParseException("Invalid query");
            }

            return new FindCommand(parts[1], tasks);
        }
        default:
            throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }

    /**
     * Parses command for saved list.
     *
     * @param toParse String to be parsed.
     * @return Split line of command for further processing.
     * @throws LamballParseException
     */
    public static String[] parseSavedList(String toParse) throws LamballParseException {

        String[] divided = toParse.split(" \\| ", 2);

        // Means that it is not formatted correctly in the <0 or 1> | <command> format
        if (divided.length != 2 || !(Integer.valueOf(divided[0]) == 1 || Integer.valueOf(divided[0]) == 0)) {
            throw new LamballParseException("Corrupt format, ignoring...");
        }

        return divided;
    }

    /**
     * Parses a given memo text. Quite barebones as of now - Abstracted for potential future
     * implementation.
     *
     * @param memo Content of the memo (notes)
     * @return Memo object
     * @throws LamballParseException
     */
    public static Memo parseMemo(String memo) throws LamballParseException {
        String trimmed = memo.trim();
        if (memo == "") {
            throw new LamballParseException("Empty memo");
        }
        Memo temp = new Memo(memo);

        return temp;
    }

    /**
     * Parses memo related commands
     *
     * @param args Arguments for the command
     * @param memos List to do memo operations on
     * @return Command to run
     * @throws LamballParseException
     */
    public static Command memoParse(String[] args, MemoList memos) throws LamballParseException {
        assert args[0] == "memo" : "Only memo commands should reach here.";
        if (args.length < 2) {
            throw new LamballParseException("Invalid memo command, baa. Available commands are: memo add <memo>,"
                    + " memo del <index>, memo list, memo clear");
        }

        assert args.length >= 2 : "There should be something after memo";

        String[] furtherSplit = args[1].split(" ", 2);
        System.out.println(furtherSplit[0]);

        switch(furtherSplit[0]) {
        case "add": {
            String trimmedAgain = furtherSplit[1].trim();
            if (trimmedAgain == "") {
                throw new LamballParseException("Empty memo!");
            }

            assert trimmedAgain != "" : "Empty string!";
            return new MemoAddCommand(trimmedAgain, memos);
        }
        case "delete": {
            int idx = Integer.valueOf(furtherSplit[1]);
            if (idx <= 0 || idx > memos.size()) {
                throw new LamballParseException("Invalid index, baa.");
            }

            assert idx > 0 : "Not valid index";
            assert idx <= memos.size() : "Too high index";

            return new MemoDelCommand(idx, memos);
        }
        case "list": {

            return new PrintMemosCommand(memos);
        }
        case "clear": {
            return new MemoClearCommand(memos);
        }
        default:
            throw new LamballParseException("Invalid memo command, baa. Available commands are: memo add <memo>,"
            + " memo del <index>, memo list, memo clear");
        }
    }
}
