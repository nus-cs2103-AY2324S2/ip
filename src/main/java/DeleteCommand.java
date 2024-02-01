import java.io.IOException;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        try {
            Task deletedTask = tasks.remove(index);
            ui.delete(deletedTask, tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
