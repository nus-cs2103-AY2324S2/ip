public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.showAdd(task, tasks);
        storage.saveAdd(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
