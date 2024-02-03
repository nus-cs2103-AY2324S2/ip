package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.task.Todo;
import eggy.ui.Ui;

public class TodoCommand extends Command {
    private String name;

    public TodoCommand(String[] commands) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("todo");
        }
        this.name = commands[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task newTodo = new Todo(this.name);
        tasks.addTask(newTodo);
        ui.printTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
    }
}
