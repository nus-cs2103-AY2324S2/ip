import java.io.IOException;
import java.util.ArrayList;
public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws IOException;
    public boolean hasExited() {
        return false;
    }
}
