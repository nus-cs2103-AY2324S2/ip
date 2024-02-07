public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    public String simpleToString() {
        return "T " + super.simpleToString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
