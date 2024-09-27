package pew;

public class Deadline extends Task {
    protected String type = "D";
    protected String deadline;

    /**
     * Constructor for Deadline
     *
     * @param index the index of the task
     * @param description the description of the task
     * @param deadline the deadline of the task
     */
    public Deadline(int index, String description, String deadline) {
        super(index, description);
        this.deadline = deadline;
    }

    @Override
    public String getTask() {
        return index + ". [" + type + "][" + getStatusIcon() + "] " + description + " (" + deadline + ")";
    }

    @Override
    public String save() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description + "|" + deadline;
    }
}
