import java.io.IOException;

public class DeleteCommand extends Command {
    private int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToRemove = tasks.deleteTask(indexToDelete);
            ui.showDeletedTask(taskToRemove, tasks.getSize());
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
