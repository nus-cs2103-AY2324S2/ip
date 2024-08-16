package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a help command.
 */
public class HelpCommand extends Command {
    private static final String MANUAL = "Usage: help <command>\n\n"
            + "Displays the manual for the specified command, or the list of commands if no command is specified.\n\n"
            + "Aliases: h";

    /** Supported commands */
    private static final String[] COMMANDS = new String[] { "list", "mark", "unmark", "bye", "todo", "deadline",
        "event", "delete", "find", "undo", "redo", "help" };

    private static final String LINE = "----------------------------------------\n";

    /**
     * Creates a new help command.
     *
     * @param body The body of the command.
     */
    public HelpCommand(String body) {
        super(body);
    }

    /**
     * Gets the manual for the help command.
     *
     * @return The manual for the help command.
     */
    public static String getManual() {
        return MANUAL;
    }

    private String getManualString(String command) {
        switch (command) {
        case "ls":
        case "list":
            return ListCommand.getManual();
        case "mk":
        case "mark":
            return MarkCommand.getManual();
        case "umk":
        case "unmark":
            return UnmarkCommand.getManual();
        case "ex":
        case "exit":
        case "bye":
            return ExitCommand.getManual();
        case "td":
        case "todo":
            return TodoCommand.getManual();
        case "dl":
        case "deadline":
            return DeadlineCommand.getManual();
        case "ev":
        case "event":
            return EventCommand.getManual();
        case "d":
        case "delete":
            return DeleteCommand.getManual();
        case "f":
        case "find":
            return FindCommand.getManual();
        case "ud":
        case "undo":
            return UndoCommand.getManual();
        case "rd":
        case "redo":
            return RedoCommand.getManual();
        case "h":
        case "help":
            return HelpCommand.getManual();
        default:
            return null;
        }
    }

    @Override
    public String execute(TaskList list, ProgramState state) throws SylviaException {
        String body = this.getBody().trim();
        String response = getManualString(body);
        if (body.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the list of commands that you can use:\n");
            for (String command : COMMANDS) {
                sb.append(LINE + command + "\n" + LINE + getManualString(command) + "\n" + LINE + "\n\n");
            }
            response = sb.toString();
        }
        if (response == null) {
            throw new UnknownCommandException("Unknown command: " + body,
                    "I'm sorry, I can't find the manual for that command :<");
        }
        state.setNormal();
        return response.trim();
    }
}
