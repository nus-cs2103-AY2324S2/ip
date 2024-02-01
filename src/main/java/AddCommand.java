public class AddCommand extends Command{
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.addTask(task, tasks.size());
        try {
            storage.save(tasks);
        } catch(Exception e) {}
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
