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
     */
    @Override
    public void execute(Duke bot) {
        bot.getTaskList().displayList();
    }
}

