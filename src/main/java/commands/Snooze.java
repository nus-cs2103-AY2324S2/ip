package commands;

import exception.SnoozeException;
import objects.Task;
import objects.ToDo;
import objects.TaskList;
import view.SnoozedTask;

public class Snooze implements Command{
    private final TaskList tasks;
    private final int index;

    public Snooze(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public String execute() throws SnoozeException {
        Task task = tasks.get(index);

        if (task instanceof ToDo) {
            throw new SnoozeException();
        } else {
            task.snooze();
        }

        return SnoozedTask.display(task);
    }
}
