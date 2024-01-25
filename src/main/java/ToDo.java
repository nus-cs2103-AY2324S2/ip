public class ToDo extends Task {
    private TaskType type;

    public ToDo(String description) {
        super(description);
        this.type = TaskType.T;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", type, super.toString());
    }
}
