package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Task;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand class defines a command to mark tasks stored in the Duke
 * application as completed
 *
 * @author Ryan NgWH
 */
public class MarkCommand extends Command {
    /**
     * Index of task to mark
     */
    private int index;

    /**
     * Creates a mark command
     *
     * @param index Index of task to mark
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the mark command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        // Mark task as completed
        Task markedTask = taskList.markTask(this.index);

        // Print success message
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format(" %s", markedTask.toString()));
    }

}
