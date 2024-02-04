package panda.command;
import panda.component.*;
public class ExitCommand extends Command {

    public void execute(TaskList tlist) {
        return;
    }

    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        ui.showBye();
        return;
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
