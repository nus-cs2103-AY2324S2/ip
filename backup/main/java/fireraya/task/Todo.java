package fireraya.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveFormat() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
