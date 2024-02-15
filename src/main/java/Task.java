/*
 * This class represents a task we want to record.
 */
class Task {
    private final String description;
    private boolean isDone;

    /*
     * A constructor that depicts a new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * A method that marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /*
     * A method that will undo the mark on a task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /*
     * A method that returns the isDone boolean.
     *
     * @return A boolean depending on whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /*
     * A method that returns the description.
     *
     * @return A string of the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * A method that returns the task status as a string.
     *
     * @return A check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}