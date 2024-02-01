import java.io.IOException;

public abstract class Command {
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        storage.saveTasks(taskList);
    }
}
