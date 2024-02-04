public class ToDo extends Task {
    public ToDo(String name) {
        this(name, false);
    }

    public ToDo(String name, boolean mark) {
        super(name, mark);
    }

    @Override
    public String toSaveString() {
        return String.format("T\t%s", super.toSaveString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
