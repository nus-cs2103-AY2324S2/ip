package seedu.klara.task;

import java.io.Serializable;

/**
 * Represents the <code>Task</code> class to store information
 * about the user-created task e.g., <code>description, isDone</code>
 */
public class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Constructor for <code>Task</code>.
     * @param description Represents description of <code>Task</code>
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks <code>Task</code> as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks <code>Task</code> as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns description of <code>Task</code>.
     * @return Description of <code>Task</code>
     */
    public boolean containsSearchTerm(String searchTerm) {
        assert searchTerm != null : "Search term should not be null";
        return description.contains(searchTerm);
    }

    /**
     * Represents overridden toString method for printing <code>Task</code> details.
     * @return details of type <code>String</code>
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
