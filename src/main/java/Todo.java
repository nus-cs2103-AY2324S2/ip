public class Todo extends Task {

    public Todo(String description) {
        super(description);
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