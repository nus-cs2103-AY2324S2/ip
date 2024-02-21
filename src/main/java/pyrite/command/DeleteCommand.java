package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int id;

    /**
     * Constructs a DeleteCommand.
     *
     * @param id Index of task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        if (!tasks.isValidId(this.id)) {
            return "Task to remove does not exist.";
        }
        String taskString = tasks.toString(this.id);
        tasks.remove(this.id);
        String saveResult = file.saveState(tasks);
        return "Scratched out the task:\n"
                + "\t"
                + taskString
                + saveResult;
    }
}
