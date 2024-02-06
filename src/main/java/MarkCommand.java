public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (tasks.size() <= index) {
            throw new DukeException("There is nothing to be marked");
        } else {
            Task task = tasks.get(index);
            task.markDone();
            storage.save(tasks);
            ui.showMarked(task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
