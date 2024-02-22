package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.ExcessiveArgException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;
import tasks.Deadline;

/**
 * an action which adds a Deadline item to the list
 */
public class DeadlineAction extends actions.GenericAction {
    /**
     * executes the given task, modifying the tasklist accordingly
     * @param userInput What the user inputted
     * @param taskList the current task list
     * @return the string containing the Steven's response to the task.
     */
    public String execute(UserInput userInput, TaskList taskList) {
        try {
            if (userInput.arg1Empty() || userInput.arg2Empty()) {
                throw new InsufficientArgException();
            }
            if (!userInput.arg3Empty()) {
                throw new ExcessiveArgException();
            }
            assert !(userInput.arg2Empty() && userInput.arg1Empty());
            LocalDate due = LocalDate.parse(userInput.getArg2());
            taskList.addToList(new Deadline(userInput.getArg1(), due));
            outputString += "I see. I shall add the following to the list of tasks:\n";
            outputString += String.format(taskList.get(taskList.size() - 1) + "\n");
            outputString += String.format("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format of \"Deadline\" is as follows:"
                    + "\nDeadline (item) /by (date) - item is the name of an item that you want to add to the "
                    + "list as a deadline. date must be a date.");
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        } catch (DateTimeParseException error) {
            outputString += String.format(DATE_ERR);
        }
        return outputString;
    }
}
