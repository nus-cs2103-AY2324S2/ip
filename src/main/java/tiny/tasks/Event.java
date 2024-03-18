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
        String invalidDateErrorMessage = "Please ensure that the date is valid. eg. 2024-01-29";

        // Processes the date
        try {
            String[] dateSplit = dateTimeSplit[0].split("-");
            assert dateSplit.length == 3;
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);

            if (!isValidDate(month, day)) {
                throw new TinyException(invalidDateErrorMessage);
            }
        } catch (TinyException e) {
            throw e;
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
            } catch (TinyException e) {
                throw e;
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
            if (!isValidTime(time)) {
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
        return time >= 0 && time <= 2359;
    }

    private Boolean isValidDate(int month, int day) {
        if (month > 12 || month < 1) {
            return false;
        }

        if (month == 2) {
            return day <= 29;
        }

        int[] thirtyDayMonth = new int[] { 4, 6, 9, 11 };
        int[] thirtyOneDayMonth = new int[] { 1, 3, 5, 7, 8, 10, 12 };

        for (int i = 0; i < thirtyOneDayMonth.length; i++) {
            if (thirtyDayMonth[i] == month) {
                return day <= 30;
            }
        }

        for (int i = 0; i < thirtyOneDayMonth.length; i++) {
            if (thirtyOneDayMonth[i] == month) {
                return day <= 31;
            }
        }
        return true;
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
