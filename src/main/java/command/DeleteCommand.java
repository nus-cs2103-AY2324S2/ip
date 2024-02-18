package command;

import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    public DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Task t = taskList.remove(taskIndex);
        
        System.out.println("Noted, I've removed this task:");
        System.out.println(" " + t.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
