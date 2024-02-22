package teletubbi;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns Deadline task details in the format to be stored in duke.txt.
     *
     * @return Deadline details in file format.
     */
    public String getFileFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
