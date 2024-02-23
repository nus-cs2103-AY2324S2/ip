package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.TaskNotFoundException;
import badgpt.exceptions.WrongFormatException;
import badgpt.util.TaskList;

/**
 * The command which deletes a task.
 */
public class Delete extends Command {

    /**
     * Deletes a task.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The number corresponding to the task index.
     * @throws TaskNotFoundException If the number entered does not exist in the list.
     * @throws WrongFormatException If the argument entered is not a number.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws TaskNotFoundException, WrongFormatException {
        try {
            taskList.delete(Integer.parseInt(args.split(" ")[0]) - 1);
        } catch (NumberFormatException e) {
            rightUsage = "delete taskNum\n";
            example = "delete 2";
            throw new WrongFormatException(e.getMessage(), this);
        }
    }
}
