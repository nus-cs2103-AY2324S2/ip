package command;

import emis.TaskList;
import emis.Storage;

/**
 * The MarkCommand class represents a command to mark a task as done in the EMIS application.
 * When executed, it marks the specified task as done and updates the storage.
 */
public class MarkCommand extends Command {
    /** The index of the task to mark as done. */
    private int taskNo;
    private Integer[] tasksToMark;

    /**
     * Constructs a new MarkCommand object with the specified task index.
     *
     * @param taskNo The index of the task to mark as done.
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    public MarkCommand(Integer[] tasksToMark) {
        this.tasksToMark = tasksToMark;
    }

    /**
     * Executes the mark command by marking the specified task as done and updating the storage.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param storage The Storage object handling loading and saving of tasks.
     * @return A string indicating the result of the command execution.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        try {
            String response = tasklist.markAsDone(this.taskNo);
            storage.updateStorage();
            return response;
        } catch (AssertionError e) {
            return e.getMessage();
        }
    }
}
