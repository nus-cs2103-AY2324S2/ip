package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.ListEmptyException;
import badgpt.exceptions.WrongFormatException;
import badgpt.util.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The command which finds any tasks (specifically events and deadlines) occurring before or during the specified date.
 */
public class Date extends Command {

    /**
     * Finds any tasks (specifically events and deadlines) occurring before or during the specified date.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The date to check.
     * @throws ListEmptyException If there are no tasks containing the keyword.
     * @throws WrongFormatException If the argument entered does not follow the expected format.
     */
    @Override
    public void execute(BadGpt bot, TaskList taskList, String args) throws ListEmptyException, WrongFormatException {
        rightUsage = "date YYYY-MM-DD\n";
        example = "date 2024-01-31";

        if (args.isEmpty()) {
            throw new WrongFormatException("No date is specified", this);
        }

        try {
            taskList.findByDate(LocalDate.parse(args));
        } catch (DateTimeParseException e) {
            throw new WrongFormatException(e.getMessage(), this);
        }
    }
}
