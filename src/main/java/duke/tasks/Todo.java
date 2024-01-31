package duke.tasks;

/**
 * Class represent Task type Todo.
 */
public class Todo extends Task {
    /**
     * Initializes a Todo object with given params.
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     */
    public Todo(Boolean status, String detail) {
        super(status, detail);
    }

    /**
     * Formats object to be stored in file.
     * @return Formatted string to be stored in file.
     */
    @Override
    public String inFileStringFormat() {
        return "T|" + super.inFileStringFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
