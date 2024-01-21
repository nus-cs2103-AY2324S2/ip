public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getType() {
        return "T";
    }
}
