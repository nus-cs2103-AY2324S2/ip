public class Todo extends Task {

    protected String from;
    protected String to;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}