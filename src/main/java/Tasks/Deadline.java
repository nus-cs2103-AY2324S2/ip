package Tasks;

import Tasks.Task;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String date) {
        super(description);
        this.deadline = date;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String getDescription() {
        return String.format("%s (by: %s)", this.description, this.deadline);
    }
    public String getCommand() {
        return String.format("deadline %s /by %s\n%b\n", this.description, this.deadline, this.isDone);
    }
}
