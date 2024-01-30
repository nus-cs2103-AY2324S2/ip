package main.java;

public class DeadlineTask extends Task {
    private String endDate;
    public DeadlineTask(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String getTask() {
        if (isDone) {
            return "[D][X] " + this.description + "(by: " + endDate + ")";
        } else {
            return "[D][ ] " + this.description + "(by: " + endDate + ")";
        }
    }
}
