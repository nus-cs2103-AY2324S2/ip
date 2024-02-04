package duke.command;

import duke.TaskList;
import duke.task.Task;

public class Sort implements Command{
    private TaskList taskList;
    public Sort(TaskList taskList) {
        taskList.sortTaskList();
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        String reply = "Your tasks has been sorted:\n";
        int i = 0;
        for (Task task : taskList.getTaskList()) {
            reply += String.format("  %s.%s\n", ++i, task);
        }
        return reply;
    }
}
