package tiny.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

/**
 * Represents a deadline task.
*/
public class Deadline extends Task {
    protected LocalDateTime endDatetime;

    /**
     * Initializes Deadline.
     *
     * @param description Description of the task.
     * @param endDateTime End date and end time of the tasks.
     */
    public Deadline(String description, String endDatetime) throws TinyException {
        super(description);
        this.endDatetime = datetimeParser(endDatetime);
    }

    /**
     * Initializes Deadline.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     * @param endDateTime End date and end time of the tasks.
     */
    public Deadline(String description, boolean isDone, String endDatetime) throws TinyException {
        super(description, isDone);
        this.endDatetime = datetimeParser(endDatetime);
    }

    /**
     * Parses the user input into the LocalDateTime format.
     *
     * @param dateTime User input date and time string to be parsed.
     * @return LocalDateTime format the date and time from the user input.
     * @throws TinyException When input is invalid.
     */
    public LocalDateTime datetimeParser(String dateTime) throws TinyException {
        String[] dateTimeSplit = dateTime.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        String errorMsg = "Please ensure that you are using the format deadline <description> /by "
                + "yyyy-MM-dd <time>. eg. deadline assignment /by 2024-01-29 1835";
        // Processes the date
        try {
            String[] dateSplit = dateTimeSplit[0].split("-");
            assert dateSplit.length == 3;
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);

        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }

        // Processes the time
        if (dateTimeSplit[1].length() == 4) {
            try {
                int time = Integer.parseInt(dateTimeSplit[1]);
                if (isValidTime(time)) {
                    throw new TinyException("Please choose a time from 0000 to 2359!");
                }
                String[] hourMinuteSplit = dateTimeSplit[1].split("");
                assert hourMinuteSplit.length == 4;
                hour = Integer.parseInt(hourMinuteSplit[0] + hourMinuteSplit[1]);
                minute = Integer.parseInt(hourMinuteSplit[2] + hourMinuteSplit[3]);
            } catch (Exception e) {
                throw new TinyException(errorMsg);
            }
        }

        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }

    /**
     * Formats the DateTimeLocal instance into String to be displayed.
     *
     * @return String of the date and time to be displayed.
     */
    public String endDatetimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return endDatetime.format(formatter);
    }

    /**
     * Formats the DateTimeLocal instance into String to be saved.
     *
     * @return String of the date and time to be saved.
     */
    public String endDatetimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return endDatetime.format(formatter);
    }

    private boolean isValidTime(int time) {
        return time >= 2400 || time < 0;
    }

    /**
     * Formats the task into the correct format to save.
     *
     * @return String of the task in the correct format to save.
     */       
    @Override
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + endDatetimeSaveFormat();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDatetimeFormat() + ")";
    }

}
