package com.example.Mamta;

import java.time.LocalDate;

public class Event extends Task {
    private final String startTime;
    private final String endTime;

    Event(String content, String startTime, String endTime) {
        super(content);
        if (startTime.isEmpty() || endTime.isEmpty()) { //handling the case where event does not get valid dates.
            this.startTime = String.valueOf(MamtaException.invalidDates());
            this.endTime = "";
        } else {
            this.startTime = transformDates(startTime);
            this.endTime = transformDates(endTime);
        }
    }

    Event(boolean isComplete, String content, String startTime, String endTime) {
        super(isComplete, content);
        this.startTime = transformDates(startTime);
        this.endTime = transformDates(endTime);
    }

    public String transformDates(String deadline) {
        String year = "";
        String month = "";
        String day = "";
        String time = "";
        try {
            String[] splitOutput = deadline.split("-");
            for (String s : splitOutput) {
                year = splitOutput[0];
                month = splitOutput[1];
                day = splitOutput[2].split(" ")[0];
                time = splitOutput[2].split(" ")[1];
            }
            LocalDate date = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
            year = String.valueOf(date.getYear());
            month = String.valueOf(date.getMonth());
            day = String.valueOf(date.getDayOfMonth());
            return String.format("%s %s %s %s", month, day, year, time);
        } catch (Exception e) {
            return deadline;
        }
    }

    @Override
    public Event markDone() {
        return new Event(true, this.content, this.startTime, this.endTime);
    }
    @Override
    public Event unmarkTask() {
        return new Event(false, this.content, this.startTime, this.endTime);
    }

    public String toString() {
        if (this.isComplete) {
            return String.format("E|X|%s|%s|%s", this.content, this.startTime, this.endTime);
        } else {
            return String.format("E| |%s|%s|%s", this.content, this.startTime, this.endTime);
        }
    }

}
