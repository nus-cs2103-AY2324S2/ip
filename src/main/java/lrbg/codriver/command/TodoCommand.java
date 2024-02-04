package lrbg.codriver.command;

import lrbg.codriver.data.exception.CoDriverException;
import lrbg.codriver.data.Task;
import lrbg.codriver.data.TaskList;
import lrbg.codriver.data.Todo;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws CoDriverException {
        Task newTask = new Todo(this.description);
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.size());
    }

    public boolean testEquals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof TodoCommand) {
            TodoCommand other = (TodoCommand) obj;
            return other.description.equals(this.description);
        } else {
            return false;
        }
    }
}
