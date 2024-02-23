package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.ListFullException;
import badgpt.exceptions.WrongFormatException;
import badgpt.tasks.Deadline;
import badgpt.util.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The command which creates a new deadline task and adds it to the task list.
 */
public class CreateDeadline extends Command {

    /**
     * Creates a new deadline task and adds it to the task list.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The task description and information.
     * @throws ListFullException If the task list is already full.
     * @throws WrongFormatException If the argument entered does not follow the expected format.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws ListFullException, WrongFormatException {
        rightUsage = "deadline taskDescription /by YYYY-MM-DD\n";
        example = "deadline return book /by 2024-01-31";

        int by = args.indexOf("/by");
        if (by == -1) {
            throw new WrongFormatException("No deadline is specified.", this);
        }

        try {
            taskList.store(new Deadline(args.substring(0, by).trim(), args.substring(by + 3).trim()));
        } catch (DateTimeParseException e) {
            throw new WrongFormatException(e.getMessage(), this);
        }
    }
}
