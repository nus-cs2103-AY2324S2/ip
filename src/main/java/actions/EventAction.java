package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.IllogicalDateException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;
import tasks.Event;

/**
 * an action which adds an Event item to the list
 */
public class EventAction extends actions.GenericAction {
    /**
     * executes the given task, modifying the tasklist accordingly
     * @param userInput What the user inputted
     * @param taskList the current task list
     * @return the string containing the Steven's response to the task.
     */
    public String execute(UserInput userInput, TaskList taskList) {
        try {
            if (userInput.arg1Empty() || userInput.arg2Empty() || userInput.arg3Empty()) {
                throw new InsufficientArgException();
            }
            assert !(userInput.arg1Empty() && userInput.arg2Empty() && userInput.arg3Empty());
            LocalDate start = LocalDate.parse((userInput.getArg2()));
            LocalDate end = LocalDate.parse(userInput.getArg3());
            if (end.isBefore(start)) {
                throw new IllogicalDateException();
            }
            taskList.addToList(new Event(userInput.getArg1(), start, end));
            outputString += "I see. I shall add the following to the list of tasks:\n";
            outputString += String.format(taskList.get(taskList.size() - 1) + "\n");
            System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
        } catch (InsufficientArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format "
                    + "of \"Event\" is as follows:\nEvent "
                    + "(item) /from (date1) /to (date2) - item is the name of an item that you want to add to the "
                    + "list as a deadline. date1 and date2 must be dates.");
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        } catch (DateTimeParseException error) {
            outputString += String.format(DATE_ERR);
        } catch (IllogicalDateException error) {
            outputString += "Now hold on, this doesn't make sense! How can you have an event start earlier "
                    + "than it ends?\nSteven's advice: Make sure your first date is before the second!";
        }
        return outputString;
    }
}
