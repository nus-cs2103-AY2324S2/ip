
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("OOPS!!! Task index is out of range.");
        }

        Task task = tasks.get(index - 1);
        task.markDone();
        ui.showMarkedMessage(task);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
