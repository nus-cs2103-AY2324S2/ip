package zhen.task;

public class Todos extends Task {
    public Todos(String message) {
        super(message);
    }

    @Override
    public String toString() {
        if (isCompleted()) {
            return "[T][X] " + getMessage();
        } else {
            return "[T][ ] " + getMessage();
        }
    }
}
