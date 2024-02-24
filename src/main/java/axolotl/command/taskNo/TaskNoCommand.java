package axolotl.command.taskNo;

import axolotl.command.Command;
import axolotl.command.InvalidCommand;
import axolotl.task.Task;

public abstract class TaskNoCommand extends Command {
    protected static int taskNo;
    protected Task t;

    public static final String COMMAND_TASK_NOT_EXIST = "-------------------------------- \n" +
            "Oops, task %d does not exist. Please try again! \n" +
            "Here is your list for reference: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public TaskNoCommand(int taskNo) {
        this.taskNo = taskNo;
        this.t = storage.getTaskFromTaskNo(taskNo - 1);
    }

    public static InvalidCommand errorTaskNotExist() {
        return new InvalidCommand(String.format(COMMAND_TASK_NOT_EXIST, taskNo, storage.getTasksInString()));
    }

}
