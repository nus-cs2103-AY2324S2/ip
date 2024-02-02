package harvard.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) super.mark();

    }
    @Override
    public String saveString() {
        return "T," + (this.isDone ? "1," : "0,") + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
