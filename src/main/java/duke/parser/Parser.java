package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.dukeexception.DukeException;

/**
 * This class reads in the input text and split it to the different groups.
 * @author Tang Hao Liang
 */
public class Parser {

    /**
     * Returns Array of string split into command and description.
     * If the input is bye or list, return spilt.
     *
     * @param input User's input.
     * @return Array of string split into command and description.
     * @throws DukeException If command lacks description.
     */
    public String[] parse(String input) throws DukeException {
        String[] split = input.split(" ", 2);
        if (split[0].equalsIgnoreCase("bye")
                || split[0].equalsIgnoreCase("list")
                || split[0].equalsIgnoreCase("help")) {
            return split;
        } else if (split.length == 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            return split;
        }
    }

    /**
     * Converts string to integer.
     * Minuses 1 for array input.
     *
     * @param num User's input.
     * @return Array get value.
     * @throws DukeException If num is not a number.
     */
    public int stringToNum(String num) throws DukeException {
        try {
            return Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number for the task that you wish to edit.\n");
        }
    }

    /**
     * Returns task's description
     *
     * @param input User's input.
     * @return Task's description.
     * @throws DukeException If user's input is too short.
     */
    public String toDo(String input) throws DukeException {
        if (input.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            return input;
        }
    }

    /**
     * Returns string array of description and deadline.
     *
     * @param input User's input.
     * @return Task's description and deadline.
     * @throws DukeException If description is too short and no deadline inputted.
     */
    public String[] deadline(String input) throws DukeException {
        if (input.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split = input.split(" /by ");
            if (split[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split.length != 2 || split[1].length() <= 1) {
                throw new DukeException("Please enter the deadline of the task. \n");
            } else {
                return split;
            }
        }
    }

    /**
     * Returns string array of description and duration.
     *
     * @param input User's input.
     * @return Task's description and duration.
     * @throws DukeException If description is too short and no duration inputted.
     */
    public String[] event(String input) throws DukeException {
        if (input.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] descriptionDurationSplit = input.split(" /from ");
            String[] fromToSplit = descriptionDurationSplit[1].split(" /to ");
            if (descriptionDurationSplit[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (descriptionDurationSplit.length != 2) {
                throw new DukeException("Please enter the duration of the event. \n");
            } else if (fromToSplit.length != 2) {
                throw new DukeException("Please enter the ending time of the event. \n");
            } else {
                return new String[]{descriptionDurationSplit[0], fromToSplit[0], fromToSplit[1]};
            }
        }
    }

    /**
     * Converts string to LocalDateTime.
     *
     * @param dateTime Date and time in String.
     * @return Date and Time in LocalDateTime.
     * @throws DukeException If date and time is not in correct format.
     */
    public LocalDateTime stringToDateTime(String dateTime) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTime.strip(), dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in format of yyyy-mm-dd HH:mm.\n");
        }
    }

}
