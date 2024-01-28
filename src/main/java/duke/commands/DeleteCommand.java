package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Task;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class defines a command to delete tasks stored in the Duke
 * application
 *
 * @author Ryan NgWH
 */
public class DeleteCommand extends Command {
    /**
     * Index of task to delete
     */
    private int index;

    /**
     * Creates a delete command
     *
     * @param index Index of task to mark
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the delete command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        // Delete the task
        Task deletedTask = taskList.deleteTask(this.index);

        // Print success message
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format(" %s", deletedTask.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
