package command;

import task.Task;
import task.TaskList;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    public UnmarkCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Task t = taskList.get(taskIndex);
        t.unmark();
        
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
