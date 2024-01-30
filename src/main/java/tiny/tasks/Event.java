package tiny.tasks;

import tiny.exceptions.TinyException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalTime endDateTime;

    public Event(String name, String startDateTime, String endDateTime) throws TinyException {
        super(name);
        this.startDateTime = startDatetimeParser(startDateTime);
        this.endDateTime = endDatetimeParser(endDateTime);
    }

    public Event(String name, boolean isDone, String startDateTime, String endDateTime) throws TinyException {
        super(name, isDone);
        this.startDateTime = startDatetimeParser(startDateTime);
        this.endDateTime = endDatetimeParser(endDateTime);
    }

    public LocalDateTime startDatetimeParser(String date) throws TinyException {
        String[] dateTimeSplit = date.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        String errorMsg = "Please ensure that you are using the format event <description> /from yyyy-MM-dd <time> /to <end date>. "
                + "eg. event meeting /from 2024-01-29 1835 /to 2035";
        // Date
        try {
            String[] dateSplit = dateTimeSplit[0].split("-");
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);

        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }

        // Time
        if (dateTimeSplit[1].length() == 4) {
            try {
                int time = Integer.parseInt(dateTimeSplit[1]);
                if (time >= 2400 || time < 0) {
                    throw new TinyException("Please choose a time from 0000 to 2359!");
                }
                String[] hourMinuteSplit = dateTimeSplit[1].split("");
                hour = Integer.parseInt(hourMinuteSplit[0] + hourMinuteSplit[1]);
                minute = Integer.parseInt(hourMinuteSplit[2] + hourMinuteSplit[3]);
            } catch (Exception e) {
                throw new TinyException(errorMsg);
            }
        }

        // Combine
        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }


    public LocalTime endDatetimeParser(String timeStr) throws TinyException { 
        String errorMsg = "Please ensure that you are using the format event <description> /from yyyy-MM-dd <time> /to <end date>. "
        + "eg. event meeting /from 2024-01-29 1835 /to 2035";
            int time = Integer.parseInt(timeStr);
            try {
                if (time >= 2400 || time < 0) {
                    throw new TinyException("Please choose your end time from 0000 to 2359!");
                }
                String[] hourMinuteSplit = timeStr.split("");
                int hour = Integer.parseInt(hourMinuteSplit[0] + hourMinuteSplit[1]);
                int minute = Integer.parseInt(hourMinuteSplit[2] + hourMinuteSplit[3]);
                return LocalTime.of(hour, minute);

            } catch (Exception e) {
                throw new TinyException(errorMsg);
            }
    }
    
    public String startDatetimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return startDateTime.format(formatter);
    }

    public String endDatetimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return endDateTime.format(formatter);
    }    

    public String startDatetimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return startDateTime.format(formatter);
    }        

    public String endDatetimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return endDateTime.format(formatter);
    }    

    @Override
    public String toSave() {
        return "E" + super.toSave() + " | " + startDatetimeSaveFormat() + " | " + endDatetimeSaveFormat();
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDatetimeFormat() + " to: " + endDatetimeFormat() + ")";
    }
}
