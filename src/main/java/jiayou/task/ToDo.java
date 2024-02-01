package jiayou.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStringForStore() {
        return "T" + super.toStringForStore();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
