package mike.command;

import mike.MikeException;
import mike.Storage;
import mike.TaskList;
import mike.task.Deadline;
import mike.task.Task;

/**
 * Adds deadline to the task list.
 * @author ningc
 */
public class AddDeadlineCommand extends AddCommand {
    private final String deadline;

    /**
     * Constructor.
     * @param description What the task is.
     * @param deadline When the task is due.
     */
    public AddDeadlineCommand(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        Task newTask = new Deadline(description, deadline);
        taskList.add(newTask);
        return response(taskList, newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "ADD DEADLINE " + description + " " + deadline;
    }
}
