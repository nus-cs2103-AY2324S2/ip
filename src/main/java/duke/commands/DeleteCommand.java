package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

/**
 *  The Find class handles search requests.
 */
public class DeleteCommand extends Command {
    private TaskList userTasks;

    /**
     * Constructs a Find that handles search requests.
     * @param cmd The user input split by command and description.
     * @param userTasks The current task list of user tasks.
     */
    public DeleteCommand(String[] cmd, TaskList userTasks) {
        super(cmd);
        this.userTasks = userTasks;
    }
    @Override
    public String execute() {
        super.validateArgs();

        int task = Integer.parseInt(cmd[1]);
        int numTasks = userTasks.getSize();
        if (task > numTasks) {
            throw new DukeException("you have " + numTasks
                    + " tasks. Please fill in a number lower than or equal to it!"
            );
        }

        int taskIdx = task - 1;
        Task delTask = userTasks.rmvTask(taskIdx);
        String response = "    Noted. I've removed this task:\n"
                + "        " + delTask + "\n"
                + "    Now you have " + (numTasks - 1) + " tasks in the list.";
        return response;
    }
}
