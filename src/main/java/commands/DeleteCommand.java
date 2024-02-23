package commands;

import exceptions.WeiException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Adds a tasks from the TaskList.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Creates a DeleteCommand object.
     *
     * @param input the order of the task to be deleted.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes the task in the TaskList and inform the user.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     * @throws WeiException If the command is incomplete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            String deletedTask = tasks.delete(order);
            int size = tasks.getSize();
            return ui.showDeleteMessage(deletedTask) + ui.showNumberOfRemainingTasks(size);
        } catch (NumberFormatException e) {
            throw new WeiException("which task do you want to delete?");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
