package command;

import task.Task;
import task.TaskList;

public class MarkCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    public MarkCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Task t = taskList.get(taskIndex);
        t.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
