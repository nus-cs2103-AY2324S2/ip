package tasks;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isComplete) {
        super(task, isComplete);
    }

    @Override
    public String stringify() {
        return "[T]" + super.stringify();
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }

}
