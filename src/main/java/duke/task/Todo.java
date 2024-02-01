package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeContent() {
        return "T |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}