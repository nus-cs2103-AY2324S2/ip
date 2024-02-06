package tasks;

/**
 * <p>
 *  represents a generic Task that a user would want to save
 *  </p>
 */
public class Task {
    private final String description;
    private boolean isDone;
    private final char shortForm;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.shortForm = 'T';
    }

    public Task(String description, char shortForm) {
        this.description = description;
        this.isDone = false;
        this.shortForm = shortForm;
    }

    public Task(String description, boolean isDone, char shortForm) {
        this.description = description;
        this.isDone = isDone;
        this.shortForm = shortForm;
    }

    /**
     * Converts the boolean isDone to a String for printing
     * @return string "X" if done else " "
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Converts the boolean isDone to a String for Storage
     * @return string "1" if done else "0"
     */
    public String getStatusIconNum() {
        return (this.isDone ? "1" : "0");
    }

    public String getDescription() {

        return this.description;
    }

    public void markDone() {

        this.isDone = true;
    }

    public void unmarkDone() {

        this.isDone = false;
    }

    @Override
    public String toString() {

        return String.format("[%c][%s] %s", this.shortForm, getStatusIcon(), this.description);
    }

    public String prepareStore() {
        return String.format("%c | %s | %s", this.shortForm, getStatusIconNum(), this.description);
    }
}
