package linus;

public class Todo extends Task {
    public Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}