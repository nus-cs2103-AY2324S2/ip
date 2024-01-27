public class Deadline extends Task {
    private String deadline;
    public Deadline(String listItem, String inputItem, String deadline) {
        super(listItem, inputItem);
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
