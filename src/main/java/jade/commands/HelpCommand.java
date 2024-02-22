package jade.commands;

import jade.data.TaskList;
import jade.exception.JadeException;
import jade.storage.Storage;

/**
 * The <code>HelpCommand</code> object represents the command to access help page.
 */
public class HelpCommand extends Command {
    private static final String RESULT_MSG_FORMATTED = "*** Welcome to the Help Page ***\n"
            + "Jade is designed to help you manage your tasks efficiently. "
            + "Below is a list of available commands along with their usage instructions:\n"
            + "1. list [DATE]\n"
            + "   - Lists all tasks scheduled for the specified date. If no date is provided, it lists all tasks.\n"
            + "   - Example: list or list 2024-01-01\n"
            + "2. find KEYWORD(S)\n"
            + "   - Finds tasks containing the specified keyword in their descriptions.\n"
            + "   - Example: find team meeting\n"
            + "3. todo DESCRIPTION\n"
            + "   - Adds a new to-do task with the given description.\n"
            + "   - Example: todo read a book\n"
            + "4. deadline DESCRIPTION /by DEADLINEDATETIME\n"
            + "   - Adds a new task with a deadline and description.\n"
            + "   - Example: deadline Submit proposal /by 2024-01-01 12:00 pm\n"
            + "5. event DESCRIPTION /from STARTDATETIME /to ENDDATETIME\n"
            + "   - Adds a new event with a description and time frame.\n"
            + "   - Example: event Team meeting /from 2024-01-01 02:00 pm /to 2024-02-01 04:00 pm\n"
            + "6. recur DESCRIPTION /dfrom STARTDATE /dto ENDDATE "
            + "/tfrom STARTTIME /tto ENDTIME /freq TASKFREQ\n"
            + "   - Adds a new recurring task with a description, date frame, time frame, and frequency.\n"
            + "   - The TaskFreq enumeration has values 'Daily', 'Weekly', 'Monthly'.\n"
            + "   - Example: recur consultation /dfrom 2024-01-01 /dto 2024-02-01 "
            + "/tfrom 02:00 pm /tto 04:00 pm /freq Weekly\n"
            + "7. delete INDEX\n"
            + "   - Deletes the task with the specified index from the list.\n"
            + "   - Example: delete 1\n"
            + "8. mark INDEX\n"
            + "   - Marks the task with the specified index as completed.\n"
            + "   - Example: mark 1\n"
            + "9. unmark INDEX\n"
            + "   - Marks the task with the specified index as uncompleted.\n"
            + "   - Example: unmark 1\n"
            + "10. bye\n"
            + "   - Exits the program\n"
            + "Remember to replace UPPER_CASE parameter with appropriate values, "
            + "also note that parameters inside [] are optional.";

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
