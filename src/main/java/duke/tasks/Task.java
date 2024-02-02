package duke.tasks;

/**
 * Parent class for task
 * @param description
 */
public class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for Task
     * @param description String task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task when loaded from a save
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * gets status of class whether it is marked or not
     * @return String X or ' '
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a task
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Creates the save format for task
     * @return String Task save format
     */
    public String saveFormat() {
        String isDoneSave = (isDone ? "1" : "0");
        return String.format("%s;;%s",
                isDoneSave, description);
    }

    /**
     * @return String for task to be printed
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
            this.getStatusIcon(),
            this.description);
    }

}
