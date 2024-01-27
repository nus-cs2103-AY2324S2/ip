public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    @Override
    public String toString() {
        return type.getSymbol() + super.toString();
    }
}