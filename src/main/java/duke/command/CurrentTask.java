package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.task.Task;

public class CurrentTask implements Command {
    private LocalDate currentTime;
    private TaskList taskList;
    public CurrentTask(TaskList taskList) {
        this.currentTime = LocalDate.now();
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        String reply = "    Dear sir, here are the tasks by now:\n";
        int i = 0;
        for (Task task: taskList.getTaskList()) {
            if (task.isTimeForStart(currentTime)) {
                reply += String.format("    %s.%s\n", ++i, task);
            }
        }
        return reply;
    }
}
