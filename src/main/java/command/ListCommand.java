package command;

import task.Task;
import task.TaskList;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i);
            System.out.println(i + ". " + t.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
