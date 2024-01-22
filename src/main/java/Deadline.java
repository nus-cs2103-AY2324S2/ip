public class Deadline extends Task {
    private String deadline;
    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by")).strip());
        setDeadline(input.substring(input.indexOf("/by") + 3).strip());
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
