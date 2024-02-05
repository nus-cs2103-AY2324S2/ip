package com.example.Mamta;

import java.time.LocalDate;
public class Deadline extends Task {
    private final String deadline;
    Deadline(String content, String deadline) {
        super(content);
        if (deadline.isEmpty()) { //handling the case where deadline does not get a valid deadline
            this.deadline = String.valueOf(MamtaException.invalidDates());
        } else {
            this.deadline = transformDates(deadline);
        }
    }

    Deadline(boolean isComplete, String content, String deadline) {
        super(isComplete, content);
        this.deadline = transformDates(deadline);
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
    public Deadline markDone() {
        return new Deadline(true, this.content, this.deadline);
    }
    @Override
    public Deadline unmarkTask() {
        return new Deadline(false, this.content, this.deadline);
    }
    public String toString() {
        if (this.isComplete) {
            return String.format("D|X|%s|%s", this.content, this.deadline);
        } else {
            return String.format("D| |%s|%s", this.content, this.deadline);
        }
    }
}
