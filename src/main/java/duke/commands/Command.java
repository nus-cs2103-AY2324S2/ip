package duke.commands;
import duke.*;

public abstract class Command {
    private boolean isExit;
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException;
}
