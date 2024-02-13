package lite.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveToFile() {
        return "T!" + super.saveToFile() + "\n";
    }
}
