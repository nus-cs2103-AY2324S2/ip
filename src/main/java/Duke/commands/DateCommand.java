package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidDateFormatException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to find tasks with a specific date and time.
 * The date should be in the format "DD/MM/YYYY HHMM".
 */
public class DateCommand extends Command {
    private String[] words;

    /**
     * Constructs a DateCommand with the specified array of words.
     * @param words The array of words representing the date command.
     */
    public DateCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Checks if the provided deadline string has a valid date format.
     * @param deadline The deadline string to be validated.
     * @return True if the deadline string has a valid date format, otherwise false.
     */
    private static boolean isValidDateFormat(String deadline) {
        if (deadline.length() <= 12 || deadline.length() >= 16) {
            return false;
        }
        String[] dateNumbers = deadline.split("[/ ]");
        if (dateNumbers.length != 4) {
            return false;
        }
        try {
            for (String i : dateNumbers) {
                Integer.parseInt(i);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (time >= 2400 || time < 0) {
            return false;
        }
        return true;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("date command");
        }
        words[1] = words[1].trim();
        if (!isValidDateFormat(words[1])) {
            throw new InvalidDateFormatException();
        }
        String[] dateNumbers = words[1].split("[/ ]");
        LocalDateTime toFind = LocalDateTime.of(
                Integer.parseInt(dateNumbers[2]),
                Integer.parseInt(dateNumbers[1]),
                Integer.parseInt(dateNumbers[0]),
                Integer.parseInt(dateNumbers[3].substring(0, 2)),
                Integer.parseInt(dateNumbers[3].substring(2)));
        ui.displayFoundList(tasks.findTaskWithDate(toFind));
        return false;
    }
}
