package actions;

import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * an action which displays the list of commands for Steven
 */
public class HelpAction extends actions.GenericAction {
    private static final String HELP1 = "The following are User Inputs that I recgonise, "
            + "and their respective formats:\n";
    private static final String HELP2 = "bye - terminates the program\n";
    private static final String HELP3 = "list - provides a list of events that you have added prior\n";
    private static final String HELP4 = "mark (x) - marks the xth item on the list as complete. Note that an "
            + "item marked"
            + "complete cannot be marked complete again.\n";
    private static final String HELP5 = "unmark (x) - marks the xth item on the list as incomplete. Note than an"
            + "item marked "
            + "incomplete cannot be marked incomplete again.\n";
    private static final String HELP6 = "todo (item) - adds a todo item to the list.\n";
    private static final String HELP7 = "deadline (item) /by (date1) - adds a deadline item to the list"
            + "which is due on "
            + "date1. The format for both dates must be \"yyyy-mm-dddd\".\n";
    private static final String HELP8 = "event (item) /from (date1) /to (date2) - adds an event item "
            + "to the list which begins "
            + "on date1 and ends on date2. The format for both dates must be \"yyyy-mm-dddd\".\n";
    private static final String HELP9 = "delete (x) - delete the xth item from the list. Do note "
            + "that this may affect the "
            + "positioning of some of the items.\n";
    private static final String HELP10 = "find (item) - finds any items in the list and prints them out, giving "
            + "their indexes.\n";
    private static final String HELP11 = "sort (asc/des) - sorts the user's task list in ascending or descending order"
            + " respectively, where \"asc\" or \"des\" is given as the second parameter.";
    /**
     * executes the given task
     * @param userInput What the user inputted
     * @param taskList the current task list
     * @return the string containing the javafxFiles.Steven's response to the task.
     */
    public String execute(UserInput userInput, TaskList taskList) {
        return String.format(HELP1 + HELP2 + HELP3 + HELP4 + HELP5 + HELP6 + HELP7 + HELP8 + HELP9 + HELP10 + HELP11);
    }
}
