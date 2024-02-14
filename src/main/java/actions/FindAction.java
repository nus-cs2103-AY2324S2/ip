package actions;

import exceptions.ExcessiveArgException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * an action which finds tasks with matching names in the list
 */
public class FindAction extends actions.GenericAction {
    /**
     * executes the given task, modifying the tasklist accordingly
     * @param userInput What the user inputted
     * @param taskList the current task list
     * @return the string containing the Steven's response to the task.
     */
    public String execute(UserInput userInput, TaskList taskList) {
        try {
            if (userInput.arg1Empty()) {
                throw new InsufficientArgException();
            }
            if (!userInput.arg2Empty() || !userInput.arg3Empty()) {
                throw new ExcessiveArgException();
            }
            String matchingList = taskList.findTasks(userInput.getArg1());
            outputString += String.format("Ah, so you're looking for tasks with the word %s? Sure! "
                    + "here they are!%n\n", userInput.getArg1());
            outputString += String.format(matchingList);
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format of \"find\" is as follows:\n"
                    + "find (item) - item is the name of the task, or part of the task");
        }
        return outputString;
    }
}
