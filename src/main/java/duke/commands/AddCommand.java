package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 *  The Find class handles search requests.
 */
public class AddCommand extends Command {
    private TaskList userTasks;
    private boolean isAdded;

    /**
     * Constructs a Find that handles search requests.
     * @param cmd The user input split by command and description.
     * @param userTasks The current task list of user tasks.
     */
    public AddCommand(String[] cmd, TaskList userTasks) {
        super(cmd);
        this.userTasks = userTasks;
    }

    @Override
    public String execute() {
        super.validateArgs();

        String taskType = cmd[0];
        if (taskType.equals("deadline")) {
            String[] task = cmd[1].split(" /by ");
            Deadline newDL = new Deadline(task[0], task[1]);
            userTasks.addTask(newDL);
            isAdded = true;
        } else if (taskType.equals("event")) {
            String[] task = cmd[1].split(" /from ", 2);
            String[] period = task[1].split(" /to ", 2);
            Event newEvt = new Event(task[0], period[0], period[1]);
            userTasks.addTask(newEvt);
            isAdded = true;
        } else if (taskType.equals("todo")) {
            Todo newTd = new Todo(cmd[1]);
            userTasks.addTask(newTd);
            isAdded = true;
        }

        if (isAdded) {
            int numTasks = userTasks.getSize();
            Task addedTask = userTasks.getTask(numTasks - 1);
            String response = "    Got it. I've added this task:\n"
                    + "        " + addedTask + "\n"
                    + "    Now you have " + numTasks + " tasks in the list.";
            return response;
        } else {
            throw new DukeException("Task is not added x.x");
        }
    }
}
