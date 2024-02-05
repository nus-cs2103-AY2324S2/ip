public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
