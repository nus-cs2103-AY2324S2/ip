package commands;

import excceptions.WeiException;
import taskList.TaskList;
import ui.Ui;

/**
 * Marks the task as incomplete.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Creates a UnmarkCommand object.
     *
     * @param input the order of the task to be marked as incomplete.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the task in the TaskList as incomplete and inform the user.
     *
     * @param tasks All the tasks of the user.
     * @param ui Gives reply to the user.
     * @throws WeiException If the command is incomplete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            String unmarkedTask = tasks.unmark(order);
            ui.showUnmarkedMessage(unmarkedTask);
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