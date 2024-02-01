public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveTask() {
        return this.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
