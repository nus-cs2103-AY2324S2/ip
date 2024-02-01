public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + this.description;
    }
}
