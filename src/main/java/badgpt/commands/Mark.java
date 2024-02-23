package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.TaskException;
import badgpt.exceptions.WrongFormatException;
import badgpt.util.TaskList;

/**
 * The command which marks a task as complete.
 */
public class Mark extends Command {

    /**
     * Marks a task as complete.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The number corresponding to the task index.
     * @throws TaskException If the number entered does not exist in the list or if the task is already at that status.
     * @throws WrongFormatException If the argument entered is not a number.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws TaskException, WrongFormatException {
        try {
            taskList.mark(Integer.parseInt(args.split(" ")[0]) - 1);
        } catch (NumberFormatException e) {
            rightUsage = "mark taskNum\n";
            example = "mark 2";
            throw new WrongFormatException(e.getMessage(), this);
        }
    }
}
