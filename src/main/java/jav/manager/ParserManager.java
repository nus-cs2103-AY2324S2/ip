package jav.manager;

import jav.command.Command;
import jav.command.DeleteTaskCommand;
import jav.command.FindTaskCommand;
import jav.command.ListTasksCommand;
import jav.command.ShutdownCommand;
import jav.command.StoreTaskCommand;
import jav.command.UndoCommand;
import jav.command.UpdateTaskMarkCommand;
import jav.exception.InvalidCommandException;

/**
* ParserManager handles parsing logic of commands.
*/
public class ParserManager {
    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private ParserManager() {}
    private static class LazyHolder {
        static final ParserManager INSTANCE = new ParserManager();
    }
    public static ParserManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Checks if the given command is a valid command and returns the requested command.
     *
     * @param cmd the command to check.
     * @param param the parameters of the command.
     * @return the requested command.
     */
    public Command checkCommand(String cmd, String param) throws InvalidCommandException {
        if (cmd.equals("bye") || cmd.equals("b")) {
            return new ShutdownCommand();
        } else if (cmd.equals("deadline") || cmd.equals("d")) {
            return new StoreTaskCommand(StorageManager.StorageType.DEADLINE, param);
        } else if (cmd.equals("event") || cmd.equals("e")) {
            return new StoreTaskCommand(StorageManager.StorageType.EVENT, param);
        } else if (cmd.equals("list") || cmd.equals("l")) {
            return new ListTasksCommand();
        } else if (cmd.equals("remove") || cmd.equals("r")) {
            return new DeleteTaskCommand(param);
        } else if (cmd.equals("mark") || cmd.equals("m")) {
            return new UpdateTaskMarkCommand(true, param);
        } else if (cmd.equals("todo") || cmd.equals("t")) {
            return new StoreTaskCommand(StorageManager.StorageType.TODO, param);
        } else if (cmd.equals("unmark") || cmd.equals("u")) {
            return new UpdateTaskMarkCommand(false, param);
        } else if (cmd.equals("find") || cmd.equals("f")) {
            return new FindTaskCommand(param);
        } else if (cmd.equals("undo") || cmd.equals("un")) {
            return new UndoCommand();
        } else {
            throw new InvalidCommandException("Unknown command", null);
        }
    }
}
