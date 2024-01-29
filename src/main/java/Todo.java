public class Todo extends Task {
    private static final String TYPE = "Todo";
    Todo(String description) {
        super(description, TYPE);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
