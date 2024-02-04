package main.java.task;

public class DeadlineTask extends Task {
    private final String endDate;

    public DeadlineTask(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String getTask() {
        if (isDone) {
            return "[D][X] " + this.description + " (by: " + endDate + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + endDate + ")";
        }
    }

    @Override
    public String toFileString() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.endDate;
        } else {
            return "D | 0 | " + this.description + " | " + this.endDate;
        }
    }
}
