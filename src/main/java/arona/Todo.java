package arona;

/**
 * A type of task that has no dates involved.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Process the string with unmodified date form which is to be saved in the file.
     *
     * @return String that is to be processed and stored in the file.
     */
    @Override
    public String userInputToString() {
        return "[T]" + super.toString();
    }
}
