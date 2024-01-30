public class AddCommand extends Command {
    private final Task toAdd;
    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.toAdd);
        ui.showAddOutput(tasks, this.toAdd);
    }
}