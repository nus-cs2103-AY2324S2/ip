import java.io.IOException;
public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public void exit() {
        this.isExit = !this.isExit;
    }

    public boolean isExited() {
        return false;
    }


}

