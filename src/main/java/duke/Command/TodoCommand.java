package duke.Command;

import duke.*;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Tasks.TodoTask;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new TodoTask(description);
        tasks.addTask(task);
        int count = tasks.size();
        ui.showAddedMessage(task, count);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

