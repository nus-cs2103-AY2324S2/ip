package bob;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws SavingException {
        Task task = taskList.addTodo(description);
        ui.showAdd(task, taskList.getSize());
        storage.saveTask(task);
    }
}
