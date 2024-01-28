package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Task;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand class defines a command to mark tasks stored in the Duke
 * application as completed/uncompleted
 *
 * @author Ryan NgWH
 */
public class MarkCommand extends Command {
    /**
     * Index of task to mark
     */
    private int index;

    /**
     * Status of task
     */
    private boolean isCompleted;

    /**
     * Creates a mark command
     *
     * @param index       Index of task to mark
     * @param isCompleted Status of the task
     */
    public MarkCommand(int index, boolean isCompleted) {
        super(false);
        this.index = index;
        this.isCompleted = isCompleted;
    }

    /**
     * Executes the mark command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        // Mark task as completed/uncomplete
        Task markedTask = taskList.markTask(this.index, this.isCompleted);

        // Print success message
        if (isCompleted) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("Nice! I've marked this task as not done:");
        }
        System.out.println(String.format(" %s", markedTask.toString()));
    }
}
