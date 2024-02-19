package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>HelpCommand</code> object represents the command to access help page.
 */
public class HelpCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "*** Welcome to the Help Page ***\n\n"
            + "Jade is designed to help you manage your tasks efficiently. "
            + "Below is a list of available commands along with their usage instructions:\n\n"
            + "1. list {date} (optional)\n"
            + "   - Lists all tasks scheduled for the specified date. If no date is provided, it lists all tasks.\n"
            + "   - Example: list or list 2024-01-01\n\n"
            + "2. find {keyword}\n"
            + "   - Finds tasks containing the specified keyword in their descriptions.\n"
            + "   - Example: find meeting\n\n"
            + "3. todo {description}\n"
            + "   - Adds a new to-do task with the given description.\n"
            + "   - Example: todo Complete report\n\n"
            + "4. deadline {description} /by {dateTime}\n"
            + "   - Adds a new task with a deadline and description.\n"
            + "   - Example: deadline Submit proposal /by 2024-01-01 12:00 pm\n\n"
            + "5. event {description} /from {startDateTime} /to {endDateTime}\n"
            + "   - Adds a new event with a description and time frame.\n"
            + "   - Example: event Team meeting /from 2024-01-01 02:00 pm /to 2024-02-01 04:00 pm\n\n"
            + "6. recur {description} /dfrom {startDate} /dto {endDate} "
            + "/tfrom {startTime} /tto {endTime} /freq {TaskFreq}"
            + "   - Adds a new recurring task with a description, date frame, time frame, and frequency.\n"
            + "   - The TaskFreq enumeration has values 'Daily', 'Weekly', 'Monthly.\n"
            + "   - Example: recur discussion /dfrom 2024-01-01 /dto 2024-02-01 "
            + "/tfrom 02:00 pm /tto 04:00 pm /freq Weekly\n\n"
            + "7. delete {index}\n"
            + "   - Deletes the task with the specified index from the list.\n"
            + "   - Example: delete 3\n\n"
            + "8. mark {index}\n"
            + "   - Marks the task with the specified index as completed.\n"
            + "   - Example: mark 2\n\n"
            + "9. unmark {index}\n"
            + "   - Unmarks the task with the specified index as incomplete.\n"
            + "   - Example: unmark 1\n\n"
            + "Remember to replace {date}, {keyword}, {description}, {dateTime}, {startDateTime}, "
            + "{endDateTime}, and {index} with appropriate values when using these commands.";

    /**
     * {@inheritDoc}
     * Prints an add message after the task is added.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws JadeException {
        return RESULT_MSG_FORMATTED;
    }

    /**
     * {@inheritDoc}
     * Indicates that the program is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
