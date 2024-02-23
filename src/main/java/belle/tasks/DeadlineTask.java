package belle.tasks;

import belle.others.DateFormatter;

/**
 * Represents tasks with deadline.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs DeadlineTask.
     *
     * @param name Name of task.
     * @param isDone Boolean that indicates
     *             whether task is done.
     * @param deadline Deadline to be set
     *                 in deadlinetask.
     */
    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
        DateFormatter d = new DateFormatter(this.deadline);
        if (d.hasValidDate()) {
            this.deadline = d.convertDate();
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String getType() {
        return "D";
    }

    public void setDeadline(String newDeadline) {
        this.deadline = newDeadline;
    }
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + deadline + ")";
    }
}
