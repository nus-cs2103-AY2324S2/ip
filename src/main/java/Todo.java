public class Todo extends Task {
    public Todo(String description, boolean isComplete) {
        super(description);
        this.isComplete = isComplete; // Adjust constructor to accept isComplete
    }

    @Override
    public String toFileFormat() {
        return String.format("T | %d | %s", isComplete ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

}
