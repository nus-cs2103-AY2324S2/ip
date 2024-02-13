package actions;

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
     * @param index The index of the task to be unmarked in the task list.
     */
    public Unmark(int index) {
        this.index = index;
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
        return bot.getTaskList().unmarkTask(index);
    }
}

