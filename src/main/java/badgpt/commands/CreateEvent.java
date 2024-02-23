package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.ListFullException;
import badgpt.exceptions.WrongFormatException;
import badgpt.tasks.Event;
import badgpt.util.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The command which creates a new event task and adds it to the task list.
 */
public class CreateEvent extends Command {

    /**
     * Creates a new event task and adds it to the task list.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The task description and information.
     * @throws ListFullException If the task list is already full.
     * @throws WrongFormatException If the argument entered does not follow the expected format.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws ListFullException, WrongFormatException {
        rightUsage = "event taskDescription /from YYYY-MM-DD /to YYYY-MM-DD\n";
        example = "event holiday /from 2024-01-31 /to 2024-02-07";

        int fromIdx = args.indexOf("/from");
        int toIdx = args.indexOf("/to");
        if (fromIdx == -1 || toIdx == -1) {
            throw new WrongFormatException("No duration is specified.", this);
        }

        try {
            taskList.store(new Event(args.substring(0, fromIdx).trim(),
                    args.substring(fromIdx + 5, toIdx).trim(), args.substring(toIdx + 3).trim()));
        } catch (DateTimeParseException e) {
            throw new WrongFormatException(e.getMessage(), this);
        }
    }
}
