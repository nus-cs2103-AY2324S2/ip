package commands;

import exception.InvalidCommandException;
import exception.InvalidIndexException;
import objects.Task;
import objects.TaskList;
import view.EncaseLines;

public class UnmarkTask implements Command {
    private final TaskList tasks;
    private final int index;

    public UnmarkTask(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() throws InvalidIndexException, InvalidCommandException {
        if (this.index == -1) {
            throw new InvalidCommandException();

        } else if (this.index < 0 || this.index > tasks.size() - 1) {
            throw new InvalidIndexException();
        }

        tasks.unmarkTask(this.index);
        Task t = tasks.get(this.index);

        String o = String.format("Nice! I've marked this task as not done yet: \n   %s",  t.toString());

        EncaseLines.display(o);
    }
}
