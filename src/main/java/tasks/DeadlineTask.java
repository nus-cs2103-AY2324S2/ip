package tasks;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class DeadlineTask extends Task {
    protected LocalDate doneBy;

    public DeadlineTask(String description, LocalDate doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    @Override
    public void updateDoneIcon() {
        super.updateDoneIcon();
    }

    @Override
    public String getDoneIcon() {
        return super.getDoneIcon();
    }

    public LocalDate getDoneBy() {
        return this.doneBy;
    }

    @Override
    public void markDone() {
        super.markDone();
    }

    @Override
    public void markNotDone() {
        super.markNotDone();
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + "(by: " + getDoneBy() + ")";
    }

}
