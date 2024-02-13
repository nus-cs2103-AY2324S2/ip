package actions;

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
     * @param index The index of the task to be marked as done in the task list.
     */
    public Mark(int index) {
        this.index = index;
    }

    /**
     * Executes the mark action by marking the task at the stored index as done in the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the mark action.
     */
    @Override
    public String execute(Duke bot) {
        return bot.getTaskList().markTask(index);
    }
}

