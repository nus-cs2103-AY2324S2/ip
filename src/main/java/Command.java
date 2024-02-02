import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ZackException, IOException;

    public boolean isExit() {
        return isExit;
    }
}
