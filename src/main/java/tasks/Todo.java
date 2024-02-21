package tasks;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone, String priority) {
        super(name, isDone, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String[] getTimes() {
        return new String[] { "NILL", "NILL" };
    }
}
