package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a task given by the user that is needs to be stored
 */
abstract public class Task {
    private static final List<String> DATE_FORMATS = List.of(
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd-MM-yyyy",
            "yyyy-MM-d",
            "d-MM-yyyy",
            "d/MM/yyyy",
            "dd/MM/yyyy",
            "yyyy/MM/dd",
            "yyyy/MM/d"
    );

    private static final List<String> TIME_FORMATS = List.of(
            "HH[:mm[:ss[.SSS]]]",
            "H[:mm[:ss[.SSS]]]",
            "HH[mm[ss]]",
            "hh[:mm[:ss]] a",
            "h[:mm[:ss]] a",
            "hh[mm[ss]] a"
    );

    private final String name;
    private boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param name The content of the task
     */
    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Checks if a task is marked as done.
     *
     * @return Whether the task is marked done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks a task as unfinished.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the task into a string of certain format as to be written in the file.
     *
     * @return The formatted string that is suitable to be written in the file.
     */
    String taskToLine() {
        String mark = "X";
        if (isDone) {
            mark = "O";
        }
        return mark + " | " + name;
    }

    /**
     * Converts a string representing the dateTime into a LocalDateTime object.
     *
     * @param string a string that represents the dateTime
     * @return The LocalDateTime object that contains the same information as the string
     * @throws DukeException if the conversion is unsuccessful
     */
    static LocalDateTime parse(String string) throws DukeException {
        for (String date : DATE_FORMATS) {
            for (String time : TIME_FORMATS) {
                String format = date + " " + time;
                try {
                    return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }
        throw new DukeException("Oops! Unable to extract time from the prompt!");
    }

    /**
     * Converts a string representing the date into a LocalDate object.
     *
     * @param string a string that represents the dateTime
     * @return The LocalDate object that contains the same information as the string
     * @throws DukeException if the conversion is unsuccessful
     */
    public static LocalDate parseDate(String string) throws DukeException {
        for (String date : DATE_FORMATS) {
            try {
                return LocalDate.parse(string, DateTimeFormatter.ofPattern(date));
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Oops! Cannot understand the input date!");
    }

    /**
     * Checks if the given date matches own date.
     *
     * @param localDate The given date that the user wishes to check
     * @return False by default.
     */
    boolean canMatchDate(LocalDate localDate) {
        return false;
    }


    /**
     * Checks if a keyword is contained in the content of the task.
     *
     * @param keyword The keyword typed in by the user
     * @return True if the keyword is contained in the content
     */
    boolean isContaining(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Prints the task with certain information.
     *
     * @return A string with the content and whether it is done or not
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
