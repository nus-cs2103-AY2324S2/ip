public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.type = "D";
    }

    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (by: " + deadline + ")";
    }

    public String toText() {
        return super.toText() + " / " + deadline;
    }

    static public Task fromText(String description, String done, String by) {
        Task task = new Deadline(description, by);
        task.isDone = done.equals("1");
        return task;
    }
}
