public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("T | %s | %s", status, super.formatTask());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
