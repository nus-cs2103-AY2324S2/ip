package tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String completed, String description) {
        super(description, completed.equals("1"));
    }

    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("T | %d | %s", intIsDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}