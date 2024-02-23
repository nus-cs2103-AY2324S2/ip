package actions;

import exceptionhandling.DukeException;
import ui.Duke;

/**
 * The `Mark` class implements the Action interface and represents an action to mark a task as done in the task list.
 * It stores the index of the task to be marked as done and provides a method to execute the action.
 */
public class Mark implements Action {

    private int index;

    /**
     * Constructs a `Mark` object with the specified index of the task to be marked as done.
     *
     * @param command     The command string.
     */
    public Mark(String command) throws DukeException {
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
     * Executes the mark action by marking the task at the stored index as done in the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the mark action.
     */
    @Override
    public String execute(Duke bot) {
        assert(bot != null);
        assert(bot.getTaskList() != null);
        return bot.getTaskList().markTask(index);
    }
}

