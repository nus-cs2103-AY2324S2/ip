package charlie.models;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
    }

    /**
     * @return string containing information about to-Do task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
