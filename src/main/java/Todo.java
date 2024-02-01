public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String savedFormat() {
        return "T " + "|" + super.savedFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
