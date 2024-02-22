package actions;

import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * Generic action that other tasks will inherit from
 */
public abstract class GenericAction {
    protected static final String CORRUPTED = "Oh dear, looks like the file used to handle the data "
            + "I'm supposed to store is corrupted..."
            + "\nSteven's Advice: You may need to re-create the file!";
    protected static final String FORMAT_ERROR = "My, it would appear as though you didn't"
            + " format your instruction properly!\n";
    protected static final String DATE_ERR = "Ah, this one might be slightly complicated - "
            + "I need your date in the format of \"yyyy-mm-dd\", "
            + "and I'm quite strict with this, unfortunately."
            + "\nSteven's advice: Follow the format, append your days/months with zero as necessary! "
            + "For example, \"03\" is accepted for the month of March, but not \"3\"!";
    protected String outputString = "";

    protected GenericAction() {

    }
    /**
     * executes the given task, modifying the tasklist accordingly
     * @param userInput What the user inputted
     * @param taskList the current task list
     * @return the string containing the Steven's response to the task.
     */
    public abstract String execute(UserInput userInput, TaskList taskList);
}
