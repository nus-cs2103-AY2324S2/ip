public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    @Override
    String taskToLine() {
        return "T | " + super.taskToLine();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
