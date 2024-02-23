package anxi.handlers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.tasks.Task;

/**
 * Handler deals with common operations across the different handler child classes.
 */
public class Handler {
    /**
     * Handler constructor.
     */
    public Handler() {
    }

    /**
     * Parses and converts string to LocalDateTime object.
     *
     * @param dateTime              String containing date and time.
     * @return dateTime             LocalDateTime object.
     * @exception AnxiException     Throws when input is not a valid date and time.
     */
    public LocalDateTime parseDateTime(String dateTime) throws AnxiException {
        List<String> separators = Arrays.asList("-", "/");
        List<String> dateCombinations = Arrays.asList("MM_dd_yyyy ", "yyyy_MM_dd ");
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        ArrayList<String> allDates = new ArrayList<>();
        for (String d : dateCombinations) {
            for (String s : separators) {
                String pattern = d.replaceAll("_", s);
                allDates.add(pattern);
            }
        }

        for (String d : allDates) {
            for (String t : timeCombinations) {
                try {
                    return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(d + t));
                } catch (DateTimeParseException dt) {
                    // Don't catch exception as it is still trying different combinations
                }
            }
        }

        throw new AnxiException("Invalid date or time... well hard to help you here\n"
                + "\n<yyyy/MM/dd> or <MM-dd-yyyy>, forward slashes or hyphens and the 24hr clock");
    }

    /**
     * Parses and converts string to LocalTime object.
     *
     * @param time                  String containing time.
     * @return time                 LocalTime object.
     * @exception AnxiException     Throws when input is not a valid time.
     */
    public LocalTime parseTime(String time) throws AnxiException {
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");

        for (String t : timeCombinations) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(t));
            } catch (DateTimeParseException dt) {
                // Don't catch exception as it is still trying different combinations
            }
        }

        throw new AnxiException("Invalid time.... try again\nSimplest is the 24hr clock");
    }

    /**
     * Parses and converts string to LocalDate object.
     * @param date                  String containing date.
     * @return date                 LocalDate object.
     * @exception AnxiException     Throws when input is not a valid date.
     * */
    public LocalDate parseDate(String date) throws AnxiException {
        List<String> separators = Arrays.asList("-", "/");
        List<String> dateCombinations = Arrays.asList("MM_dd_yyyy", "yyyy_MM_dd");

        ArrayList<String> allDates = new ArrayList<>();
        for (String d : dateCombinations) {
            for (String s : separators) {
                String pattern = d.replaceAll("_", s);
                allDates.add(pattern);
            }
        }

        for (String d : allDates) {
            try {
                return LocalDate.parse(date.strip(), DateTimeFormatter.ofPattern(d));
            } catch (DateTimeParseException dt) {
                // Don't catch exception as it is still trying different combinations
            }
        }

        throw new AnxiException("Wrong date input... try again\n<yyyy/MM/dd> or <MM-dd-yyyy>");
    }

    /**
     * Converts string to integer.
     * @param input             Integer in string form.
     * @return int              Result of converting integer to string.
     * @throws AnxiException    Throws if input is not a valid integer.
     */
    public int stringToInt(String input) throws AnxiException {
        int index;
        try {
            index = Integer.parseInt(input.strip());
        } catch (NumberFormatException n) {
            throw new AnxiException("Tsk tsk integers only.");
        }

        return index;
    }

    /**
     * Checks if index is in range of tasks.
     * @param index             Index of task being searched for.
     * @param numOfTasks        Total number of tasks in task list currently.
     * @throws AnxiException    Throws if index is out of bounds.
     */
    public void checkOutOfBounds(int index, int numOfTasks) throws AnxiException {
        boolean belowLower = (index - 1) < 0;
        boolean exceedUpper = index > numOfTasks;

        if (belowLower || exceedUpper) {
            throw new AnxiException("Index out of bounds, no task found.");
        }
    }

    /**
     * Saves updated task to storage.
     * @param storage           Instance of storage object.
     * @param t                 Task object to be saved to file.
     * @param index             Index of task.
     * @param numOfTasks        Total number of tasks in task list.
     * @throws AnxiException    Throws if there is an error saving to file.
     */
    public void updateTaskInStorage(Storage storage, Task t, int index, int numOfTasks) throws AnxiException {
        try {
            storage.updateTask(t, index, numOfTasks);
        } catch (IOException e) {
            throw new AnxiException("Error, unable to update task in storage.");
        }
    }

    /**
     * Adds new task to storage.
     * @param storage           Instance of storage object.
     * @param task              Task object to be saved to file.
     * @throws AnxiException    Throws if there is an error saving to file.
     */
    public void addTaskInStorage(Storage storage, Task task) throws AnxiException {
        try {
            storage.addNewTask(task);
        } catch (IOException e) {
            throw new AnxiException("Error, unable to update task in storage.");
        }
    }
}
