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
     *
     * @param words The array of words representing the date command.
     */
    public DateCommand(String[] words) {
        super();
        this.words = words;
    }

    /**
     * Checks if all elements in the array are integers.
     *
     * @param inputs The array to be checked.
     * @return True if all elements are integers, otherwise false.
     */
    private static boolean checkStringArrayContainIntegers(String[] inputs) {
        try {
            for (String input : inputs) {
                Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided time is in a valid 24-hour format.
     *
     * @param time The time to be checked.
     * @return True if the time is in valid 24-hour format, otherwise false.
     */
    private static boolean checkValidInteger24hourFormat(int time) {
        boolean isNegative = time < 0;
        boolean isMoreThan2400 = time >= 2400;
        if (isNegative || isMoreThan2400) {
            return false;
        }
        int numberOfMinutes = 60;
        boolean hasValidMinutes = (time % 100) < numberOfMinutes;
        return hasValidMinutes;
    }

    /**
     * Checks if the provided deadline string has a valid date format.
     *
     * @param dateString The deadline string to be validated.
     * @return True if the deadline string has a valid date format, otherwise false.
     */
    private static boolean isValidDateFormat(String dateString) {
        boolean isShorterThanMinimum = dateString.length() <= 12;
        boolean isLongerThanMaximum = dateString.length() >= 16;
        if (isShorterThanMinimum || isLongerThanMaximum) {
            return false;
        }
        String[] dateNumbers = dateString.split("[/ ]");
        boolean hasIncorrectDateFormatNumbers = dateNumbers.length != 4;
        if (hasIncorrectDateFormatNumbers) {
            return false;
        }
        boolean isDateNumberAllIntegers = checkStringArrayContainIntegers(dateNumbers);
        if (!isDateNumberAllIntegers) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (!checkValidInteger24hourFormat(time)) {
            return false;
        }
        return true;
    }

    /**
     * Executes the DateCommand to find tasks with the specified date and time.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface object.
     * @param storage The storage object.
     * @return A formatted message displaying tasks found with the specified date and time.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("date command");
        }
        String dateString = words[1].trim();
        if (!isValidDateFormat(dateString)) {
            throw new InvalidDateFormatException();
        }
        String[] dateNumbers = dateString.split("[/ ]");
        int year = Integer.parseInt(dateNumbers[2]);
        int month = Integer.parseInt(dateNumbers[1]);
        int day = Integer.parseInt(dateNumbers[0]);
        int hour = Integer.parseInt(dateNumbers[3].substring(0, 2));
        int min = Integer.parseInt(dateNumbers[3].substring(2));

        LocalDateTime toFind = LocalDateTime.of(
                year,
                month,
                day,
                hour,
                min);
        return ui.foundListMessage(tasks.findTaskWithDate(toFind));
    }
}
