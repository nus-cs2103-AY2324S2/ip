public class Deadline extends Task {
    protected String type = "D";
    protected String deadline;
    public Deadline(int index, String description, String deadline) {
        super(index, description);
        this.deadline = deadline;
    }

    @Override
    public String getTask() {
        return index + ". [" + type + "][" + getStatusIcon() + "] " + description + "(" + deadline + ")";
    }

    @Override
    public String save() {
        return type + "|" + (isDone ? "1" : "0") + "|" + description + "|" + deadline;
    }
}
