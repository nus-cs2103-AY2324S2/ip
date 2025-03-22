package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidEventException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class for EventCommand
 */
public class EventCommand extends Command {
    private String[] words;
    /**
     * Constructs an EventCommand object with the given array of command words.
     *
     * @param words The array of strings containing the command and its arguments.
     */
    public EventCommand(String[] words) {
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
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("event");
        }
        int startIdx = words[1].indexOf("/from");
        int endIdx = words[1].indexOf("/to");
        boolean hasInvalidEventFormat = startIdx == -1 || endIdx == -1;
        if (hasInvalidEventFormat) {
            throw new InvalidEventException();
        }
        String[] eventDates = words[1].substring(startIdx).split("/from | /to ");
        boolean hasIncorrectNumbers = eventDates.length != 3;
        if (hasIncorrectNumbers) {
            throw new InvalidEventException();
        }
        String eventDescription = words[1].substring(0, startIdx);
        String startDate = eventDates[1];
        String endDate = eventDates[2];
        if (!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
            throw new InvalidEventException();
        }
        Task newEvent = new Event(eventDescription, startDate, endDate);
        tasks.addTask(newEvent);
        storage.addToWriteFile(newEvent);
        return ui.addTaskMessage(newEvent, tasks.getNumberOfTasks());
    }
}
