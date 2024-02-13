package atlas.task;

public class ToDo extends Task{

    public ToDo(String description, int priority) {
        super(description, priority);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description + " | " + priority;
    }
}
