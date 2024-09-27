package rick.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import rick.logic.RickException;

/**
 * The deadline task.
 */
public class Deadline implements Task {
    private String name;
    private String status;
    private LocalDateTime dueDateTime;
    private boolean includesTime;

    /**
     * Creates a new Deadline Item instance with specified name, status and due date/time.
     * @param name the name of the Item.
     * @param status the status of the Item, either done or not done.
     * @param ddl the due date or time, written in specified string format.
     * @throws RickException when there is a problem with user input
     */
    public Deadline(String name, String status, String ddl) throws RickException {
        try {
            if (name == null || name.isBlank()) {
                throw new RickException("Nothing is due!");
            }
            if (ddl == null || ddl.isBlank()) {
                throw new RickException("due when?");
            }
            this.name = name;
            this.includesTime = !(ddl.length() == 10);
            this.dueDateTime = ddl.length() == 10
                    ? LocalDateTime.parse(ddl + "T00:00:00")
                    : LocalDateTime.parse(ddl);
            this.status = status;
        } catch (Exception e) {
            throw new RickException("Something wrong with your input! "
                    + "Follow 'deadline [ddl] /by yyyy-mm-ddTHH:mm:ss'");
        }
    }

    /**
     * Returns the string representation for the Deadline item that is understandable for the user.
     * @return a user-friendly string representation for the item.
     */
    @Override
    public String toString() {
        if (this.includesTime) {
            return "[D]" + this.status + " " + this.name + " (by: "
                    + this.dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
        } else {
            return "[D]" + this.status + " " + this.name + " (by: "
                    + this.dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }

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
        return "D|" + this.status + "|" + this.name + "|" + this.dueDateTime;
    }
}
