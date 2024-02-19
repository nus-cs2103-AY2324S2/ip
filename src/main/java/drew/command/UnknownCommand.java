package drew.command;

import drew.storage.Storage;
import drew.storage.TaskList;

/**
 * This class represents unkown commands.
 */
public class UnknownCommand extends Command {
    /**
     * Contains the static instance of the Unknown command.
     */
    public static final Command UNKNOWN = new UnknownCommand("");

    private UnknownCommand(String input) {
        super(input);
    }

    public static Command getUnkownCommand() {
        return UNKNOWN;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws IllegalArgumentException {
        return "Unknown command";
    }
}
