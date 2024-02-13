package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String doneIcon;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.doneIcon = "[ ]";
    }

    public void updateDoneIcon() {
        if (this.isDone) {
            this.doneIcon = "[X]";
        } else {
            this.doneIcon = "[ ]";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getDoneIcon() {
        return this.doneIcon;
    }

    public void markDone() {
        this.isDone = true;
        updateDoneIcon();
        System.out.println("_________________________________________\n");
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(this);
        System.out.println("_________________________________________\n");
    }

    public void markNotDone() {
        this.isDone = false;
        updateDoneIcon();
        System.out.println("_________________________________________\n");
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(this);
        System.out.println("_________________________________________\n");
    }
    @Override
    public String toString() {
        return getDoneIcon() + " | " + getDescription();
    }
}
