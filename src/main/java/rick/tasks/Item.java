package rick.tasks;

public interface Item {
    String name = "";
    String status = "[ ]";

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
