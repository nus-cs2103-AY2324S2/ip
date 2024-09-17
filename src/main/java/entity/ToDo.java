package entity;

import java.time.LocalDateTime;

/**
 * The ToDo class which extends the Task class
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class
     * @param title
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Constructor for ToDo class
     * @param title
     * @param isMarked
     */
    public ToDo(String title, boolean isMarked) {
        super(title);
        this.isMarked = isMarked;
    }

    /**
     * Helper function to generate a string representation of the ToDo object for saving purposes.
     *
     * @return A string representation of the Event object for saving to a file.
     */
    @Override
    public String save() {
        if (this.getMarked()) {
            return "T|Done|" + this.title;
        } else {
            return "T|Not Done|" + this.title;
        }
    }
    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

    @Override
    public LocalDateTime getDate() {
        return null;
    }
}
