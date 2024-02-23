package panda.command;

import panda.component.*;

import panda.exception.PandaException;

public class ExitCommand extends Command {
    public void execute(TaskList tlist) {
        return;
    }

    /**
     * Generates goodbye message
     * 
     * @param tlist the current TaskList. (unused)
     * @param cacheFile the cache file. (unused)
     * @return the goodbye message.
     * @throws PandaException if an error occurs during execution.
     */
    public String execute(TaskList tlist, Storage cacheFile) throws PandaException {
        return "Bye. Hope to see you again soon!\n"
            + "Panda will exit in 3 seconds.";
    }


    /**
     * Checks if the command is an exit command.
     * @return true, as this command is an exit command.
     */
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
