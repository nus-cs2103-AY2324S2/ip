package lrbg.codriver.data;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileSaveString() {
        return "T|" + super.toFileSaveString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Todo) {
            Todo other = (Todo) obj;
            return other.getDescription().equals(this.getDescription());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getDescription().hashCode();
    }
}
