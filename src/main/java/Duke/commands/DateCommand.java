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
    private static boolean checkArrayContainIntegers(String[] inputs) {
        try {
            for (String input : inputs) {
                Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
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
        boolean isDateNumberAllIntegers = checkArrayContainIntegers(dateNumbers);
        if (!isDateNumberAllIntegers) {
            return false;
        }
        int time = Integer.parseInt(dateNumbers[3]);
        if (!checkValidInteger24hourFormat(time)) {
            return false;
        }
        return true;
    }
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
        LocalDateTime toFind = LocalDateTime.of(
                Integer.parseInt(dateNumbers[2]),
                Integer.parseInt(dateNumbers[1]),
                Integer.parseInt(dateNumbers[0]),
                Integer.parseInt(dateNumbers[3].substring(0, 2)),
                Integer.parseInt(dateNumbers[3].substring(2)));
        assert toFind != null;
        return ui.foundListMessage(tasks.findTaskWithDate(toFind));
    }
}
