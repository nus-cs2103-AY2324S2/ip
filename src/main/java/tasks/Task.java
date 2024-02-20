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

    /**
     * initialises a Task class
     * @param description describes a Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.shortForm = 'T';
    }

    /**
     * initialises a Task class
     * @param description describes a Task
     * @param shortForm character that represents the type of class
     */
    public Task(String description, char shortForm) {
        this.description = description;
        this.isDone = false;
        this.shortForm = shortForm;
    }

    /**
     * initialises a Task class
     * @param description describes a Task
     * @param isDone indicates if a Task has been marked as done
     * @param shortForm character that represents the type of class
     */
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
    public String getStatusIconAsNum() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Changes the boolean isDone to True
     */
    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * Changes the boolean isDone to False
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean descriptionContainsWord(String word) {
        return this.description.contains(word);
    }

    @Override
    public String toString() {

        return String.format("[%c][%s] %s", this.shortForm, getStatusIcon(), this.description);
    }

    public String prepareForStorage() {
        return String.format("%c | %s | %s", this.shortForm, getStatusIconAsNum(), this.description);
    }
}
