package duke.tasks;

import duke.others.DateFormatter;

/**
 * Represents tasks with deadline.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs DeadlineTask.
     */
    public DeadlineTask(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
        DateFormatter d = new DateFormatter(this.deadline);
        if (d.isValidDate()) {
            this.deadline = d.convertDate();
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String getType() {
        return "D";
    }

    public void setDeadline(String newdeadline) {
        this.deadline = newdeadline;
    }
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + deadline + ")";
    }
}
