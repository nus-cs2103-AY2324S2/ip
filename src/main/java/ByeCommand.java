import java.io.IOException;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasksFileException {
        try {
            ui.close();
            storage.saveTasksFile(taskList);
            ui.goodbye();
        } catch (IOException e) {
            throw new TasksFileException();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
