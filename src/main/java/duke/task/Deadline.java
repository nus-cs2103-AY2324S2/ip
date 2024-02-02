package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalDateTime byDateTime;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
        this.byDateTime = null;
    }

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDate = null;
        this.byDateTime = byDateTime;
    }

    public Deadline(String description, boolean isDone, LocalDate byDate) {
        super(description, isDone);
        this.byDate = byDate;
        this.byDateTime = null;
    }

    public Deadline(String description, boolean isDone, LocalDateTime byDateTime) {
        super(description, isDone);
        this.byDate = null;
        this.byDateTime = byDateTime;
    }

    private String padZero(int s) {
        if (s < 10) {
            return "0" + s;
        }
        return Integer.toString(s);
    }

    public String getDeadlineForSave() {
        return this.byDate == null
                ? (this.byDateTime.getYear() + "-" + padZero(this.byDateTime.getMonthValue())
                + "-" + padZero(this.byDateTime.getDayOfMonth()) + " " + padZero(this.byDateTime.getHour()) + ":"
                + padZero(this.byDateTime.getMinute()))
                : (this.byDate.getYear() + "-" + padZero(this.byDate.getMonthValue())
                + "-" + padZero(this.byDate.getDayOfMonth()));
    }

    public String getDeadlineForDisplay() {
        return this.byDate == null
                ? (this.byDateTime.getMonth() + " " + padZero(this.byDateTime.getDayOfMonth())
                + " " + this.byDateTime.getYear() + " " + padZero((this.byDateTime.getHour() > 12
                ? this.byDateTime.getHour() - 12 : this.byDateTime.getHour())) + ":"
                + padZero(this.byDateTime.getMinute()) + (this.byDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.byDate.getMonth() + " " + padZero(this.byDate.getDayOfMonth())
                + " " + this.byDate.getYear());
    }

    @Override
    public String getDescriptionStatus() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by: "
                + this.getDeadlineForDisplay() + ")";
    }

    @Override
    public String[] getFields() {
        String[] result = new String[3];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        result[2] = this.getDeadlineForSave();
        return result;
    }
}
