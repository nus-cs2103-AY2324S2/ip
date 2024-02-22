package pew;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.time.format.DateTimeParseException;

public class PewException extends Exception{
    /**
     * Constructor for PewException
     *
     * @param msg error message
     */
    public PewException(String msg) {
        super(msg);
    }

    /**
     * Validates the todo input is not empty
     *
     * @param str user input for todo task
     * @throws PewException if the input is empty after todo
     */
    static void validateToDo(String str) throws PewException {
        if (str.split(" ").length < 2) {
            throw new PewException("ToDo Task Missing!");
        }
    }

    static void validateDeadline(String str) throws PewException {
        if (!str.contains("/by")) {
            throw new PewException("Deadline Date Missing!");
        }
    }

    static void validateEvent(String str) throws PewException {
        if (!str.contains("/from") || !str.contains("/to")) {
            throw new PewException("Event Start and End Date Missing!");
        }
    }

    /**
     * Validates the instruction is in the list of valid instructions
     *
     * @param str user input
     * @throws PewException if the instruction is not in the list
     */
    public static void validateInstn(String str) throws PewException {
        String[] instnArr = {"todo", "deadline", "event", "mark", "unmark", "list", "delete", "find", "pewpewpew"};
        String instn = str.split(" ")[0].toLowerCase();
        if (!Arrays.asList(instnArr).contains(instn)) {
            throw new PewException("Invalid instruction for PeWPeWPeW:(((");
        }
    }

    /**
     * Validates if the list index to be deleted is valid
     *
     * @param index list index to be deleted
     * @param task_arr list of tasks
     * @throws PewException if the index is invalid
     */
    static void validateArrIndex(int index, ArrayList<Task> task_arr) throws PewException {
        if (index > task_arr.size() || index < 1) {
            throw new PewException("Your task number input is invalid, please try again");
        }
    }

    /**
     * Validates of the date and time format is valid
     *
     * @param str date and time user input
     * @throws PewException if the date and time format is invalid
     */
    static void validateDateTime(String str) throws PewException {
        // Your existing validation code remains unchanged
        String[] dateFormats = {
                "M/d/yyyy[ HHmm]",
                "yyyy-MM-dd[ HHmm]",
                "dd-MM-yyyy[ HHmm]",
                "d/M/yyyy[ HHmm]",
                "M-d-yyyy[ HHmm]",
                "d-M-yyyy[ HHmm]"
        };

        boolean isValidFormat = false;

        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
                formatter.parse(str);
                isValidFormat = true;
                break;
            } catch (DateTimeParseException ignored) {
                // Ignore exception for the current format, try the next one
            }
        }

        if (!isValidFormat) {
            throw new PewException("Invalid date and time format, use the following formats: " +
                    "\n" + Arrays.toString(dateFormats));
        }
    }
}
