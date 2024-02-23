package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.ListEmptyException;
import badgpt.exceptions.WrongFormatException;
import badgpt.util.TaskList;

/**
 * The command which finds any tasks containing the specified keyword.
 */
public class Find extends Command {

    /**
     * Finds any tasks containing the specified keyword.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The term of interest to filter out tasks.
     * @throws ListEmptyException If there are no tasks containing the keyword.
     * @throws WrongFormatException If the argument entered does not follow the expected format.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws ListEmptyException, WrongFormatException {
        if (args.isEmpty()) {
            rightUsage = "find keyword\n";
            example = "find book";
            throw new WrongFormatException("No keyword is specified.", this);
        } else {
            taskList.find(args);
        }
    }
}
