package duke.command.mark;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.InvalidCommand;
import duke.task.Task;
import duke.task.TaskList;

public abstract class ChangeisDoneCommand extends Command {
    protected static int taskNo;
    protected Task t;

    public static final String COMMAND_TASK_NOT_EXIST = "-------------------------------- \n" +
            "Oops, task %d does not exist. Please try again! \n" +
            "Here is your list for reference: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public ChangeisDoneCommand(int taskNo) {
        this.taskNo = taskNo;
        this.t = storage.getTaskFromTaskNo(taskNo - 1);
    }

    public static InvalidCommand errorTaskNotExist() {
        return new InvalidCommand(String.format(COMMAND_TASK_NOT_EXIST, taskNo, storage.getTasksInString()));
    }

}
