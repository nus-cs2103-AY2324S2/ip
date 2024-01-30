package commands;

import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;
import view.EncaseLines;

public class DeleteTask implements Command {
    private final TaskList tasks;
    private final int index;

    public DeleteTask(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() throws InvalidIndexException {
        if (this.index < 0 || this.index >= tasks.size()) {
            throw new InvalidIndexException();
        }

        Task task = tasks.get(this.index);

        tasks.remove(this.index);
        String o = String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.", task.toString(), tasks.size());
        EncaseLines.display(o);
    }
}
