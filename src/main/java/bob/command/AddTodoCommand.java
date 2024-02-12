package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.SavingException;
import bob.task.Task;

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
