package ciara.commands;

import ciara.exceptions.CiaraException;
import ciara.storage.Task;
import ciara.storage.TaskList;
import ciara.ui.Ui;

/**
 * The ArchiveCommand class defines a command to archive/unarchive tasks stored
 * in the Ciara application
 *
 * @author Ryan NgWH
 */
public class ArchiveCommand extends Command {
    /**
     * Index of task to archive/unarchive
     */
    private int index;

    /**
     * Visibility to apply to the task
     */
    private boolean toArchive;

    /**
     * Creates a Archive command
     *
     * @param index     Index of task to mark
     * @param toArchive Visibility to apply to the task
     */
    public ArchiveCommand(int index, boolean toArchive) {
        super(false);
        this.index = index;
        this.toArchive = toArchive;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) throws CiaraException {
        // Archive/unarchive the task
        Task updatedTask = taskList.archiveTask(this.index, this.toArchive);

        // Print success message
        String response;
        if (toArchive) {
            response = "Nice! I've archived this task:\n";
        } else {
            response = "Nice! I've unarchived this task:\n";
        }
        return response + String.format("  %s", updatedTask.toString());
    }

    /**
     * Executes the mark command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws CiaraException {
        String successMessage = this.execute(taskList);

        // Print tasks
        System.out.println(successMessage);
    }

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ArchiveCommand) {
            ArchiveCommand command = (ArchiveCommand) obj;

            return super.equals(command)
                    && this.index == command.index
                    && this.toArchive == command.toArchive;
        }

        return false;
    }
}
