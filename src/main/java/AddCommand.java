public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        ui.printAddTask(this.task, taskList.getSize());
    }
}
