package tiny.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tiny.exceptions.TinyException;

public class Deadline extends Task {
    protected LocalDateTime endDatetime;

    public Deadline(String description, String endDatetime) throws TinyException {
        super(description);
        this.endDatetime = datetimeParser(endDatetime);
    }

    public Deadline(String description, boolean isDone, String endDatetime) throws TinyException {
        super(description, isDone);
         this.endDatetime = datetimeParser(endDatetime);
    }

    public LocalDateTime datetimeParser(String dateTime) throws TinyException {
        String[] dateTimeSplit = dateTime.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        String errorMsg = "Please ensure that you are using the format deadline <description> /by yyyy-MM-dd <time>. eg. deadline assignment /by 2024-01-29 1835";
        //Date
        try {
            String[] dateSplit = dateTimeSplit[0].split("-");
            year = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            day = Integer.parseInt(dateSplit[2]);
            
        } catch (Exception e) {
                throw new TinyException(errorMsg);
        }

        //Time
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

        //Combine
        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new TinyException(errorMsg);
        }
    }

    public String endDatetimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return endDatetime.format(formatter);
    }

    public String endDatetimeSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return endDatetime.format(formatter);
    }    

    @Override
    public String toSave() {
        return "D" + super.toSave() + " | " + endDatetimeSaveFormat();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDatetimeFormat() + ")";
    }

}
