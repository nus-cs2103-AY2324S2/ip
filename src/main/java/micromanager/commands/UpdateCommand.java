package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Task;

public class UpdateCommand extends Command {
    private int index;
    private Task newTask;

    public UpdateCommand(int index, Task newTask) {
        super();
        this.index = index;
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.update(index - 1, newTask);

        return "Got it. I've updated this task:\n"
                + "  " + newTask + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskList.size());
    }
}
