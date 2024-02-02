public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString().trim();
    }

    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}
