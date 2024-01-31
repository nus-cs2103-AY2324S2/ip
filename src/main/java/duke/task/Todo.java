package duke.task;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public Todo(String task, TodoState todoState) {
        super(task, todoState);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task;
    }
}
