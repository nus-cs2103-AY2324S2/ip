package actions;

import java.io.IOException;

import exceptions.ExcessiveArgException;
import exceptions.IncompatibleMarkException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;

/**
 * To mark a task as complete
 */
public class MarkAction extends actions.GenericAction {
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
            taskList.markList(index);
            outputString += "As you wish, this task will be marked:\n";
            outputString += String.format(taskList.get(index).toString());
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format of \"mark\" is as follows:"
                    + "\nunmark (x) - x is an number corresponding with the index of an item in the list.");
        } catch (NumberFormatException error) {
            outputString += "Hmm... Seems like you want me to mark something, but you didn't provide a "
                    + "valid number for me to work off.\nSteven's Advice: Use a number instead.";
        } catch (IndexOutOfBoundsException error) {
            outputString += "Ah, a pity... Seems like you don't have that many tasks.\nSteven's advice: "
                    + "Use a number which corresponds to a task number. If you need to know what number "
                    + "corresponds to what task, use \"list\".";
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        } catch (IncompatibleMarkException e) {
            outputString += "Wait a moment, this one's already complete! I can't mark it as such again!."
                    + "\nSteven's advice: You might have wanted to use\"unmark\" instead!";
        }
        return outputString;
    }
}
