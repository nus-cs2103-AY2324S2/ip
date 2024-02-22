package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.task.Task;

/**
 *  The Find class handles search requests.
 */
public class MarkCommand extends Command {
    private TaskList userTasks;

    /**
     * Constructs a Find that handles search requests.
     * @param cmd The user input split by command and description.
     * @param userTasks The current task list of user tasks.
     */
    public MarkCommand(String[] cmd, TaskList userTasks) {
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
        Task currTask = userTasks.getTask(taskIdx);
        if (cmd[0].equals("mark")) {
            currTask.markAsDone();
            String response = "    Nice! I have marked this task as done: \n"
                    + "        " + currTask;
            return response;
        } else if (cmd[0].equals("unmark")) {
            currTask.markAsUndone();
            String response = "    Ok, I've marked this task as not done yet: \n"
                    + "        " + currTask;
            return response;
        } else {
            throw new DukeException("unknown command containing 'mark'. Perhaps try another keyword?");
        }
    }
}
