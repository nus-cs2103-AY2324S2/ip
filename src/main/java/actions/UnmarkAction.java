package actions;

import java.io.IOException;

import exceptions.ExcessiveArgException;
import exceptions.IncompatibleMarkException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * an action which unmarks an item in the list
 */
public class UnmarkAction extends actions.GenericAction {
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
            int index = Integer.parseInt(userInput.getArg1()) - 1;
            taskList.unmarkList(index);
            outputString += "I see. In that case, the following task will be unmarked:\n";
            outputString += String.format(taskList.get(index).toString());
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format of \"unmark\" is as follows:"
                    + "\nmark (x) - x is an number corresponding with the index of an item in the list.");
        } catch (NumberFormatException error) {
            outputString += "Hmm... Seems like you want me to unmark something, but you didn't provide a valid "
                    + "number for me to work off.\nSteven's Advice: Use a number instead.";
        } catch (IndexOutOfBoundsException error) {
            outputString += "Ah, a pity... Seems like you don't have that many tasks.\nSteven's advice: "
                    + "Use a number which corresponds to a task number. If you need to know what number corresponds"
                    + " to what task, use \"list\".";
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        } catch (IncompatibleMarkException e) {
            outputString += "Ah, hold on. Seems like this one's still incomplete. I can't unmark this."
                    + "\nSteven's advice: You might have wanted to use\"mark\" instead!";
        }
        return outputString;
    }
}
