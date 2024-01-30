public class ListCommand extends Command {
    public ListCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public void execute() {
        ui.showAllTasks(tasks.getTasks());
    }
}
