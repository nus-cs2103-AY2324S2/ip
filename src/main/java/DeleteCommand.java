public class DeleteCommand extends Command{

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Index out of bound handler
        if (index >= tasks.getItems().size()) {
            throw new DukeException("The index of task cannot be larger than number of task.");
        } else if (index < 0) {
            throw new DukeException("The index of task must be positive integer.");
        }

        ui.delete(tasks.getItem(index), tasks);
        tasks.delete(index);
        storage.saveChanges(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
