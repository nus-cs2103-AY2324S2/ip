public class Deadline extends Task {
    private String deadline;
    public Deadline(String listItem, String deadline) {
        super(listItem);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem +
                " (by: " +
                this.deadline + ")"
                ;
    }
}
