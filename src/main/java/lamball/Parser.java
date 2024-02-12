package lamball;

import lamball.command.ByeCommand;
import lamball.command.Command;
import lamball.command.DeadlineCommand;
import lamball.command.DeleteCommand;
import lamball.command.EventCommand;
import lamball.command.FindCommand;
import lamball.command.MarkCommand;
import lamball.command.PrintListCommand;
import lamball.command.ToDoCommand;
import lamball.command.UnMarkCommand;

/**
 *  Contains methods related to parsing user's inputs.
 *
 * @author ongzhili
 */
public class Parser {

    /**
     * Parses command into String array in the form command type, arguments.
     *
     * @param msg Original input command.
     * @return String array of length 2, containing command type and arguments respectively.
     * @throws LamballParseException if invalid arguments are parsed.
     */
    public static Command parse(String msg, TaskList tasks, boolean isInit) throws LamballParseException {
        String[] parts = msg.split(" ", 2);
        switch (parts[0]) {
        case "delete": {
            if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                throw new LamballParseException("Invalid number, baa.");
            } else {
                int idx = Integer.valueOf(parts[1]) - 1;
                if (idx >= tasks.size() || idx < 0) {
                    throw new LamballParseException("Taaask index out of range, baa.");
                }
            }
            int idx = Integer.valueOf(parts[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new LamballParseException("Taaask index out of range, baa.");
            }
            return new DeleteCommand(idx, tasks);
        }
        case "mark": {
            if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
                throw new LamballParseException("Invalid number, baa.");
            }
            int idx = Integer.valueOf(parts[1]) - 1;
            if (idx >= tasks.size() || idx < 0) {
                throw new LamballParseException("Taaask index out of range, baa.");
            }
            return new MarkCommand(idx, tasks, isInit);
        }
        case "unmark": {
            if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
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
            if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }
            return new ToDoCommand(parts[1], tasks, isInit);
        case "deadline": {
            if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }
            String[] furtherSplit = parts[1].split(" /", 2);
            if (furtherSplit.length < 2 || !furtherSplit[1].substring(0, 3).equals("by ")) {
                throw new LamballParseException("Deadline is in the wrong formaaaaaaat, baa. :(\n    Correct fo"
                        + "rmaaat is: deadline <name> /by <time>, baa.");
            }
            return new DeadlineCommand(furtherSplit, tasks, isInit);
        }
        case "event": {
            if (parts.length < 2 || parts[1] == null || parts[1].trim().isEmpty()) {
                // Checks if empty string (nothing after command) or only whitespaces
                throw new LamballParseException("Your " + parts[0] + " field is empty, baaaaka.");
            }
            String[] furtherSplit = parts[1].split(" /", 3);
            if (furtherSplit.length < 3 || !furtherSplit[1].substring(0, 5).equals("from ")
                    || !furtherSplit[2].substring(0, 3).equals("to ")) {
                throw new LamballParseException("Event is in the wrong formaaaaaaat, baa. :(\n    Correct "
                        + "formaaat is: event <name> /from <time> /to <time>, baa.");
            }
            return new EventCommand(furtherSplit, tasks, isInit);
        }
        case "find": {
            if (parts.length < 2 || parts[1] == null) {
                throw new LamballParseException("Invalid query");
            }
            return new FindCommand(parts[1], tasks);
        }
        default:
            throw new LamballParseException("Sorry, I don't understaaaaaand your commaaaaand, baa. :(");
        }
    }
}
