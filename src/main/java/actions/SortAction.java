package actions;

import java.io.IOException;
import java.util.Objects;

import exceptions.ExcessiveArgException;
import exceptions.IncorrectFieldException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * an action which sorts the tasks in the list according to the specified criteria
 */
public class SortAction extends actions.GenericAction {
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
            if (!userInput.arg3Empty() || !userInput.arg2Empty()) {
                throw new ExcessiveArgException();
            }
            assert !userInput.arg1Empty();
            if (Objects.equals(userInput.getArg1(), "asc")) {
                taskList.sortTasks();
            } else if (Objects.equals(userInput.getArg1(), "des")) {
                taskList.sortTasksInverse();;
            } else {
                throw new IncorrectFieldException();
            }
            outputString += "Certainly. I've sorted your list of tasks accordingly. Use \"list\" to verify that it"
                    + " is to your liking!";
        } catch (ExcessiveArgException | IncorrectFieldException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format "
                    + "of \"sort\" is as follows:\nsort (asc/des) "
                    + "- you input \"asc\" or \"des\" depending on if you want to sort the list in ascending or"
                    + " descending order respectively!");
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        } catch (InsufficientArgException error) {
            outputString += "Oh my! Seems like you forgot to tell me how you want me to sort the list of tasks that"
                    + "you have!\nSteven's advice: use \"asc\" if you want to sor the list in ascending order, or"
                    + " \"des\" if you want to sort the list in a descending order instead!";
        }
        return outputString;
    }
}
