public class Todo  extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "]" + " Todo | " + this.description;
    }
}