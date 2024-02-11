package entity;

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
     * Helper function to generate a string representation of the ToDo object for saving purposes.
     *
     * @return A string representation of the Event object for saving to a file.
     */
    @Override
    public String save() {
        if (this.getMarked()) {
            return "T | Done | " + this.title;
        } else {
            return "T | Not Done | " + this.title;
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
}
