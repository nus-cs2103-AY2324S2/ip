public class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    public Todo(boolean isDone, String desc) {
        super(isDone, desc);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
