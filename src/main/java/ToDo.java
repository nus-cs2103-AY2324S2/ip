public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String status, String description) {
        super(status.equals("1"), description);
    }

    @Override
    public String toFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
