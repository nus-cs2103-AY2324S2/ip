package rick.tasks;

import rick.logic.RickException;

/**
 * The to-do tasks.
 */
public class ToDo implements Task {
    private String name;
    private String status;

    /**
     * Creates a new ToDo Item instance with specified name and status.
     * @param name the name of the Item.
     * @param status the status of the Item, either done or not done.
     * @throws RickException when there is a problem with user input
     */
    public ToDo(String name, String status) throws RickException {
        if (name.isBlank()) {
            throw new RickException("Nothing to do!");
        }
        this.name = name;
        this.status = status;
    }

    /**
     * Returns the string representation for the ToDo item that is understandable for the user.
     * @return a user-friendly string representation for the item.
     */
    @Override
    public String toString() {
        return "[T]" + this.status + " " + this.name;
    }

    /**
     * Marks the item as done.
     */
    public void mark() {
        this.status = "[X]";
    }

    /**
     * Unmarks the item as not yet done.
     */
    public void unmark() {
        this.status = "[ ]";
    }

    /**
     * Returns the item in a string format that is compatible with the local data file.
     * @return a string representation of the item in a particular format.
     */
    public String store() {
        return "T|" + this.status + "|" + this.name;
    }
}
