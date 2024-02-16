package hanxiao.command;

import hanxiao.TaskList;
import hanxiao.task.Task;

/**
 * Class for listing all the tasks
 */
public class List implements Command {
    private TaskList taskList;
    public List(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Override the reply method in interface
     * iterate through the task list and print out everything.
     */
    @Override
    public String reply() {
        String reply = "Here are the tasks in your list:\n";
        int i = 0;
        for (Task task : taskList.getTaskList()) {
            reply += String.format("  %s.%s\n", ++i, task);
        }
        return reply;
    }
}
