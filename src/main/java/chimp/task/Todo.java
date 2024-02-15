package chimp.task;
public class Todo extends Task {
    public Todo(String text, TaskStatus status) {
        super(text, status);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
