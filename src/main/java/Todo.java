public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        return "[T][" + status + "] " + super.toString();
    }
}
