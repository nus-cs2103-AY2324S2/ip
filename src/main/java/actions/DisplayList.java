package actions;

import ui.Duke;

/**
 * The `DisplayList` class implements the Action interface and represents an action to display the task list.
 * It provides a method to execute the action, which displays the tasks in the task list of the Duke chatbot.
 */
public class DisplayList implements Action {

    /**
     * Executes the display list action by printing the tasks in the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the display list action.
     */
    @Override
    public String execute(Duke bot) {
        return bot.getTaskList().displayList();
    }
}

