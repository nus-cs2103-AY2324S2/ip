package pyrite.command;

import pyrite.StateFile;
import pyrite.task.Task;
import pyrite.TaskList;

public class AddCommand extends Command{
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public String execute(TaskList tasks, StateFile file) {
        tasks.add(this.task);
        String saveResult = file.saveState(tasks);
        return "Got it. I've added this task:"
                + "\n\t" + this.task.toString()
                + "\n" + "Now you have " + tasks.size() + " tasks in the list."
                + saveResult;
    }
}
