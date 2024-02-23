package commands;

import exceptions.WeiException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

/**
 * Marks the task as complete.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Creates a MarkCommand object.
     *
     * @param input the order of the task to be marked.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the task in the TaskList and inform the user.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     * @throws WeiException If the command is incomplete.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(5)) - 1;
            String markedTask = tasks.mark(order);
            return ui.showMarkedMessage(markedTask);
        } catch (NumberFormatException e) {
            throw new WeiException("which task do you want to mark?");
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
