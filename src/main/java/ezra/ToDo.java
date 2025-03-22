package ezra;

/**
 * Represents a simple to-do task without a specific deadline or start/end time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the ToDo object to display to the user.
     *
     * @return A formatted string including task type, status, and description.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns a formatted string representation of the ToDo object for storage purposes.
     *
     * @return A formatted string suitable for storage, including task type, status, and description.
     */
    @Override
    public String toStorageString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Checks if this ToDo object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ToDo)) {
            return false;
        }
        ToDo t = (ToDo) o;
        return this.description.equals(t.description);
    }

    @Override
    public ToDo copy() {
        ToDo copy = new ToDo(this.description);
        copy.isDone = this.isDone;
        return copy;
    }
}
