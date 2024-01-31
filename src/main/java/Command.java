import java.io.IOException;
import java.util.List;

public class Command {
    protected TaskList tasks;

    public void execute() throws DukeException, IOException {
        throw new DukeException("Invalid call of execute()");
    };

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
