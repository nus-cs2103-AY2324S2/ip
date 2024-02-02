public class AddToDoCommand extends AddCommand {

    public AddToDoCommand(String taskName) {
        super("todo", taskName);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        Task newTask = new ToDoTask(super.taskName);
        tasks.addTask(newTask);
        ui.taskAdded(newTask, tasks);
        storage.storeTask(newTask, tasks);
    }

}
