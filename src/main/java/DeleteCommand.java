public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.remove(index);
        ui.delete(deletedTask, tasks.size());
        try {
            storage.save(tasks);
        } catch (Exception e) {}
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
