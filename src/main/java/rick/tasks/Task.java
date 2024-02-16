package rick.tasks;

/**
 * Interface for all tasks.
 */
public interface Task {
    /**
     * Marks the item as done.
     */
    public void mark();

    /**
     * Unmarks the item as not yet done.
     */
    public void unmark();

    /**
     * Returns the item in a string format that is compatible with the local data file.
     * @return a string representation of the item in a particular format.
     */
    public String store();
}
