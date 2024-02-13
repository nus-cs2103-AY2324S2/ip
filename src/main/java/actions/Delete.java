package actions;

import ui.Duke;

/**
 * The `Delete` class implements the Action interface and represents an action to delete a task from the task list.
 * It stores the index of the task to be deleted and provides a method to execute the action.
 */
public class Delete implements Action {

    private int index;

    /**
     * Constructs a `Delete` object with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Executes the delete action by removing the task at the stored index from the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the delete action.
     */
    @Override
    public String execute(Duke bot) {
        return bot.getTaskList().deleteTask(index);
    }
}
