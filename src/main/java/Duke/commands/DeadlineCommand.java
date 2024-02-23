package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidDeadlineException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String[] words;

    /**
     * Constructs a DeadlineCommand with the specified array of words.
     * @param words The array of words representing the deadline command.
     */
    public DeadlineCommand(String[] words) {
        super();
        this.words = words;
    }
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
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean isOneWord = words.length == 1;
        if (isOneWord) {
            throw new EmptyDescriptionException("deadline");
        }

        int deadlineStartIdx = words[1].indexOf("/by");
        boolean isInvalidDeadline = deadlineStartIdx == -1;
        if (isInvalidDeadline) {
            throw new InvalidDeadlineException();
        }

        String deadlineDescription = words[1].substring(0, deadlineStartIdx);
        String deadlineDate = words[1].substring(deadlineStartIdx + 4);
        if (!isValidDateFormat(deadlineDate)) {
            throw new InvalidDeadlineException();
        }
        Task newDeadline = new Deadline(deadlineDescription, deadlineDate);
        assert newDeadline != null;
        tasks.addTask(newDeadline);
        storage.addToWriteFile(newDeadline);
        int numberOfCurrentTasks = tasks.getNumberOfTasks();
        String result = ui.addTaskMessage(newDeadline, numberOfCurrentTasks);
        return result;
    }
}
