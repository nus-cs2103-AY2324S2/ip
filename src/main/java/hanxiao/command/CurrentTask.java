package hanxiao.command;

import java.time.LocalDate;

import hanxiao.TaskList;
import hanxiao.task.Task;

/**
 * Class for listing all incomplete task within the deadline.
 */
public class CurrentTask implements Command {
    private LocalDate currentTime;
    private TaskList taskList;

    /**
     * Constructor for the Command.
     *
     * @param taskList take in a TaskList for reading.
     */
    public CurrentTask(TaskList taskList) {
        this.currentTime = LocalDate.now();
        this.taskList = taskList;
    }

    /**
     * Reply all the unfinished tasks.
     *
     * @return Unfinished tasks.
     */
    @Override
    public String reply() {
        String reply = "    Dear sir, here are the tasks by now:\n";
        int i = 0;
        for (Task task: taskList.getTaskList()) {
            if (task.isTimeForStart(currentTime)) {
                reply += String.format("%s.%s\n", ++i, task);
            }
        }
        return reply;
    }
}
