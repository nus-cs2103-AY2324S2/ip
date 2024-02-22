package actions;

import java.io.IOException;

import exceptions.ExcessiveArgException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;
import tasks.Task;

/**
 * an action which removes the index of a particular item from the list
 */
public class DeleteAction extends actions.GenericAction {
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
            int index = Integer.parseInt(userInput.getArg1());
            assert index - 1 < taskList.size();
            Task removedItem = taskList.get(index - 1);
            taskList.removeFromList(index - 1);
            outputString += String.format("Very well, the following item has been removed from the"
                    + "list:\n" + removedItem.toString() + "\n");
            outputString += "For the sake of completeness, this is the current list, do take note if any of "
                    + "your items have been moved around in order.\n";
            outputString += taskList.printListString();
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format of \"Delete\" is as follows:\n"
                    + "delete (x) - x is an number corresponding with the index of an item in the list.");
        } catch (NumberFormatException error) {
            outputString += "Oh, I can't  delete that - I need a number of an item in the list to delete it."
                    + "\nSteven's Advice: Use a number instead.";
        } catch (IndexOutOfBoundsException error) {
            outputString += "Apologies, you don't have a task of this number, so I can't delete it."
                    + "\nSteven's advice: Use a number which corresponds to a task number. If you need to know"
                    + "what number corresponds to what task, use \"list\".";
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        }
        return outputString;
    }
}
