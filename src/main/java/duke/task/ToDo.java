package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDoc
     *
     * @return description + isDone + from + to
     */
    @Override
    public String getTokens() {
        return String.join(",", TaskType.TODO.toString(), super.getTokens());
    }
}