package jav.manager;

import java.util.Stack;

import jav.command.Command;

/**
* CommandHistoryManager manages a history of the user commands.
*/
public class CommandHistoryManager {
    /** A stack to store all commands done by the user. */
    private Stack<Command> commandHistory;

    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private CommandHistoryManager() {}
    private static class LazyHolder {
        static final CommandHistoryManager INSTANCE = new CommandHistoryManager();
    }
    public static CommandHistoryManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Push the latest command into the command history.
     *
     * @param command the latest command.
     */
    public void pushCommandToHistory(Command command) {
        if (commandHistory == null) {
            commandHistory = new Stack<Command>();
        }
        commandHistory.add(command);
    }

    /**
     * Pops the latest command in the command history.
     *
     * @return the latest command or null if there are no commands.
     */
    public Command popCommandToHistory() {
        if (commandHistory.size() == 0) {
            return null;
        }
        return commandHistory.pop();
    }
}
