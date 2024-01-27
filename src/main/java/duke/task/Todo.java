package duke.task;

public class Todo extends Task {
    public Todo(String input) {
        super(input);
    }

    public Todo(String description, Boolean hasDone) {
        super.setHasDone(hasDone);
        super.setDescription(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
