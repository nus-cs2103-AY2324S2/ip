package tiny.tasks;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

/**
 * Represents an event task.
*/
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalTime endTime;

    /**
     * Initializes Event.
     *
     * @param description   Description of the task.
     * @param startDateTime Start date and start time of the task.
     * @param endDateTime   End date and end time of the task.
     */
    public Event(String description, String startDateTime, String endTime) throws TinyException {
        super(description);
        this.startDateTime = startDatetimeParser(startDateTime);
        this.endTime = endTimeParser(endTime);
    }

    /**
     * Initializes Event.
     *
     * @param description   Description of the task.
     * @param isDone        Status of the task.
     * @param startDateTime Start date and start time of the task.
     * @param endDateTime   End date and end time of the task.
     */
    public Event(String description, boolean isDone, String startDateTime, String endTime) throws TinyException {
        super(description, isDone);
        this.startDateTime = startDatetimeParser(startDateTime);
        this.endTime = endTimeParser(endTime);
    }

    /**
     * Parses the user input into the LocalDateTime format.
     *
     * @param dateTime User input date and time string to be parsed.
     * @return LocalDateTime format the start date and start time from the user
     *         input.
     * @throws TinyException When input is invalid.
     */
    public LocalDateTime startDatetimeParser(String dateTime) throws TinyException {
        String[] dateTimeSplit = dateTime.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        String errorMsg = "Please ensure that you are using the format event <description> "
                + "/from yyyy-MM-dd <time> /to <end date>. "
                + "eg. event meeting /from 2024-01-29 1835 /to 2035";
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
     * Parses the user input into the LocalTime format.
     *
     * @param timeStr User input date and time string to be parsed.
     * @return LocalTime format of end time from the user input.
     * @throws TinyException When input is invalid.
     */
    public LocalTime endTimeParser(String timeStr) throws TinyException {
        String errorMsg = "Please ensure that you are using the format event <description> "
                + "/from yyyy-MM-dd <time> /to <end date>. "
                + "eg. event meeting /from 2024-01-29 1835 /to 2035";
        int time = Integer.parseInt(timeStr);
        try {
            if (isValidTime(time)) {
                throw new TinyException("Please choose your end time from 0000 to 2359!");
            }
            String[] hourMinuteSplit = timeStr.split("");
            assert hourMinuteSplit.length == 4;
            int hour = Integer.parseInt(hourMinuteSplit[0] + hourMinuteSplit[1]);
            int minute = Integer.parseInt(hourMinuteSplit[2] + hourMinuteSplit[3]);
            return LocalTime.of(hour, minute);

        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }

    /**
     * Formats the start LocalDateTime instance into String to be displayed.
     *
     * @return String of the date and time to be displayed.
     */
    public String startDatetimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return startDateTime.format(formatter);
    }

    /**
     * Formats the end LocalTime instance into String to be displayed.
     *
     * @return String of the time to be displayed.
     */
    public String endTimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return endTime.format(formatter);
    }

    /**
     * Formats the start LocalDateTime instance into String to be saved.
     *
     * @return String of the date and time to be saved.
     */
    public String startDatetimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return startDateTime.format(formatter);
    }

    /**
     * Formats the LocalTime instance into String to be saved.
     *
     * @return String of the time to be saved.
     */
    public String endTimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return endTime.format(formatter);
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
        return "E" + super.formatToSave() + " | " + startDatetimeSaveFormat() + " | " + endTimeSaveFormat();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDatetimeFormat() + " to: " + endTimeFormat() + ")";
    }
}
