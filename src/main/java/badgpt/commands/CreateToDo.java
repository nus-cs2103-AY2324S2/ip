package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.ListFullException;
import badgpt.exceptions.WrongFormatException;
import badgpt.tasks.ToDo;
import badgpt.util.TaskList;

/**
 * The command which creates a new todo task and adds it to the task list.
 */
public class CreateToDo extends Command {

    /**
     * Creates a new todo task and adds it to the task list.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The task description and information.
     * @throws ListFullException If the task list is already full.
     * @throws WrongFormatException If the argument entered does not follow the expected format.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws ListFullException, WrongFormatException {
        if (args.isEmpty()) {
            rightUsage = "todo taskDescription\n";
            example = "todo read book\n" +
                    "are you satisfied with that, todo aoi";
            throw new WrongFormatException("Description is empty.", this);
        } else {
            taskList.store(new ToDo(args));
        }
    }
}
