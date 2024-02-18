package adam.task;

/**
 * @inheritDoc
 */
public class Todo extends Task {
    /**
     * @inheritDoc
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toFileString() {
        return "T," + super.toFileString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
