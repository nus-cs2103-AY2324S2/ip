package actions;

import exceptionhandling.DukeException;
import ui.Duke;

/**
 * The `Unmark` class implements the Action interface and represents an
 * action to unmark a task (mark it as undone) in the task list.
 * It stores the index of the task to be unmarked and provides a method to execute the action.
 */
public class Unmark implements Action {

    private int index;

    /**
     * Constructs an `Unmark` object with the specified index of the task to be unmarked.
     *
     * @param command     The command string.
     */
    public Unmark(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new DukeException("Please include a task index to mark");
        }
        try {
            String index = splitCommand[1];
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number as your index");
        }
    }

    /**
     * Executes the unmark action by unmarking the task at the stored index
     * (marking it as undone) in the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the unmark action.
     */
    @Override
    public String execute(Duke bot) {
        assert(bot != null);
        assert(bot.getTaskList() != null);
        return bot.getTaskList().unmarkTask(index);
    }
}

