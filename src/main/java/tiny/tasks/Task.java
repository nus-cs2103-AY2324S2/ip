package tiny.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes Task.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void taskDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as done.
     */
    public void taskUndone() {
        this.isDone = false;
    }

    /**
     * Search the description of the task using the keyword.
     *
     * @param keyword Keyword to search for.
     * @return True if the description contains the keyword, otherwise False.
     */
    public boolean descriptionSearch(String keyword) {
        return description.contains(keyword);
    }        

    /**
     * Formats the task into the correct format to save.
     *
     * @return String of the task in the correct format to save.
     */    
    public String toSave() {
        return " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
