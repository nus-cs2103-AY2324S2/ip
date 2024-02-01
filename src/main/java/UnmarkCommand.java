import java.io.IOException;

public class UnmarkCommand extends Command {
    private int indexToUnmark;

    public UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToUnmark = tasks.getTask(indexToUnmark);
            taskToUnmark.markAsNotDone();
            ui.showUnmarkedTask(taskToUnmark);
            storage.save(tasks.getTasks()); // Handle exceptions appropriately
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The task index provided is invalid.");
        } catch (IOException e) {
            ui.showError("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
