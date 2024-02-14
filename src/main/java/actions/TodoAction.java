package actions;

import java.io.IOException;

import exceptions.ExcessiveArgException;
import exceptions.InsufficientArgException;
import mainfiles.TaskList;
import mainfiles.UserInput;
import tasks.Todo;

/**
 * an action which adds a Todo item to the list
 */

public class TodoAction extends actions.GenericAction {
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
            assert !userInput.arg1Empty();
            String name = userInput.getArg1();
            taskList.addToList(new Todo(name));
            outputString += "I see. I shall add the following to the list of tasks:\n";
            outputString += String.format(taskList.get(taskList.size() - 1) + "\n");
            System.out.printf("Do bear in mind that you now have %d tasks in the list.%n", taskList.size());
        } catch (InsufficientArgException | ExcessiveArgException error) {
            outputString += String.format(FORMAT_ERROR + "Steven's advice: The format "
                    + "of \"Todo\" is as follows:\nTodo (item) "
                    + "- item is the name of an item that you want to add to the list as a todo.");
        } catch (IOException e) {
            outputString += String.format(CORRUPTED);
        }
        return outputString;
    }
}
