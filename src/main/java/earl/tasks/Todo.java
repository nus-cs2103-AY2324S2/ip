package earl.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String status, String description) {
        super(status, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T," + super.getStatusIcon() + "," + description;
    }
}
