package duke.tasks;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    protected LocalDate doneBy;

    public DeadlineTask(String description, LocalDate doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    public LocalDate getDoneBy() {
        return this.doneBy;
    }

    @Override
    public String toString() {
        return "D" + " | " + super.toString() + " | " + "(by: " + getDoneBy() + ")";
    }

}
