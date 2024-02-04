package panda.command;
import panda.component.*;
public class ExitCommand extends Command {

    public void execute(TaskList tlist) {
        return;
    }

    /**
     * Shows goodbye message on Ui
     * 
     * @param tlist the current TaskList. (unused)
     * @param ui the UI to show message.
     * @param cacheFile the cache file. (unused)
     */
    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        ui.showBye();
        return;
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
