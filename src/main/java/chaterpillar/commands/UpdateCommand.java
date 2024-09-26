package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to update a task from the list of tasks.
 *
 * @author marclamp
 */
public class UpdateCommand extends Command {
    private final int index;
    private final String updatedName;
    private final String updatedDate;
    private final String updatedStartDate;
    private final String updatedEndDate;

    /**
     * Basic constructor to create the object for this class.
     *
     * @param index of the <code>Task</code> in the list to be updated
     * @param updatedName the updated name of the task
     * @param updatedDate the updated date of the task
     * @param updatedStartDate the updated start date of the task
     * @param updatedEndDate the updated end date of the task
     */
    public UpdateCommand(int index, String updatedName, String updatedDate,
                         String updatedStartDate, String updatedEndDate) {
        this.index = index;
        this.updatedName = updatedName;
        this.updatedDate = updatedDate;
        this.updatedStartDate = updatedStartDate;
        this.updatedEndDate = updatedEndDate;
        assert index > 0;
    }

    /**
     * Updates the details of the <code>Task</code> accordingly.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return reply from the ChatBot.
     * @throws ChaterpillarException if there are any mismatch in the detail.
     *     to be updated and the type of <code>Task</code>.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        Task task = tasks.get(index);

        task.updateName(updatedName);
        task.updateDate(updatedDate);
        task.updateStartDate(updatedStartDate);
        task.updateEndDate(updatedEndDate);
        storage.saveAllToFile(tasks);

        String output =
                "Okay! I have updated the following task: \n"
                + (index + 1) + ". " + task;

        ui.echo(output);
        return output;
    }
}
