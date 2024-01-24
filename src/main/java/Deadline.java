public class Deadline extends Task{

    protected String due;

    Deadline(String name, String due) {
        super(name);
        this.due = due;
    }
    @Override
    protected String taskTypeDisplay() {
        return "[D]";
    }
    @Override
    public String toString() {
        return String.format("%s%s %s (by: %s)", this.taskTypeDisplay(), this.completionDisplay(), this.name, this.due);
    }
}
